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

package editor.controllers;

import editor.views.MirrorElementView;
import xml.Element;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This controller is used when the user wants to mirror an element
 */
public class MirrorElementController implements ActionListener {
    private MirrorElementView view;

    public MirrorElementController(MirrorElementView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Element mirroredElement = view.getMirroredElement();
        System.out.println("[DEBUG] mirroredElement = " + mirroredElement);
        if (mirroredElement != null && mirroredElement != view.getRoot()) {
            Element parent = mirroredElement.getParent();
            System.out.println("[DEBUG] parent = " + parent);
            Element mirrorElement = new Element();
            mirrorElement.mirrorElement(mirroredElement);
            parent.addChild(mirrorElement);

            view.dispose();
        }
    }
}
