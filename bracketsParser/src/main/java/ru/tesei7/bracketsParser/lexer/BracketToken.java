package ru.tesei7.bracketsParser.lexer;

public interface BracketToken extends Token {
	BracketTokenType getType();
	BracketDirection getDirection();
}
