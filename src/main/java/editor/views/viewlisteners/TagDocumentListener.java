package editor.views.viewlisteners;

import xml.Element;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The TagDocumentListener updates an element's tag when the user edits it
 */
public class TagDocumentListener extends ElementDocumentListener implements DocumentListener {
    public TagDocumentListener(Element element) {
        super(element);
    }

    private void updateElementTag(DocumentEvent documentEvent) {
        if (elem != null) {
            String str = getStringFromEvent(documentEvent);
            elem.setTag(str);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        updateElementTag(documentEvent);
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        updateElementTag(documentEvent);
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
        updateElementTag(documentEvent);
    }
}
