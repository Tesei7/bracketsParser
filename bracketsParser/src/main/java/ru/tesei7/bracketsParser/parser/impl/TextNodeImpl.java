package ru.tesei7.bracketsParser.parser.impl;

import ru.tesei7.bracketsParser.lexer.TextToken;
import ru.tesei7.bracketsParser.parser.Node;
import ru.tesei7.bracketsParser.parser.TextNode;

import java.util.Collections;
import java.util.List;

public class TextNodeImpl implements TextNode {
    private final TextToken token;

    public TextNodeImpl(TextToken token) {
        this.token = token;
    }

    @Override
    public List<Node> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public TextToken getTextToken() {
        return token;
    }

    @Override
    public String toString() {
        return toString("");
    }

    @Override
    public String toString(String prefix) {
        return String.format("%s'%s'", prefix, token);
    }
}
