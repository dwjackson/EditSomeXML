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

import editor.ElementTreeData;
import editor.views.CloneElementView;
import editor.views.DeleteElementView;
import editor.views.ElementTreeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the menu bar for EditSomeXML
 */
public class EditSomeXMLMenuBar extends JMenuBar {
    public EditSomeXMLMenuBar(ElementTreeData data, final ElementTreeView elementTreeView) {
        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New...");
        newItem.addActionListener(new NewRootActionListener(data.getRoot()));
        fileMenu.add(newItem);

        fileMenu.addSeparator();

        JMenuItem exportItem = new JMenuItem("Export...");
        exportItem.addActionListener(new ExportActionListener(data.getRoot()));
        fileMenu.add(exportItem);
        JMenuItem importItem = new JMenuItem("Import...");
        importItem.addActionListener(new ImportActionListener(data));
        fileMenu.add(importItem);

        fileMenu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);
        add(fileMenu);

        // Edit Menu
        JMenu editMenu = new JMenu("Edit");
        JMenuItem newElementItem = new JMenuItem("New Element...");
        NewElementActionListener listener;
        listener = new NewElementActionListener(elementTreeView);
        newElementItem.addActionListener(listener);
        editMenu.add(newElementItem);
        JMenuItem cloneElementItem = new JMenuItem("Clone Element...");
        cloneElementItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new CloneElementView(elementTreeView);
            }
        });
        editMenu.add(cloneElementItem);
        JMenuItem deleteItem = new JMenuItem("Delete...");
        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new DeleteElementView(elementTreeView);
            }
        });
        editMenu.add(deleteItem);
        add(editMenu);
    }
}
