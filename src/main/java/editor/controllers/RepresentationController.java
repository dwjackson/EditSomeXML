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
		
		view.dispose();
	}
}
