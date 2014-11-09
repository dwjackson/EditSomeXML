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

import editor.menubar.actionlisteners.NewElementActionListener;
import editor.views.CloneElementView;
import editor.views.DeleteElementView;
import editor.views.ElementTreeView;
import editor.views.MirrorElementView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the "Edit" menu of the menu bar
 */
public class EditMenu extends JMenu {
    public EditMenu(final ElementTreeView elementTreeView) {
        setText("Edit");
        
        JMenuItem undoItem = new JMenuItem("Undo");
        add(undoItem);
        
        JMenuItem redoItem = new JMenuItem("Redo");
        add(redoItem);
        
        addSeparator();

        JMenuItem newElementItem = new JMenuItem("New Child Element...");
        NewElementActionListener listener;
        listener = new NewElementActionListener(elementTreeView);
        newElementItem.addActionListener(listener);
        add(newElementItem);

        JMenuItem cloneElementItem = new JMenuItem("Clone Element...");
        cloneElementItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new CloneElementView(elementTreeView);
            }
        });
        add(cloneElementItem);

        JMenuItem mirrorItem = new JMenuItem("Mirror Element...");
        mirrorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new MirrorElementView(elementTreeView);
            }
        });
        add(mirrorItem);

        JMenuItem deleteItem = new JMenuItem("Delete Element...");
        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new DeleteElementView(elementTreeView);
            }
        });
        add(deleteItem);
    }
}
