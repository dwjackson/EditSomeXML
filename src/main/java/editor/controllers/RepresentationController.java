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
import editor.views.RepresentationView;

public class RepresentationController implements ActionListener {
	private RepresentationView view;
	
	public RepresentationController(RepresentationView representationView) {
		view = representationView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Element elem = view.getElement();
		switch(view.getRepresentationType()) {
		case TAG:
			elem.setRepresentationToTag();
			break;
		case ATTRIBUTE_VALUE:
			String attName = view.getAttributeName();
			elem.setRepresentationToAttributeValue(attName);
			break;
		default:
			// Do nothing
		}
		
		view.dispose();
	}
}
