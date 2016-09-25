package ru.tesei7.bracketsParser.parser;

import java.util.List;

public interface Node {
    List<Node> getChildren();
    String toString(String prefix);
}
