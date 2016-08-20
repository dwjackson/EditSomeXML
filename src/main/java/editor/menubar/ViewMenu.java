/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (C) 2014-2016  David Jackson
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
