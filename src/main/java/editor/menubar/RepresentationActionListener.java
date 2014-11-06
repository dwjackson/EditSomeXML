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

package editor.menubar;

import editor.views.ElementTreeView;
import xml.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This deals with events where the user wants to change the representation of
 * an element
 */
public class RepresentationActionListener implements ActionListener {
    private ElementTreeView elementTreeView;

    public RepresentationActionListener(ElementTreeView elementTreeView) {
        this.elementTreeView = elementTreeView;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Element elem = elementTreeView.getSelectedElement();

        if (elem != null) {
            JFrame repFrame = new JFrame();
            repFrame.setTitle("Element Representation");
            repFrame.setSize(300, 200);
            repFrame.setLayout(new FlowLayout());

            repFrame.add(new JLabel("Element"));
            JTextField elemField = new JTextField(elem.getTag(), 20);
            elemField.setEditable(false);

            repFrame.add(new JLabel("Representation"));
            String[] repChoices = {"Tag", "Attribute Value"};
            JComboBox repComboBox = new JComboBox(repChoices);
            repFrame.add(repComboBox);

            repFrame.add(new JLabel("Set for all of this type of element"));
            JCheckBox setForAllCheckBox = new JCheckBox();
            repFrame.add(setForAllCheckBox);

            JButton okButton = new JButton("OK");
            repFrame.add(okButton);

            repFrame.setVisible(true);
        } else {
            // TODO: Show an error window
        }
    }
}
