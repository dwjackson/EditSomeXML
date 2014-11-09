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

package editor.menubar.actionlisteners;

import editor.views.ElementTreeView;
import editor.views.RepresentationView;
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
            new RepresentationView(elem);
        } else {
            // TODO: Show an error window
        }
    }
}
