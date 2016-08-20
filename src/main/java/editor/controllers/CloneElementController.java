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
import xml.commands.CloneElementCommand;
import editor.views.CloneElementView;

/**
 * the CloneElementController handles events from the CloneElementView
 * @see editor.views.CloneElementView
 */
public class CloneElementController implements ActionListener {
    private CloneElementView view;

    public CloneElementController(CloneElementView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Element origElem = view.getElement();
        int numClones = view.getNumberOfClones();
        if (origElem != null && !origElem.isRoot()) {
        	Element parent = origElem.getParent();
        	CloneElementCommand cmd;
        	cmd = new CloneElementCommand(parent, origElem, numClones);
        	parent.performCommand(cmd);
            view.dispose();
        }
    }
}
