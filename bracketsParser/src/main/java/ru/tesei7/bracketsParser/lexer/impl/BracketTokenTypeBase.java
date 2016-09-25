package ru.tesei7.bracketsParser.lexer.impl;

import ru.tesei7.bracketsParser.lexer.BracketDirection;
import ru.tesei7.bracketsParser.lexer.BracketTokenType;
import ru.tesei7.bracketsParser.lexer.BracketsLexerException;

public class BracketTokenTypeBase implements BracketTokenType {

	private final String leftBracket;
	private final String rightBracket;
	private final String description;

	public BracketTokenTypeBase(String leftBracket, String rightBracket, String description)
			throws BracketsLexerException {
		if (leftBracket == null || rightBracket == null || description == null) {
			throw new BracketsLexerException("Bracket values and description should not be null");
		}
		if (leftBracket.equals(rightBracket)) {
			throw new BracketsLexerException("Left and right brackets should be different");
		}
		this.leftBracket = leftBracket;
		this.rightBracket = rightBracket;
		this.description = description;
	}

	@Override
	public String getBracket(BracketDirection direction) throws BracketsLexerException {
		if (direction == null)
			throw new BracketsLexerException("Direction should not be null");
		switch (direction) {
		case LEFT:
			return leftBracket;
		case RIGHT:
			return rightBracket;
		default:
			throw new BracketsLexerException("Unexpected bracket direction: " + direction);
		}
	}

	@Override
	public String getDescription() {
		return description;
	}
}
