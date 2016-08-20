/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (C) 2014-2016  David Jackson
 */

package editor.views.viewlisteners;

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
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        return txt;
    }

    public void setElement(Element element) {
        elem = element;
    }

    public void unsetElement() {
        elem = null;
    }
}
