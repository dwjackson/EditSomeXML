package editor;

import xml.Element;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 * The TagDocumentListener updates an element's tag when the user edits it
 */
public class TagDocumentListener implements DocumentListener {
    private Element elem;

    public TagDocumentListener(Element element) {
        elem = element;
    }

    private String getStringFromEvent(DocumentEvent documentEvent) {
        Document doc = documentEvent.getDocument();
        String txt = null;
        try {
            txt = doc.getText(0, doc.getLength());
            System.out.println("[DEBUG] txt = " + txt);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        return txt;
    }

    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        String str = getStringFromEvent(documentEvent);
        elem.setTag(str);
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        String str = getStringFromEvent(documentEvent);
        elem.setTag(str);
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
        String str = getStringFromEvent(documentEvent);
        elem.setTag(str);
    }

    public void setElement(Element element) {
        elem = element;
    }
}
