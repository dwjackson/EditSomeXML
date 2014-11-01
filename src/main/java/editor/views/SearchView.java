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

/**
 * This view is used when the user wants to search for particular things
 * within the element tree
 */
public class SearchView extends JFrame {
	public SearchView() {
        setTitle("Search");
        setSize(450,200);
        setLayout(new FlowLayout());

        add(new JLabel("Search for:"));
        JTextField queryField = new JTextField("", 20);
        add(queryField);

        JButton searchButton = new JButton("Search");
        add(searchButton);

        JButton prevButton = new JButton("Prev");
        add(prevButton);

        JButton nextButton = new JButton("Next");
        add(nextButton);

        setVisible(true);
	}
}
