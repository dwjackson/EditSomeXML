/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (c) 2014-2016 David Jackson
 */

package editor.menubar.actionlisteners;

import editor.controllers.NewRootController;
import editor.views.NewRootView;
import xml.Element;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The NewRootActionListener is used to deal with the user action of creating
 * a new root element
 */
public class NewRootActionListener implements ActionListener {
    private Element root;

    public NewRootActionListener(Element root) {
        this.root = root;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        NewRootController controller = new NewRootController(root);
        NewRootView view = new NewRootView(controller);
        controller.setView(view);
    }
}
