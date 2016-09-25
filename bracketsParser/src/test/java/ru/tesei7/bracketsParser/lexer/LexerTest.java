package ru.tesei7.bracketsParser.lexer;

import static junit.framework.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import ru.tesei7.bracketsParser.lexer.impl.BracketTokenTypeBase;
import ru.tesei7.bracketsParser.lexer.impl.BracketsLexerImpl;
import ru.tesei7.bracketsParser.parser.BracketsParser;

public class LexerTest {
    @Rule
    public TestName name = new TestName();

    @Test
    public void testDefaultLexer() throws IOException {
        doTest();
    }

    @Test
    public void testEmpty() throws IOException {
        doTest();
    }

    @Test
    public void testWithLineBreak() throws IOException {
        doTest();
    }

    @Test
    public void testWithCustomBrackets() throws IOException {
        doTest(new BracketTokenTypeBase("<", ">", "corner bracket"));
    }

    private void doTest(BracketTokenType... additional) throws IOException {
        String testName = name.getMethodName().substring(4);
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("lexer/" + testName + ".txt").getFile());
        File resultFile = new File(classLoader.getResource("lexer/" + testName + "_result.txt").getFile());
        String content = FileUtils.readFileToString(file).replaceAll(System.lineSeparator(), "\n");
        String result = FileUtils.readFileToString(resultFile).replaceAll(System.lineSeparator(), "\n");

        List<BracketTokenType> types = new ArrayList<>();
        types.addAll(Arrays.asList(BracketsParser.DEFAULT_TYPES));
        types.addAll(Arrays.asList(additional));
        BracketsLexerImpl lexer = new BracketsLexerImpl(types);
        List<Token> tokens = lexer.tokenize(content);
        StringBuilder sb = new StringBuilder();
        for (Token token : tokens) {
            sb.append(token).append("\n");
        }

        String expected = sb.toString();
        assertEquals(expected, result);
    }
}
