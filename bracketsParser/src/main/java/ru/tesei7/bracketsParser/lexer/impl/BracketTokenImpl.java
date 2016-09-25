package ru.tesei7.bracketsParser.lexer.impl;

import ru.tesei7.bracketsParser.lexer.BracketDirection;
import ru.tesei7.bracketsParser.lexer.BracketToken;
import ru.tesei7.bracketsParser.lexer.BracketTokenType;
import ru.tesei7.bracketsParser.lexer.BracketsLexerException;

public class BracketTokenImpl extends TokenBase implements BracketToken {
	private BracketDirection direction;
	private BracketTokenType type;

	public BracketTokenImpl(int index, BracketTokenType type, BracketDirection direction)
			throws BracketsLexerException {
		super(index);
		if (type == null)
			throw new BracketsLexerException("Type should not be null");
		if (direction == null)
			throw new BracketsLexerException("Direction should not be null");
		this.type = type;
		this.direction = direction;
	}

	@Override
	public BracketTokenType getType() {
		return type;
	}

	@Override
	public BracketDirection getDirection() {
		return direction;
	}
	
	@Override
	public int getLength() {
		return type.getBracket(direction).length();
	}

	@Override
	public String toString() {
		String bracket = getType().getBracket(getDirection());
		return String.format("'%s' [%d]", bracket, getIndex());
	}
}
