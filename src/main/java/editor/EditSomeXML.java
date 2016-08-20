/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (C) 2014-2016  David Jackson
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
        EditSomeXMLMenuBar menuBar = new EditSomeXMLMenuBar(this, data,
        		elementTreeView, elementEditorView);
        setJMenuBar(menuBar);

        // Make the main frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        new EditSomeXML();
    }
}
