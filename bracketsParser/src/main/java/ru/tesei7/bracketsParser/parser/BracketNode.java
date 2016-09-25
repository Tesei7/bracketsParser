package ru.tesei7.bracketsParser.parser;

import ru.tesei7.bracketsParser.lexer.BracketToken;

public interface BracketNode extends Node {
    BracketToken getBracket();
}
