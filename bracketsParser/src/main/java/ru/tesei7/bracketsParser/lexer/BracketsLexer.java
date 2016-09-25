package ru.tesei7.bracketsParser.lexer;

import java.util.List;

public interface BracketsLexer {
	public List<Token> tokenize(String content) throws BracketsLexerException;
}
