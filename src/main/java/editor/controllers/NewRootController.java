/*
 * EditSomeXML is a graphical XML editor
 * 
 * Copyright (C) 2014  David Jackson
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
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
