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

import editor.views.ElementEditorView;
import xml.Element;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The ElementTreeController is used to deal with the various mouse actions
 * that happen to the element tree.
 */
public class ElementTreeController extends MouseAdapter {
    private JTree tree;
    private ElementEditorView elementEditorView;

    /**
     * Initialize the ElementTreeController
     */
    public ElementTreeController() {
        tree = null;
        elementEditorView = null;
    }

    /**
     * When the mouse is double-clicked, open the element for editing in the
     * editor pane.
     * @param e The mouse event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        int selRow = tree.getRowForLocation(e.getX(), e.getY());
        TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
        if (selPath != null) {
	        Element elem = (Element) selPath.getLastPathComponent();
	        if (selRow != -1) {
	            if (e.getClickCount() == 1) {
	                elementEditorView.populateWithElementData(elem);
	            }
	        }
        }
    }

    public void setTree(JTree tree) {
        this.tree = tree;
    }

    public void setElementEditorView(ElementEditorView view) {
        elementEditorView = view;
    }
}
