/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (C) 2014-2016  David Jackson
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
