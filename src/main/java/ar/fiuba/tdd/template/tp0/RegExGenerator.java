package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;
import java.util.List;

public class RegExGenerator {
    //private int maxLength;

    public RegExGenerator(/*int maxLength*/) {
        //this.maxLength = maxLength;
    }

    public List<String> generate(String regEx, int numberOfResults) {
        TokenGenerator tokenGenerator = new TokenGenerator(regEx);
        ArrayList<Token> tokens = tokenGenerator.getTokens();
        System.out.println("" + regEx);

        // Cuantificador dice cuantas veces tiene que generar el caracter
        ArrayList<String> resultado = new ArrayList<String>();
        int it2 = 0;
        while (it2 < numberOfResults) {
            it2 += 1;
            resultado.add(evaluate(tokens));
        }

        //final String res = resultado;
        return resultado;
    }

    private String evaluate(ArrayList<Token> tokens) {
        // Una vez que tengo todos los tokens generados, emito un String

        StringBuffer buf = new StringBuffer();
        for (Token token: tokens) {
            buf.append(token.getResult());
        }
        String resultado = buf.toString();

        System.out.println("" + resultado);

        return resultado;
    }
}