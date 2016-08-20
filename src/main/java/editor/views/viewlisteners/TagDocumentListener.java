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
import xml.commands.ChangeTagCommand;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The TagDocumentListener updates an element's tag when the user edits it
 */
public class TagDocumentListener extends ElementDocumentListener implements DocumentListener {
    private ElementEditorView view;

    public TagDocumentListener(Element element, ElementEditorView view) {
        super(element);
        this.view = view;
    }

    private void updateElementTag(DocumentEvent documentEvent) {
        if (elem != null) {
            view.stopObserving();
            String str = getStringFromEvent(documentEvent);
            //elem.setTag(str); // TODO: Remove
            ChangeTagCommand cmd = new ChangeTagCommand(elem, str);
            elem.performCommand(cmd);
            view.startObserving();
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
