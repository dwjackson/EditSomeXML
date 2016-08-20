/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (C) 2014-2016  David Jackson
 */

package editor.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import editor.ElementTreeData;
import editor.views.ElementEditorView;
import editor.views.SearchView;

/**
 * This is the "Search" menu of the menu bar
 */
public class SearchMenu extends JMenu {
    public SearchMenu(final ElementEditorView elementEditorView,
    		final ElementTreeData data) {
        setText("Search");

        JMenuItem searchItem = new JMenuItem("Search...");
        searchItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchView(elementEditorView, data);
            }
        });
        add(searchItem);
    }
}
