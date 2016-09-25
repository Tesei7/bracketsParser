package ru.tesei7.bracketsParser.parser.impl;

import ru.tesei7.bracketsParser.lexer.BracketToken;
import ru.tesei7.bracketsParser.parser.BracketNode;
import ru.tesei7.bracketsParser.parser.Node;

import java.util.Collections;
import java.util.List;

public class BracketNodeImpl implements BracketNode {
    private final BracketToken token;

    public BracketNodeImpl(BracketToken token) {
        this.token = token;
    }

    @Override
    public BracketToken getBracket() {
        return token;
    }

    @Override
    public List<Node> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public String toString(String prefix) {
        return prefix + token;
    }

    @Override
    public String toString() {
        return toString("");
    }
}
