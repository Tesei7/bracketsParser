package ru.tesei7.bracketsParser.parser;

import ru.tesei7.bracketsParser.lexer.Token;

public class BracketsParserException extends RuntimeException {

    private static final long serialVersionUID = -5850809567711565813L;
    private Token token;

    public BracketsParserException(String message) {
        super(message);
    }

    public BracketsParserException(String message, Token token) {
        super(message);
        this.token = token;
    }

    public Token getToken() {
        return token;
    }
}
