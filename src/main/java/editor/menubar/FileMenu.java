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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import xml.Element;
import editor.ElementTreeData;
import editor.menubar.actionlisteners.ExportActionListener;
import editor.menubar.actionlisteners.ImportActionListener;
import editor.menubar.actionlisteners.NewRootActionListener;
import editor.menubar.actionlisteners.SaveActionListener;
import editor.menubar.actionlisteners.SaveAsActionListener;

/**
 * This is the "File" menu of the menu bar
 */
public class FileMenu extends JMenu {
    public FileMenu(final JFrame frame, ElementTreeData data) {
        Element root = data.getRoot();
        
        setText("File");
        JMenuItem newItem = new JMenuItem("New...");
        newItem.addActionListener(new NewRootActionListener(root));
        add(newItem);

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setEnabled(false);
        saveItem.addActionListener(new SaveActionListener());
        add(saveItem);
        JMenuItem saveAsItem = new JMenuItem("Save As...");
        saveAsItem.addActionListener(new SaveAsActionListener(root));
        add(saveAsItem);
        
        addSeparator();

        JMenuItem exportItem = new JMenuItem("Export...");
        exportItem.addActionListener(new ExportActionListener(data));
        add(exportItem);
        JMenuItem importItem = new JMenuItem("Import...");
        importItem.addActionListener(new ImportActionListener(data));
        add(importItem);

        addSeparator();

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
            }
        });
        add(exitItem);
    }
}
