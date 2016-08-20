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
import editor.views.RepresentationView;
import xml.Element;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This deals with events where the user wants to change the representation of
 * an element
 */
public class RepresentationActionListener implements ActionListener {	
    private ElementTreeView elementTreeView;

    public RepresentationActionListener(ElementTreeView elementTreeView) {
        this.elementTreeView = elementTreeView;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Element elem = elementTreeView.getSelectedElement();

        if (elem != null) {
            new RepresentationView(elem);
        } else {
            // TODO: Show an error window
        }
    }
}
