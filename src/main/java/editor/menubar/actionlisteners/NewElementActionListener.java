/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (c) 2014-2016 David Jackson
 */

package editor.menubar.actionlisteners;

import editor.views.ElementTreeView;
import editor.views.NewElementView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The NewElementActionListener is used to deal with the user action of adding
 * a new element to the tree
 */
public class NewElementActionListener implements ActionListener {
    private ElementTreeView view;

    public NewElementActionListener(ElementTreeView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        new NewElementView(view);
    }
}
