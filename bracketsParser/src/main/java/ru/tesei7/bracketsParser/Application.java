package ru.tesei7.bracketsParser;

import ru.tesei7.bracketsParser.lexer.BracketsLexerException;
import ru.tesei7.bracketsParser.parser.BracketsParserException;
import ru.tesei7.bracketsParser.parser.Node;
import ru.tesei7.bracketsParser.parser.impl.BracketsParser;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.List;

public class Application {
    static final String TITLE = "Brackets Parser";
    static final BracketsParser PARSER = new BracketsParser();

    private JFrame frame;
    private JTextArea textArea;
    private JTextArea label;

    public void createAndShowGUI() {
        frame = new JFrame(TITLE);
        frame.setContentPane(createContentPane());
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    private Container createContentPane() {
        JPanel contentPane = new JPanel(new BorderLayout());
        textArea = new JTextArea();
        textArea.setRows(20);
        textArea.setColumns(80);
        textArea.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                reparseText();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                reparseText();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                reparseText();
            }
        });
        contentPane.add(new JScrollPane(textArea), BorderLayout.CENTER);
        label = new JTextArea();
        label.setRows(20);
        label.setColumns(80);
        label.setEditable(false);
        contentPane.add(new JScrollPane(label), BorderLayout.SOUTH);
        return contentPane;
    }

    private void reparseText() {
        String content = textArea.getText();
        label.setText(content);
        try {
            List<Node> nodes = PARSER.parse(content);
            StringBuilder sb = new StringBuilder();
            nodes.stream().forEach(n -> sb.append(n).append("\n"));
            label.setText(sb.toString());
        } catch (BracketsParserException | BracketsLexerException e) {
            label.setText(e.getMessage());
        }
    }
}
