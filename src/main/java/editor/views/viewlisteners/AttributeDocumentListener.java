package editor.views.viewlisteners;

import xml.Element;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * This class is used to watch for changes to attribute fields in the editor
 */
public class AttributeDocumentListener extends ElementDocumentListener
        implements DocumentListener {

    enum Source {
        NONE, NAME, VALUE
    }

    private int attributeIndex;
    private JTextField nameField;
    private JTextField valueField;

    public AttributeDocumentListener(Element element,
                                     int attributeIndex,
                                     JTextField nameField,
                                     JTextField valueField) {
        super(element);
        this.attributeIndex = attributeIndex;
        this.nameField = nameField;
        this.valueField = valueField;
    }

    private Source determineSource(DocumentEvent event) {
        if (event.getDocument() == nameField.getDocument()) {
            return Source.NAME;
        } else if (event.getDocument() == valueField.getDocument()) {
            return Source.VALUE;
        } else {
            return Source.NONE;
        }
    }

    private void updateAttribute(DocumentEvent event) {
        String text = getStringFromEvent(event);

        if (determineSource(event) == Source.NAME) {
            elem.renameAttribute(attributeIndex, text);
        } else if (determineSource(event) == Source.VALUE) {
            elem.setAttribute(elem.getAttributeName(attributeIndex), text);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        updateAttribute(documentEvent);
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        updateAttribute(documentEvent);
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
        updateAttribute(documentEvent);
    }
}
