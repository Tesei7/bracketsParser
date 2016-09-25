package ru.tesei7.bracketsParser;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import ru.tesei7.bracketsParser.lexer.BracketsLexerException;
import ru.tesei7.bracketsParser.parser.BracketsParser;
import ru.tesei7.bracketsParser.parser.BracketsParserException;

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
			PARSER.parse(content);
		} catch (BracketsParserException | BracketsLexerException e) {
			label.setText(e.getMessage());
		}
	}
}
