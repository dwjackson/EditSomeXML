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

public class CloneElementCommand implements Command {
	private Element parent;
	private Element clonedElement;
	private int numberOfClones;
	private Element[] clones;
	private final String NAME = "Clone Element";
	
	public CloneElementCommand(Element parent, Element childToClone,
                               int numberOfClones) {
		this.parent = parent;
		clonedElement = childToClone.cloneWithoutChildren();
		this.numberOfClones = numberOfClones;
		
		clones = null;
	}

	@Override
	public void perform() {
		clones = new Element[numberOfClones];
		for (int i = 0; i < numberOfClones; i++) {
			clones[i] = clonedElement.cloneWithoutChildren();
			parent.addChild(clones[i]);
		}
	}

	@Override
	public void undo() {
		for (int i = 0; i < numberOfClones; i++) {
			parent.deleteChild(clones[i]);
		}
		clones = null;
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
