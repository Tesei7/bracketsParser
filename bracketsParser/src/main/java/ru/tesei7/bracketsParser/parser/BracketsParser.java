package ru.tesei7.bracketsParser.parser;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import ru.tesei7.bracketsParser.lexer.BracketDirection;
import ru.tesei7.bracketsParser.lexer.BracketTokenType;
import ru.tesei7.bracketsParser.lexer.Token;
import ru.tesei7.bracketsParser.lexer.impl.BracketTokenTypes;
import ru.tesei7.bracketsParser.lexer.impl.BracketsLexerImpl;

public class BracketsParser {
	public static final BracketTokenType[] DEFAULT_TYPES = { BracketTokenTypes.PARENTHESIS,
			BracketTokenTypes.SQUARE_BRACKET, BracketTokenTypes.BRACE };

	private final Collection<BracketTokenType> types;

	private BracketsLexerImpl lexer;

	public BracketsParser(Collection<BracketTokenType> types) throws BracketsParserException {
		if (types == null)
			throw new BracketsParserException("Types should not be null");
		this.types = Collections.unmodifiableCollection(types);
		checkTypes();
		lexer = new BracketsLexerImpl(types);
	}

	public BracketsParser() throws BracketsParserException {
		this(Arrays.asList(DEFAULT_TYPES));
	}

	private void checkTypes() throws BracketsParserException {
		long uniqueLeftBracketsCount = types.stream().map(t -> t.getBracket(BracketDirection.LEFT)).distinct().count();
		long uniqueRightBracketsCount = types.stream().map(t -> t.getBracket(BracketDirection.RIGHT)).distinct()
				.count();
		if (types.size() != uniqueLeftBracketsCount || types.size() != uniqueRightBracketsCount) {
			throw new BracketsParserException("Incorrect bracket types: equal brackets in different types");
		}
	}

	public List<BracketsBlock> parse(String content) throws BracketsParserException {
		if (content == null)
			throw new BracketsParserException("Content should not be null");
		List<Token> tokens = lexer.tokenize(content);
		for (Token token : tokens) {
			System.out.println(token);
		}
		return null;
	}
}
