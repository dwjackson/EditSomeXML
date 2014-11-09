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

import editor.menubar.actionlisteners.RepresentationActionListener;
import editor.views.ElementTreeView;

import javax.swing.*;

/**
 * The view menu is used to change how things look in the UI
 */
public class ViewMenu extends JMenu {
    private ElementTreeView elementTreeView;

    public ViewMenu(ElementTreeView elementTreeView) {
        this.elementTreeView = elementTreeView;

        setText("View");

        JMenuItem representationItem;
        representationItem = new JMenuItem("Element Representation");
        RepresentationActionListener listener;
        listener = new RepresentationActionListener(elementTreeView);
        representationItem.addActionListener(listener);
        add(representationItem);
    }
}
