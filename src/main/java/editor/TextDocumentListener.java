package editor;

import xml.Element;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The TextDocumentListener updates the element tree when the user changes the
 * text content of an element in the UI
 */
public class TextDocumentListener extends ElementDocumentListener implements DocumentListener {
    public TextDocumentListener(Element element) {
        super(element);
    }

    private void updateElementText(DocumentEvent documentEvent) {
        if (elem != null) {
            String text = getStringFromEvent(documentEvent);
            elem.setText(text);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        updateElementText(documentEvent);
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        updateElementText(documentEvent);
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
        updateElementText(documentEvent);
    }
}
