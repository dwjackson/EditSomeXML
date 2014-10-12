package editor.views;

import javax.swing.*;
import java.awt.*;

/**
 * The ElementEditorView is used to edit the currently-selected element.
 */
public class ElementEditorView extends JPanel {
    private JTextField tagField;
    private JPanel attributesPanel;
    private JTextArea elementTextArea;

    public ElementEditorView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new JLabel("Tag"));
        tagField = new JTextField("", 20);
        add(tagField);

        add(new JLabel("Attributes"));
        attributesPanel = new JPanel();
        attributesPanel.setLayout(new GridLayout());
        attributesPanel.add(new JTextField("", 10));
        attributesPanel.add(new JTextField("", 10));
        add(attributesPanel);

        add(new JLabel("Text"));
        elementTextArea = new JTextArea();
        add(elementTextArea);

        setVisible(true);
    }
}
