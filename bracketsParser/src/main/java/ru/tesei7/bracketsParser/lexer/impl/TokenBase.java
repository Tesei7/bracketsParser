package ru.tesei7.bracketsParser.lexer.impl;

import ru.tesei7.bracketsParser.lexer.Token;

public abstract class TokenBase implements Token {
	private final int index;

	public TokenBase(int index) {
		this.index = index;
	}

	@Override
	public int getIndex() {
		return index;
	}
}
