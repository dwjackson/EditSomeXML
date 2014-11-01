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

package editor;

import editor.controllers.ElementTreeController;
import editor.menubar.EditSomeXMLMenuBar;
import editor.views.ElementEditorView;
import editor.views.ElementTreeView;
import utility.Logger;
import xml.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The EditSomeXML class is the main editor class of EditSomeXML.
 */
public class EditSomeXML extends JFrame {
	
	private void setUpFrame() {
        setSize(800, 600);
        setTitle("EditSomeXML");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.out.println("[DEBUG] Closing main window");
                Logger.getInstance().closeLog();
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {
                Logger.getInstance().closeLog();
                dispose();
                System.exit(0);
            }
        });
        setLayout(new FlowLayout());
	}
	
    public EditSomeXML() {
        ElementTreeData data = new ElementTreeData(new Element());

        setUpFrame(); // Set up the main window

        // Set up the tree view
        ElementTreeController elementTreeController;
        elementTreeController = new ElementTreeController();
        ElementTreeView elementTreeView;
        elementTreeView = new ElementTreeView(data, elementTreeController);
        add(elementTreeView);

        // Set up the element editor view
        ElementEditorView elementEditorView = new ElementEditorView();
        elementTreeController.setElementEditorView(elementEditorView);
        add(elementEditorView);

        // Set up the menu bar
        EditSomeXMLMenuBar menuBar = new EditSomeXMLMenuBar(this, data, elementTreeView);
        setJMenuBar(menuBar);

        // Make the main frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        new EditSomeXML();
    }
}
