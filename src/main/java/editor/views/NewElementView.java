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
