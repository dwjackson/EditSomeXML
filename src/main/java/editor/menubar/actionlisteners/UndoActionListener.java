/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (c) 2014-2016 David Jackson
 */

package editor.menubar.actionlisteners;

import xml.Element;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This listener is used to deal with "undo" actions
 */
public class UndoActionListener implements ActionListener {
    private Element root;

    public UndoActionListener(Element root) {
        this.root = root;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (root.canUndo()) {
            root.undo();
        }
    }
}
