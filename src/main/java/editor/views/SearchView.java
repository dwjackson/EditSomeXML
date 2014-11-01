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
