/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (C) 2014-2016  David Jackson
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
