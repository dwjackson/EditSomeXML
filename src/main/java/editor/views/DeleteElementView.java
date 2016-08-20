/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (C) 2014-2016  David Jackson
 */

package editor.views;

import editor.controllers.DeleteElementController;
import xml.Element;

import javax.swing.*;
import java.awt.*;

/**
 * This view is used in the deletion of an element of the tree
 */
public class DeleteElementView extends JFrame {
    private Element elem;

    public DeleteElementView(ElementTreeView elementTreeView) {
        elem = elementTreeView.getSelectedElement();

        setSize(400,100);
        setLayout(new FlowLayout());
        setTitle("Delete Element");

        add(new JLabel("Delete element:"));
        JTextField tagField = new JTextField(elem.getTag(), 20);
        tagField.setEditable(false);
        add(tagField);

        JButton okButton = new JButton("OK");
        DeleteElementController controller;
        controller = new DeleteElementController(this);
        okButton.addActionListener(controller);
        add(okButton);

        setVisible(true);
    }
    
    public Element getElement() {
        return elem;
    }
}
