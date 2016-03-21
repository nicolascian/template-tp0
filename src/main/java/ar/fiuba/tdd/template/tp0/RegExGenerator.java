package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;
import java.util.List;

public class RegExGenerator {
    private int maxLength;

    public RegExGenerator(int maxLength) {
        this.maxLength = maxLength;
    }

    public List<String> generate(String regEx, int numberOfResults) {
        // Debug
        //System.out.println("" + regEx);

        ArrayList<String> resultado = new ArrayList<String>();
        int it2 = 0;
        while (it2 < numberOfResults) {
            it2 += 1;
            TokenGenerator tokenGenerator = new TokenGenerator(regEx, maxLength);
            ArrayList<Token> tokens = tokenGenerator.getTokens();
            resultado.add(evaluate(tokens));
        }

        return resultado;
    }

    private String evaluate(ArrayList<Token> tokens) {
        // Una vez que tengo todos los tokens generados, emito un String

        StringBuffer buf = new StringBuffer();
        for (Token token: tokens) {
            buf.append(token.getResult());
        }
        String resultado = buf.toString();

        // Debug
        //System.out.println("" + resultado);

        return resultado;
    }
}