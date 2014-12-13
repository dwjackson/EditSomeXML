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

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import xml.Element;
import editor.menubar.actionlisteners.MoveUpDownActionListener;
import editor.menubar.actionlisteners.NewElementActionListener;
import editor.menubar.actionlisteners.RedoActionListener;
import editor.menubar.actionlisteners.UndoActionListener;
import editor.views.CloneElementView;
import editor.views.DeleteElementView;
import editor.views.ElementTreeView;
import editor.views.MirrorElementView;

/**
 * This is the "Edit" menu of the menu bar
 */
public class EditMenu extends JMenu {
    public EditMenu(final ElementTreeView elementTreeView) {
        setText("Edit");

        Element root = elementTreeView.getRoot();
        JMenuItem undoItem = new JMenuItem("Undo");
        KeyStroke undoShortcut = KeyStroke.getKeyStroke('Z',
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        undoItem.setAccelerator(undoShortcut);
        undoItem.addActionListener(new UndoActionListener(root));
        add(undoItem);
        
        JMenuItem redoItem = new JMenuItem("Redo");
        KeyStroke redoShortcut = KeyStroke.getKeyStroke('Y',
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        redoItem.setAccelerator(redoShortcut);
        redoItem.addActionListener(new RedoActionListener(root));
        add(redoItem);
        
        addSeparator();

        JMenuItem newElementItem = new JMenuItem("New Child Element...");
        KeyStroke newElementShortcut = KeyStroke.getKeyStroke('E',
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        newElementItem.setAccelerator(newElementShortcut);
        NewElementActionListener listener;
        listener = new NewElementActionListener(elementTreeView);
        newElementItem.addActionListener(listener);
        add(newElementItem);

        JMenuItem cloneElementItem = new JMenuItem("Clone Element...");
        KeyStroke cloneShortcut = KeyStroke.getKeyStroke('L',
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        cloneElementItem.setAccelerator(cloneShortcut);
        cloneElementItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new CloneElementView(elementTreeView);
            }
        });
        add(cloneElementItem);

        JMenuItem mirrorItem = new JMenuItem("Mirror Element...");
        KeyStroke mirrorShortcut = KeyStroke.getKeyStroke('M',
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        mirrorItem.setAccelerator(mirrorShortcut);
        mirrorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new MirrorElementView(elementTreeView);
            }
        });
        add(mirrorItem);

        JMenuItem deleteItem = new JMenuItem("Delete Element...");
        KeyStroke deleteShortcut = KeyStroke.getKeyStroke('D',
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        deleteItem.setAccelerator(deleteShortcut);
        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new DeleteElementView(elementTreeView);
            }
        });
        add(deleteItem);
        
        addSeparator();
        
        MoveUpDownActionListener moveUpDownListener;
        moveUpDownListener = new MoveUpDownActionListener(elementTreeView);
        
        JMenuItem moveUpItem = new JMenuItem("Move Up");
        moveUpItem.setActionCommand("MOVE_UP");
        moveUpItem.addActionListener(moveUpDownListener);
        add(moveUpItem);
        
        JMenuItem moveDownItem = new JMenuItem("Move Down");
        moveDownItem.setActionCommand("MOVE_DOWN");
        moveDownItem.addActionListener(moveUpDownListener);
        add(moveDownItem);
    }
}
