package ru.tesei7.bracketsParser.parser.impl;

import ru.tesei7.bracketsParser.parser.BracketNode;
import ru.tesei7.bracketsParser.parser.BracketsNode;
import ru.tesei7.bracketsParser.parser.Node;

import java.util.Collections;
import java.util.List;

public class BracketsNodeImpl implements BracketsNode {
    private final BracketNode left;
    private final BracketNode right;
    private final List<Node> children;

    public BracketsNodeImpl(BracketNode left, BracketNode right, List<Node> children) {
        this.left = left;
        this.right = right;
        this.children = Collections.unmodifiableList(children);
    }

    @Override
    public BracketNode getLeftBracket() {
        return left;
    }

    @Override
    public BracketNode getRightBracket() {
        return right;
    }

    @Override
    public List<Node> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return toString("");
    }

    @Override
    public String toString(String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix).append(left).append("\n");
        for (Node b : children) {
            sb.append(b.toString(prefix + "  ")).append("\n");
        }
        sb.append(prefix).append(right);
        return sb.toString();
    }
}
