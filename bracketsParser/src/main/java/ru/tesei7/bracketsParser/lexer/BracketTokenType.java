package ru.tesei7.bracketsParser.lexer;

public interface BracketTokenType {
	String getBracket(BracketDirection direction) throws BracketsLexerException;
	String getDescription();
}
