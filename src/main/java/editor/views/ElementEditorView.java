package editor.views;

import editor.ElementDocumentListener;
import editor.TagDocumentListener;
import editor.TextDocumentListener;
import xml.Element;

import javax.swing.*;
import javax.swing.text.Document;
import java.util.ArrayList;

/**
 * The ElementEditorView is used to edit the currently-selected element.
 */
public class ElementEditorView extends JPanel {
    private JTextField tagField;
    private AttributesPanelView attributesPanel;
    private JTextArea elementTextArea;
    private Element elem;
    private ArrayList<ElementDocumentListener> documentListeners;

    /**
     * Create the ElementEditorView and leave all of its fields blank
     */
    public ElementEditorView() {
        elem = null;
        documentListeners = new ArrayList<ElementDocumentListener>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Tag
        JPanel tagPanel = new JPanel();
        tagPanel.add(new JLabel("Tag"));
        tagField = new JTextField("", 20);
        TagDocumentListener tagDocumentListener;
        tagDocumentListener = new TagDocumentListener(elem);
        documentListeners.add(tagDocumentListener);
        tagField.getDocument().addDocumentListener(tagDocumentListener);
        tagPanel.add(tagField);
        add(tagPanel);

        // Attributes
        attributesPanel = new AttributesPanelView();
        add(attributesPanel);

        // Text
        JPanel textPanel = new JPanel();
        textPanel.add(new JLabel("Text"));
        elementTextArea = new JTextArea();
        elementTextArea.setRows(4);
        elementTextArea.setColumns(40);
        textPanel.add(elementTextArea);
        TextDocumentListener textDocumentListener;
        textDocumentListener = new TextDocumentListener(elem);
        documentListeners.add(textDocumentListener);
        Document doc = elementTextArea.getDocument();
        doc.addDocumentListener(textDocumentListener);
        add(textPanel);

        setVisible(true);
    }

    /**
     * Populate this view with the data of a particular element
     * @param element The element with whose data to populate this view
     */
    public void populateWithElementData(Element element) {
        elem = element;
        for (ElementDocumentListener listener : documentListeners) {
            listener.setElement(elem);
        }
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
