package ar.fiuba.tdd.template.tp0;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {

    private static class TokenInfo {
        public final Pattern regex;
        public final int token;

        public TokenInfo(Pattern regex, int token) {
            super();
            this.regex = regex;
            this.token = token;
        }
    }

    private LinkedList<TokenInfo> tokenInfos;
    private LinkedList<Token> tokens;

    public Tokenizer() {
        tokenInfos = new LinkedList<TokenInfo>();
        tokens = new LinkedList<Token>();
    }

    public void add(String regex, int token) {
        tokenInfos.add(new TokenInfo(Pattern.compile("^(" + regex + ")"), token));
    }

    public void tokenize(String str) {
        String string = str.trim();
        tokens.clear();
        while (!string.equals("")) {
            boolean match = false;
            for (TokenInfo info : tokenInfos) {
                Matcher mat = info.regex.matcher(string);
                if (mat.find()) {
                    match = true;
                    String tok = mat.group().trim();
                    string = mat.replaceFirst("").trim();
                    tokens.add(new Token(info.token, tok));
                    break;
                }
            }
            if (!match) {
                throw new RuntimeException("Unexpected character in input: " + string);
            }
        }
    }

    public LinkedList<Token> getTokens() {
        return tokens;
    }

}
