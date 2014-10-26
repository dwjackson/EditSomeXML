/*
 * EditSomeXML is a graphical XML editor
 * 
 * Copyright (C) 2014  David Jackson
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
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
