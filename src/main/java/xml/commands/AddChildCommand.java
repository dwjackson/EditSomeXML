/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/* 
 * Copyright (c) 2014-2016  David Jackson
 */

package xml.commands;

import utility.Command;
import xml.Element;

public class AddChildCommand extends ElementCommand implements Command {
	private Element child;
	private final String NAME = "Add Child";
	
	public AddChildCommand(Element parent, Element child) {
		super(parent);
		this.child = child;
	}

	@Override
	public void perform() {
		element.addChild(child);
	}

	@Override
	public void undo() {
		element.deleteChild(child);
	}

	public String getName() {
	    return NAME;
	}

	@Override
	public boolean canCombine(Command cmd) {
		return false;
	}

	@Override
	public Command combine(Command cmd) {
		return null;
	}
}
