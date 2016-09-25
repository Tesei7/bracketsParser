package ru.tesei7.bracketsParser.parser;

import ru.tesei7.bracketsParser.lexer.TextToken;

public interface TextNode extends Node {
    TextToken getTextToken();
}
