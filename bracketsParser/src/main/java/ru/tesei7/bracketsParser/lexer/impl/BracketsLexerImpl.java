package ru.tesei7.bracketsParser.lexer.impl;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import ru.tesei7.bracketsParser.lexer.BracketDirection;
import ru.tesei7.bracketsParser.lexer.BracketTokenType;
import ru.tesei7.bracketsParser.lexer.BracketsLexer;
import ru.tesei7.bracketsParser.lexer.BracketsLexerException;
import ru.tesei7.bracketsParser.lexer.Token;

public class BracketsLexerImpl implements BracketsLexer {

    private Collection<BracketTokenType> types;
    private int index;

	public BracketsLexerImpl(Collection<BracketTokenType> types) {
		this.types = types;
	}

	@Override
	public List<Token> tokenize(String content) throws BracketsLexerException {
		if (content == null)
			throw new BracketsLexerException("Content should not be null");
		LinkedList<Token> tokens = new LinkedList<>();
		index = 0;
		int length = content.length();
		do {
            BracketDirection direction = null;
            BracketTokenType type = null;
			int nearestIndex = Integer.MAX_VALUE;
			for (BracketTokenType t : types) {
				String bracket = t.getBracket(BracketDirection.LEFT);
				int indexOf = content.indexOf(bracket, index);
				if (indexOf != -1 && indexOf < nearestIndex) {
					type = t;
					direction = BracketDirection.LEFT;
					nearestIndex = indexOf;
				}
				bracket = t.getBracket(BracketDirection.RIGHT);
				indexOf = content.indexOf(bracket, index);
				if (indexOf != -1 && indexOf < nearestIndex) {
					type = t;
					direction = BracketDirection.RIGHT;
					nearestIndex = indexOf;
				}
			}
            index = nearestIndex;
			if (direction != null) {
				addTextTokenIfNeeded(content, tokens);
				BracketTokenImpl token = new BracketTokenImpl(index, type, direction);
				tokens.add(token);
				index += token.getLength();
			} else {
				index = length;
			}
		} while (index != length);
		addTextTokenIfNeeded(content, tokens);
		return tokens;
	}

	private void addTextTokenIfNeeded(String content, LinkedList<Token> tokens) {
		int prevIndex = 0;
		if (!tokens.isEmpty()) {
			Token last = tokens.getLast();
			prevIndex = last.getIndex() + last.getLength();
		}
		if (prevIndex != index) {
			tokens.add(new TextTokenImpl(index, content.substring(prevIndex, index)));
		}
	}
}