package ru.tesei7.bracketsParser.parser;

public interface BracketsNode extends Node {
    BracketNode getLeftBracket();

    BracketNode getRightBracket();
}
