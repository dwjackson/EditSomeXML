package editor;

import xml.Element;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 * This is the superclass of all DocumentListener implementers that have to
 * perform work on the element tree
 */
public class ElementDocumentListener {
    protected Element elem;

    public ElementDocumentListener(Element element) {
        elem = element;
    }

    protected String getStringFromEvent(DocumentEvent documentEvent) {
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

    public void setElement(Element element) {
        elem = element;
    }
}
