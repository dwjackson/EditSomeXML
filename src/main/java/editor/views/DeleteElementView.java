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

import editor.controllers.DeleteElementController;
import xml.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This view is used in the deletion of an element of the tree
 */
public class DeleteElementView extends JFrame {
    private ElementTreeView elementTreeView;

    public DeleteElementView(ElementTreeView elementTreeView) {
        this.elementTreeView = elementTreeView;
        Element elem = elementTreeView.getSelectedElement();

        setSize(400,80);
        setLayout(new FlowLayout());
        setTitle("Delete Element");

        add(new JLabel("Delete element:"));
        JTextField tagField = new JTextField(elem.getTag(), 20);
        tagField.setEditable(false);
        add(tagField);

        JButton okButton = new JButton("OK");
        DeleteElementController controller;
        controller = new DeleteElementController(this, elem.getParent());
        okButton.addActionListener(controller);
        add(okButton);

        setVisible(true);
    }

}
