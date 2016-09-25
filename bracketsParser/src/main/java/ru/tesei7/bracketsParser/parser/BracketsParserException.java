package ru.tesei7.bracketsParser.parser;

import ru.tesei7.bracketsParser.lexer.BracketToken;

public class BracketsParserException extends RuntimeException {

	private static final long serialVersionUID = -5850809567711565813L;
	private BracketToken token;

	public BracketsParserException(String message) {
		super(message);
	}
	
	public BracketsParserException(String message, BracketToken token) {
		super(message);
		this.token = token;
	}

	public BracketToken getToken() {
		return token;
	}
}
