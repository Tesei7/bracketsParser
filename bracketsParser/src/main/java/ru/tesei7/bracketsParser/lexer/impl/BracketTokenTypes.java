package ru.tesei7.bracketsParser.lexer.impl;

import ru.tesei7.bracketsParser.lexer.BracketTokenType;

public interface BracketTokenTypes {
	public static final BracketTokenType PARENTHESIS = new BracketTokenTypeBase("(", ")", "parenthesis");
	public static final BracketTokenType SQUARE_BRACKET = new BracketTokenTypeBase("[", "]", "square braket");
	public static final BracketTokenType BRACE = new BracketTokenTypeBase("{", "}", "brace");
}
