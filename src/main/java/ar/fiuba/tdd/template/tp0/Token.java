package ar.fiuba.tdd.template.tp0;

/**
 * Created by nicolas on 19/03/16.
 */
public class Token {
    public final int token;
    public final String sequence;

    public Token(int token, String sequence) {
        this.token = token;
        this.sequence = sequence;
    }

    public Token(String sequence) {
        this.token = 0;
        this.sequence = sequence;
    }

    public String getResult() {
        int rand = (int)(Math.random() * sequence.length());
        return sequence.substring(rand, rand + 1);
    }
}

