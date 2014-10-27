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

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

/**
 * This is the "Search" menu of the menu bar
 */
public class SearchMenu extends JMenu {
    public SearchMenu() {
        setText("Search");

        JMenuItem searchItem = new JMenuItem("Search...");
        searchItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame searchFrame = new JFrame();
                searchFrame.setTitle("Search");
                searchFrame.setSize(450,200);
                searchFrame.setLayout(new FlowLayout());

                searchFrame.add(new JLabel("Search for:"));
                JTextField queryField = new JTextField("", 20);
                searchFrame.add(queryField);

                JButton searchButton = new JButton("Search");
                searchFrame.add(searchButton);

                JButton prevButton = new JButton("Prev");
                searchFrame.add(prevButton);

                JButton nextButton = new JButton("Next");
                searchFrame.add(nextButton);

                searchFrame.setVisible(true);
            }
        });
        add(searchItem);
    }
}
