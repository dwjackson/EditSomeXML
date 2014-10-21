package editor.views;

import editor.TagDocumentListener;
import editor.TextDocumentListener;
import xml.Element;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ElementEditorView is used to edit the currently-selected element.
 */
public class ElementEditorView extends JPanel {
    private JTextField tagField;
    private AttributesPanelView attributesPanel;
    private JTextArea elementTextArea;
    private Element elem;
    private TagDocumentListener tagDocumentListener;
    private TextDocumentListener textDocumentListener;

    /**
     * Create the ElementEditorView and leave all of its fields blank
     */
    public ElementEditorView() {
        elem = null;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new JLabel("Tag"));
        tagField = new JTextField("", 20);
        tagDocumentListener = new TagDocumentListener(elem);
        tagField.getDocument().addDocumentListener(tagDocumentListener);
        add(tagField);

        attributesPanel = new AttributesPanelView();
        add(attributesPanel);

        JButton newAttributeButton = new JButton("Add Attribute");
        newAttributeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                attributesPanel.addAttribute();
            }
        });
        add(newAttributeButton);

        add(new JLabel("Text"));
        elementTextArea = new JTextArea();
        add(elementTextArea);
        textDocumentListener = new TextDocumentListener(elem);
        Document doc = elementTextArea.getDocument();
        doc.addDocumentListener(textDocumentListener);

        setVisible(true);
    }

    /**
     * Populate this view with the data of a particular element
     * @param element The element with whose data to populate this view
     */
    public void populateWithElementData(Element element) {
        elem = element;
        tagDocumentListener.setElement(elem);
        textDocumentListener.setElement(elem);
        tagField.setText(elem.getTag());
        // TODO: Set the attributes
        elementTextArea.setText(elem.getText());
    }

    /**
     * Remove all data from this view
     */
    public void depopulateAllData() {
        tagField.setText("");
        attributesPanel = new AttributesPanelView();
        elementTextArea.setText("");
        elem = null;
    }
}
