/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (C) 2014-2016  David Jackson
 */

package editor.views;

import editor.controllers.NewElementController;
import xml.Element;

import javax.swing.*;
import java.awt.*;

/**
 * The NewElementView is used to control the GUI of the window that creates a
 * new element
 */
public class NewElementView extends JFrame {
    private MonospaceJTextField tagField;
    private MonospaceJTextField parentTagField;

    public NewElementView(ElementTreeView elementTreeView) {
        setSize(500,100);
        setLayout(new FlowLayout());
        setTitle("New Element");

        add(new JLabel("Tag"));
        tagField = new MonospaceJTextField("", 20);
        add(tagField);

        add(new JLabel("Parent:"));
        parentTagField = new MonospaceJTextField("", 20);
        parentTagField.setEditable(false);
        Element parent = elementTreeView.getSelectedElement();
        if (parent == null) {
            parent = elementTreeView.getRoot();
        }
        parentTagField.setText(parent.getTag());
        add(parentTagField);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new NewElementController(this, parent));
        add(okButton);

        setVisible(true);
    }

    public String getTag() {
        return tagField.getText();
    }
}
