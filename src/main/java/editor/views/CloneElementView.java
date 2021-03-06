/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (C) 2014-2016  David Jackson
 */

package editor.views;

import editor.controllers.CloneElementController;
import xml.Element;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

/**
 * The CloneElementView is the window that pops up when the user wants to
 * clone (deep-copy) and element in the XML tree
 */
public class CloneElementView extends JFrame {
    private final int MAX_CLONES = 100;

    Element elem;
    private JFormattedTextField numberOfClonesField;

    public CloneElementView(ElementTreeView elementTreeView) {
        elem = null;

        setTitle("Clone Element");
        setSize(new Dimension(500, 100));
        setLayout(new FlowLayout());

        add(new JLabel("Element to Clone:"));
        JTextField tagField = new JTextField("", 20);
        tagField.setEditable(false);
        add(tagField);

        add(new JLabel("Number of clones:"));
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumIntegerDigits(2);
        numberOfClonesField = new JFormattedTextField(numberFormat);
        numberOfClonesField.setColumns(2);
        numberOfClonesField.setText("1");
        add(numberOfClonesField);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new CloneElementController(this));
        add(okButton);

        elem = elementTreeView.getSelectedElement();
        if (elem != null) {
            tagField.setText(elem.getTag());
        } else {
            okButton.setEnabled(false);
        }

        add(tagField);
        setVisible(true);
    }

    public Element getElement() {
        return elem;
    }

    public int getNumberOfClones() {
        Long val = (Long) numberOfClonesField.getValue();
        if (val == null || val < 0 || val > MAX_CLONES) {
            return 1;
        } else {
            return val.intValue();
        }
    }
}
