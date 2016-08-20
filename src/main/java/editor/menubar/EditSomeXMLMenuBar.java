/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (C) 2014-2016  David Jackson
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

        add(new ViewMenu(elementTreeView));

        add(new SearchMenu(elementEditorView, data));

        add(new HelpMenu());
    }
}
