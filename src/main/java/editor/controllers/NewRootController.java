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
import xml.ElementEvent;
import editor.views.NewRootView;

/**
 * The NewRootController is used to deal with actions initiated by the user in
 * the NewRootView GUI.
 */
public class NewRootController implements ActionListener {
    private Element root;
    private NewRootView view;

    /**
     * Create the controller and associate it with a root element. This
     * element is the model that is to be updated by this controller.
     * @param root The root element of the XML tree
     */
    public NewRootController(Element root) {
        this.root = root;
        view = null;
    }

    /**
     * Associate a view with this controller. The view is used to get input
     * from the user.
     * @param view The view with which to associate this controller
     */
    public void setView(NewRootView view) {
        this.view = view;
    }

    /**
     * Create the new root element
     * @param e The ActionEvent that triggers this action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        root.setTag(view.getRootTag());
        view.teardown();
        ElementEvent.EventType eventType;
        eventType = ElementEvent.EventType.NEW_ROOT;
        root.notifyObservers(new ElementEvent(eventType, root));
    }
}
