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
