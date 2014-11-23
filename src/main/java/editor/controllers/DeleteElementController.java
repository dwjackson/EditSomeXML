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
