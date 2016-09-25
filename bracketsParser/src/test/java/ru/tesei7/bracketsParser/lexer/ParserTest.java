package ru.tesei7.bracketsParser.lexer;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import ru.tesei7.bracketsParser.lexer.impl.BracketTokenTypeBase;
import ru.tesei7.bracketsParser.parser.BracketsParserException;
import ru.tesei7.bracketsParser.parser.Node;
import ru.tesei7.bracketsParser.parser.impl.BracketsParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParserTest {
    @Rule
    public TestName name = new TestName();

    @Test
    public void testEmpty() throws IOException {
        doTest();
    }

    @Test
    public void testDefaultParser() throws IOException {
        doTest();
    }

    @Test
    public void testWithAdditionalText() throws IOException {
        doTest();
    }

    @Test
    public void testIncompleteString() throws IOException {
        doTest();
    }

    @Test
    public void testUnexpectedRightBracket() throws IOException {
        doTest();
    }

    @Test
    public void testCustomBracketType() throws IOException {
        doTest(new BracketTokenTypeBase("<html>", "</html>", "html tag"));
    }

    @Test(timeout=1000)
    public void testPerformance() throws IOException {
        doTest();
    }

    private void doTest(BracketTokenType... additional) throws IOException {
        String testName = name.getMethodName().substring(4);
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("parser/" + testName + ".txt").getFile());
        File resultFile = new File(classLoader.getResource("parser/" + testName + "_result.txt").getFile());
        String content = FileUtils.readFileToString(file).replaceAll(System.lineSeparator(), "\n");
        String result = FileUtils.readFileToString(resultFile).replaceAll(System.lineSeparator(), "\n");

        List<BracketTokenType> types = new ArrayList<>();
        types.addAll(Arrays.asList(BracketsParser.DEFAULT_TYPES));
        types.addAll(Arrays.asList(additional));
        BracketsParser parser = new BracketsParser(types);
        try {
            StringBuilder sb = new StringBuilder();
            List<Node> nodes = parser.parse(content);
            for (Node node : nodes) {
                sb.append(node).append("\n");
            }

            String expected = sb.toString();
            assertEquals(expected, result);
        } catch (BracketsLexerException | BracketsParserException e) {
            assertEquals(e.getMessage(), result);
        }
    }
}
