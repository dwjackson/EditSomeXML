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
import xml.commands.ChangeRepresentationCommand;
import editor.views.RepresentationView;

public class RepresentationController implements ActionListener {
	private RepresentationView view;
	
	public RepresentationController(RepresentationView representationView) {
		view = representationView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    Element elem = view.getElement();
	    ChangeRepresentationCommand cmd;
	    cmd = new ChangeRepresentationCommand(elem,
	            view.getRepresentationType(), view.getAttributeName(),
	            view.setForAll());
	    elem.performCommand(cmd);
	    /* TODO: Delete this
		Element elem;
		boolean setForAll = false;
		
		if (!view.setForAll()) {
			elem = view.getElement();
		} else {
			elem = view.getElement().getRoot();
			setForAll = true;
		}
		
		switch(view.getRepresentationType()) {
		case TAG:
			elem.setRepresentationToTag(setForAll);
			break;
		case ATTRIBUTE_VALUE:
			String attName = view.getAttributeName();
			elem.setRepresentationToAttributeValue(attName, setForAll);
			break;
		default:
			// Do nothing
		}
		*/
		
		view.dispose();
	}
}
