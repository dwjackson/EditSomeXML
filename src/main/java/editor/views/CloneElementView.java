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

package editor.views;

import editor.controllers.CloneElementController;
import xml.Element;

import javax.swing.*;
import java.awt.*;
import java.text.Format;
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
