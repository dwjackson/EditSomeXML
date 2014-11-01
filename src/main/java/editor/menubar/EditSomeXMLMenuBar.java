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

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import editor.ElementTreeData;
import editor.views.ElementEditorView;
import editor.views.ElementTreeView;

/**
 * This is the menu bar for EditSomeXML
 */
public class EditSomeXMLMenuBar extends JMenuBar {
    public EditSomeXMLMenuBar(JFrame frame, ElementTreeData data,
    		final ElementTreeView elementTreeView,
    		final ElementEditorView elementEditorView) {
        add(new FileMenu(frame, data));

        add(new EditMenu(elementTreeView));

        add(new SearchMenu(elementEditorView, data));

        add(new HelpMenu());
    }
}
