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
import xml.commands.MirrorElementCommand;
import editor.views.MirrorElementView;

/**
 * This controller is used when the user wants to mirror an element
 */
public class MirrorElementController implements ActionListener {
    private MirrorElementView view;

    public MirrorElementController(MirrorElementView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Element mirroredElement = view.getMirroredElement();
        if (mirroredElement != null && mirroredElement != view.getRoot()) {
            MirrorElementCommand cmd;
            cmd = new MirrorElementCommand(mirroredElement);
            mirroredElement.performCommand(cmd);

            view.dispose();
        }
    }
}
