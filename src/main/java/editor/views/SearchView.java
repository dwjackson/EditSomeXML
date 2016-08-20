/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (C) 2014-2016  David Jackson
 */

package editor.views;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import editor.ElementTreeData;
import editor.controllers.SearchController;

/**
 * This view is used when the user wants to search for particular things
 * within the element tree
 */
public class SearchView extends JFrame {
	private JTextField queryField;
	private JButton prevButton;
	private JButton nextButton;
	
	public SearchView(ElementEditorView elementEditorView, ElementTreeData data) {
        setTitle("Search");
        setSize(450,200);
        setLayout(new FlowLayout());
        
        SearchController controller = new SearchController(this,
        		elementEditorView, data);

        add(new JLabel("Search for:"));
        queryField = new JTextField("", 20);
        add(queryField);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(controller.getSearchActionListener());
        add(searchButton);

        prevButton = new JButton("Prev");
        prevButton.setEnabled(false);
        prevButton.addActionListener(controller.getPrevActionListener());
        add(prevButton);

        nextButton = new JButton("Next");
        nextButton.setEnabled(false);
        nextButton.addActionListener(controller.getNextActionListener());
        add(nextButton);

        setVisible(true);
	}
	
	public String getQuery() {
		return queryField.getText();
	}
	
	public void enablePrevAndNextButtons() {
		prevButton.setEnabled(true);
		nextButton.setEnabled(true);
	}
}
