/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (C) 2014-2016  David Jackson
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
