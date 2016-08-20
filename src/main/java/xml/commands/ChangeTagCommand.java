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

public class ChangeTagCommand extends ElementCommand implements Command {
	private String tag;
	private String oldTag;
	private final String NAME = "Change Tag";
	
	public ChangeTagCommand(Element element, String tag) {
		this(element, tag, new String(element.getTag()));
	}

	public ChangeTagCommand(Element element, String tag, String oldTag) {
		super(element);
		this.tag = tag;
		this.oldTag = oldTag;
	}

	@Override
	public void perform() {
		element.setTag(tag);
	}

	@Override
	public void undo() {
		element.setTag(oldTag);
	}

	public String getName() {
	    return NAME;
	}

	public String getOldTag() {
		return oldTag;
	}

	public String getTag() {
		return tag;
	}

	public Element getElement() {
		return element;
	}

	@Override
	public boolean canCombine(Command cmd) {
		if (cmd.getClass() != getClass()) {
			return false;
		}
		return (((ChangeTagCommand)cmd).getElement() == element);
	}

	@Override
	public Command combine(Command cmd) {
		ChangeTagCommand chainCommand = (ChangeTagCommand) cmd;
		String combinedTag = chainCommand.getTag();

		ChangeTagCommand combinedCommand;
		combinedCommand = new ChangeTagCommand(element, combinedTag, oldTag);

		return combinedCommand;
	}
}
