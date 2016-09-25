package ru.tesei7.bracketsParser.parser.impl;

import ru.tesei7.bracketsParser.lexer.*;
import ru.tesei7.bracketsParser.lexer.impl.BracketTokenTypes;
import ru.tesei7.bracketsParser.lexer.impl.BracketsLexerImpl;
import ru.tesei7.bracketsParser.parser.BracketNode;
import ru.tesei7.bracketsParser.parser.BracketsParserException;
import ru.tesei7.bracketsParser.parser.Node;

import java.util.*;

public class BracketsParser {
    public static final BracketTokenType[] DEFAULT_TYPES = {BracketTokenTypes.PARENTHESIS,
            BracketTokenTypes.SQUARE_BRACKET, BracketTokenTypes.BRACE};

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

    public List<Node> parse(String content) throws BracketsParserException {
        if (content == null)
            throw new BracketsParserException("Content should not be null");
        List<Token> tokens = lexer.tokenize(content);
        LinkedList<Node> stack = new LinkedList<>();

        BracketTokenType currentType = null;
        Iterator<Token> iterator = tokens.iterator();
        while (iterator.hasNext()) {
            Token next = iterator.next();
            if (next instanceof TextToken) {
                stack.push(new TextNodeImpl((TextToken) next));
            } else if (next instanceof BracketToken) {
                BracketToken bracket = (BracketToken) next;
                if (bracket.getDirection() == BracketDirection.LEFT) {
                    stack.push(new BracketNodeImpl(bracket));
                    currentType = bracket.getType();
                } else if (bracket.getDirection() == BracketDirection.RIGHT) {
                    if (currentType == null || bracket.getType() != currentType) {
                        throw new BracketsParserException("Unexpected right bracket", bracket);
                    }
                    BracketNode right = new BracketNodeImpl(bracket);
                    LinkedList<Node> children = new LinkedList<>();
                    // find left bracket and collect children
                    BracketNode left = null;
                    do {
                        Node node = stack.pop();
                        if (node instanceof BracketNode) {
                            left = (BracketNode) node;
                        } else {
                            children.addFirst(node);
                        }
                    } while (left == null);
                    // update currentType
                    currentType = null;
                    if (!stack.isEmpty()) {
                        Iterator<Node> listIterator = stack.iterator();
                        while (listIterator.hasNext()) {
                            Node node = listIterator.next();
                            if (node instanceof BracketNode) {
                                currentType = ((BracketNode) node).getBracket().getType();
                                break;
                            }
                        }
                    }

                    stack.push(new BracketsNodeImpl(left, right, children));
                } else {
                    throw new BracketsParserException("Unsupported bracket direction", bracket);
                }
            } else {
                throw new BracketsParserException("Unsupported token type", next);
            }
        }
        for (Node node: stack) {
            if (node instanceof BracketNode) {
                throw new BracketsParserException("Incomplete string");
            }
        }
        Collections.reverse(stack);
        return stack;
    }
}
