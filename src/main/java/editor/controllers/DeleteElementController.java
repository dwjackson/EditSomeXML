/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (c) 2014-2016 David Jackson
 */

package editor.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import xml.Element;
import xml.commands.DeleteChildCommand;
import editor.views.DeleteElementView;

/**
 * This controller is used in the deletion of elements
 */
public class DeleteElementController implements ActionListener {
    private DeleteElementView view;

    public DeleteElementController(DeleteElementView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Element childToDelete = view.getElement();
        Element parent = childToDelete.getParent();
        DeleteChildCommand cmd;
        cmd = new DeleteChildCommand(parent, childToDelete);
        parent.performCommand(cmd);
        view.dispose();
    }
}
