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

import editor.views.ElementEditorView;
import xml.Element;
import xml.commands.ChangeTextCommand;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The TextDocumentListener updates the element tree when the user changes the
 * text content of an element in the UI
 */
public class TextDocumentListener extends ElementDocumentListener implements DocumentListener {
    private ElementEditorView view;

    public TextDocumentListener(Element element, ElementEditorView view) {
        super(element);
        this.view = view;
    }

    private void updateElementText(DocumentEvent documentEvent) {
        if (elem != null) {
            view.stopObserving();

            String text = getStringFromEvent(documentEvent);
            String oldText = elem.getText();
            ChangeTextCommand cmd = new ChangeTextCommand(elem, text, oldText);
            elem.performCommand(cmd);

            view.startObserving();
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
