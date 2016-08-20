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
import xml.commands.RenameAttributeCommand;
import xml.commands.SetAttributeCommand;

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
    private ElementEditorView editorView;

    public AttributeDocumentListener(Element element,
                                     int attributeIndex,
                                     JTextField nameField,
                                     JTextField valueField,
                                     ElementEditorView editorView) {
        super(element);
        this.attributeIndex = attributeIndex;
        this.nameField = nameField;
        this.valueField = valueField;
        this.editorView = editorView;
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

        editorView.stopObserving();
        if (determineSource(event) == Source.NAME) {
            String oldAttributeName;
            oldAttributeName = elem.getAttributeName(attributeIndex);
            RenameAttributeCommand cmd = new RenameAttributeCommand(elem,
                    text, oldAttributeName, attributeIndex);
            elem.performCommand(cmd);
        } else if (determineSource(event) == Source.VALUE) {
            String attName = elem.getAttributeName(attributeIndex);
            String attVal = elem.getAttribute(attName);
            SetAttributeCommand cmd;
            cmd = new SetAttributeCommand(elem, attName, text, attVal);
            elem.performCommand(cmd);
        }
        editorView.startObserving();
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
