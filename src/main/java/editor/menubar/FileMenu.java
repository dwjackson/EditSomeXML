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
import editor.ElementTreeData;
import editor.menubar.actionlisteners.ExportActionListener;
import editor.menubar.actionlisteners.ImportActionListener;
import editor.menubar.actionlisteners.NewRootActionListener;
import editor.menubar.actionlisteners.OpenActionListener;
import editor.menubar.actionlisteners.SaveActionListener;

/**
 * This is the "File" menu of the menu bar
 */
public class FileMenu extends JMenu {
    public FileMenu(final JFrame frame, ElementTreeData data) {
        Element root = data.getRoot();
        
        setText("File");
        
        JMenuItem newItem = new JMenuItem("New...");
        KeyStroke newShortcut = KeyStroke.getKeyStroke('N',
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        newItem.setAccelerator(newShortcut);
        newItem.addActionListener(new NewRootActionListener(root));
        add(newItem);
        
        JMenuItem openItem = new JMenuItem("Open...");
        KeyStroke openShortcut = KeyStroke.getKeyStroke('O',
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        openItem.setAccelerator(openShortcut);
        openItem.addActionListener(new OpenActionListener(data));
        add(openItem);

        SaveActionListener saveListener = new SaveActionListener(data);
        JMenuItem saveItem = new JMenuItem("Save");
        KeyStroke saveShortcut = KeyStroke.getKeyStroke('S',
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        saveItem.setAccelerator(saveShortcut);
        saveItem.setActionCommand("SAVE");
        saveItem.addActionListener(saveListener);
        add(saveItem);
        JMenuItem saveAsItem = new JMenuItem("Save As...");
        saveAsItem.setActionCommand("SAVE_AS");
        saveAsItem.addActionListener(saveListener);
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
