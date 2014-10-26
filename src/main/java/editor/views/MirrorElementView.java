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
