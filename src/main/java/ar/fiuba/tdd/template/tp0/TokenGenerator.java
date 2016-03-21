package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by nicolas on 20/03/16.
 */
public class TokenGenerator {
    private String regEx;
    private int max;
    private boolean escape = false;
    private Tokenizer tokenizer;

    private ArrayList<Token> tokens =  new ArrayList<Token>();

    public TokenGenerator(String regular, int maxLength) {
        regEx = regular;
        max = maxLength;

        tokenizer = new Tokenizer();
        tokenizer.add("\\\\", 1); // Una escape
        tokenizer.add("\\.", 2); // Un punto
        tokenizer.add("\\[", 3); // Comienza un grupo
        tokenizer.add("\\]", 4); // Termina un grupo
        tokenizer.add("\\+", 5); // Uno Muchos
        tokenizer.add("\\*", 6); // Cero Muchos
        tokenizer.add("\\?", 7); // Cero Uno
        tokenizer.add("[a-zA-Z0-9@]*", 8); // Literales
        tokenizer.tokenize(regEx);
    }

    public ArrayList<Token> getTokens() {
        // Recorrer los tokens e ir creando instancias de Token
        for (Token tok : tokenizer.getTokens()) {
            //System.out.println("" + tok.token + " " + tok.sequence);
            selectCase(tok.token, tok.sequence);
        }

        return tokens;
    }

    private void selectCase(int token, String sequence) {
        switch (token) {
            case 1: // Es un escape
                escape = true;
                break;
            case 2: // Es un punto
                evaluatePunto();
                break;
            case 3: // Abre corchete
                evaluateAbreCorchete();
                break;
            case 4: // Cierra corchete
                evaluateCierraCorchete();
                break;
            case 5: // Es un '+' Uno Muchos
                evaluateUnoMuchos();
                break;
            case 6: // Es un '*' Cero Muchos
                evaluateCeroMuchos();
                break;
            case 7: // Es un '?' Cero Uno
                evaluateCeroUno();
                break;
            case 8: // Son literales
                evaluateLiterales(sequence);
                break;
            default:
                throw new RuntimeException("No se puede evaluar la expresion");
        }
    }

    private void evaluatePunto() {
        if (escape) {
            tokens.add(new Token("."));
            escape = false;
        } else {
            tokens.add(new Token("aAbBcCdDeEfFgGhHiIjJkKmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789"));
        }
    }

    private void evaluateAbreCorchete() {
        if (escape) {
            tokens.add(new Token("["));
            escape = false;
        }
    }

    private void evaluateCierraCorchete() {
        if (escape) {
            tokens.add(new Token("]"));
            escape = false;
        }
    }

    private void evaluateUnoMuchos() {
        if (escape) {
            tokens.add(new Token("+"));
            escape = false;
        } else {
            // Para aumentar segun el cuantificador duplico el ultimo
            // Token de la lista las veces que sea necesario.
            int cantidad = getUnoMuchos();
            cuantificar(cantidad);
            // Debug
            //System.out.println("" + "+ cant: " + cantidad);
        }
    }

    private void evaluateCeroMuchos() {
        if (escape) {
            tokens.add(new Token("*"));
            escape = false;
        } else {
            // Para aumentar segun el cuantificador duplico el ultimo
            // Token de la lista las veces que sea necesario.
            int cantidad = getCeroMuchos();
            cuantificar(cantidad);
            // Debug
            //System.out.println("" + "* cant: " + cantidad);
        }
    }

    private void evaluateCeroUno() {
        if (escape) {
            tokens.add(new Token("?"));
            escape = false;
        } else {
            // Para aumentar segun el cuantificador duplico el ultimo
            // Token de la lista las veces que sea necesario.
            int cantidad = getCeroUno();
            cuantificar(cantidad);
            // Debug
            //System.out.println("" + "? cant: " + cantidad);
        }
    }

    private void evaluateLiterales(String sequence) {
        tokens.add(new Token(sequence));
        escape = false;
    }

    private void cuantificar(int cantidad) {
        if (cantidad == 0) {
            // Removemos el ultimo token del array
            tokens.remove(tokens.size() - 1);

        } else {
            // Para aumentar segun el cuantificador duplico el ultimo
            // Token de la lista las veces que sea necesario.
            int it3 = 0;
            while (it3 < cantidad - 1) {
                it3 += 1;
                tokens.add(tokens.get(tokens.size() - 1));
            }
        }
    }

    private int getCeroUno() {
        return getRandom(0, 1);
    }

    private int getCeroMuchos() {
        return getRandom(0, max);
    }

    private int getUnoMuchos() {
        return getRandom(1, max);
    }


    private int getRandom(int minimo, int maximo) {
        return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
    }
}
