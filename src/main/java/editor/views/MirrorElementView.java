/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (C) 2014-2016  David Jackson
 */

package editor.views;

import editor.controllers.MirrorElementController;
import xml.Element;

import javax.swing.*;
import java.awt.*;

/**
 * This view is used to mirror an element
 */
public class MirrorElementView extends JFrame {
    private Element elem;
    private ElementTreeView elementTreeView;

    public MirrorElementView(final ElementTreeView elementTreeView) {
        setTitle("Mirror an Element");
        setSize(400,100);
        setLayout(new FlowLayout());

        elem = elementTreeView.getSelectedElement();
        this.elementTreeView = elementTreeView;

        add(new JLabel("Mirror: "));
        JTextField tagField = new JTextField(elem.getTag(), 20);
        tagField.setEditable(false);
        add(tagField);

        MirrorElementController controller;
        controller = new MirrorElementController(this);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(controller);
        add(okButton);

        setVisible(true);
    }

    public Element getMirroredElement() {
        return elem;
    }

    public Element getRoot() {
        return elementTreeView.getRoot();
    }
}
