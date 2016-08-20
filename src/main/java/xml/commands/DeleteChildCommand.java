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

public class DeleteChildCommand implements Command {
	private Element parent;
	private Element child;
	private int childIndex;
	private final String NAME = "Delete Child";
	
	public DeleteChildCommand(Element parent, Element child) {
		this.parent = parent;
		this.child = child;
		childIndex = parent.getIndexOfChild(child);
	}

	@Override
	public void perform() {
		parent.deleteChild(child);
	}

	@Override
	public void undo() {
		parent.addChildAtIndex(child, childIndex);
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
