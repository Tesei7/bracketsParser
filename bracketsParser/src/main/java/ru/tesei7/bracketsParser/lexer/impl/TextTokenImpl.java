package ru.tesei7.bracketsParser.lexer.impl;

import ru.tesei7.bracketsParser.lexer.TextToken;

public class TextTokenImpl extends TokenBase implements TextToken {

	private final String text;

	public TextTokenImpl(int index, String text) {
		super(index);
		this.text = text;
	}

	@Override
	public String getText() {
		return text;
	}
	
	@Override
	public String toString() {
		return text;
	}
	
	@Override
	public int getLength() {
		return text.length();
	}

}
