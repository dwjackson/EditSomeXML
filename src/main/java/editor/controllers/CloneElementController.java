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

import editor.views.CloneElementView;
import xml.Element;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        int numberOfClones = view.getNumberOfClones();
        if (origElem != null && !origElem.isRoot()) {
            for (int i = 0; i < numberOfClones; i++) {
                Element clonedElem = origElem.cloneWithoutChildren();
                Element parent = origElem.getParent();
                parent.addChild(clonedElem);
                parent.notifyObservers();
            }
            view.dispose();
        }
    }
}
