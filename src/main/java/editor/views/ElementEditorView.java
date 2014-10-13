package editor.views;

import xml.Element;

import javax.swing.*;
import java.awt.*;

/**
 * The ElementEditorView is used to edit the currently-selected element.
 */
public class ElementEditorView extends JPanel {
    private JTextField tagField;
    private AttributesPanelView attributesPanel;
    private JTextArea elementTextArea;

    /**
     * Create the ElementEditorView and leave all of its fields blank
     */
    public ElementEditorView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new JLabel("Tag"));
        tagField = new JTextField("", 20);
        add(tagField);

        attributesPanel = new AttributesPanelView();
        add(attributesPanel);

        add(new JLabel("Text"));
        elementTextArea = new JTextArea();
        add(elementTextArea);

        setVisible(true);
    }

    /**
     * Populate this view with the data of a particular element
     * @param elem The element with whose data to populate this view
     */
    public void populateWithElementData(Element elem) {
        tagField.setText(elem.getTag());
        // TODO: Set the attributes
        elementTextArea.setText(elem.getText());
    }
}
