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
import xml.commands.AddChildCommand;
import editor.views.NewElementView;

/**
 * The NewElementController deals with the actions of the NewElementView
 * @see editor.views.NewElementView
 */
public class NewElementController implements ActionListener {
    private NewElementView view;
    private Element parent;

    public NewElementController(NewElementView view, Element parent) {
        this.view = view;
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Element child = new Element(view.getTag());
        parent.performCommand(new AddChildCommand(parent, child));
        view.dispose();
    }
}
