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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This the "Help" menu from the menu bar
 */
public class HelpMenu extends JMenu {
    public HelpMenu() {
        setText("Help");

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame aboutFrame = new JFrame();
                aboutFrame.setTitle("About EditSomeXML");
                aboutFrame.setSize(350,100);

                StringBuilder sb = new StringBuilder("<html>");
                sb.append("<div style=\"text-align: center;\">");
                sb.append("<p>EditSomeXML</p>");
                sb.append("<p>Copyright David Jackson</p>");
                sb.append("<p>This program is licensed under the GNU GPLv3</p>");
                sb.append("</div>");
                aboutFrame.add(new JLabel(sb.toString()));

                aboutFrame.setVisible(true);
            }
        });
        add(aboutItem);
    }
}
