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

public class SetAttributeCommand extends ElementCommand implements Command {
	private String attName;
	private String attVal;
	private String oldVal;
	private final String NAME = "Set Attribute";

	public SetAttributeCommand(Element element, String key, String val) {
		this(element, key, val, element.getAttribute(key));
	}

	public SetAttributeCommand(Element element, String key, String val,
							   String oldVal) {
		super(element);
		attName = key;
		attVal = val;

		this.oldVal = oldVal;
	}
	
	@Override
	public void perform() {
		element.setAttribute(attName, attVal);
	}

	@Override
	public void undo() {
		if (oldVal != null) {
			element.setAttribute(attName, oldVal);
		} else {
			element.removeAttribute(attName);
		}
	}

	public String getName() {
	    return NAME;
	}

	public String getAttributeName() {
		return attName;
	}

	public String getAttributeValue() {
		return attVal;
	}

	public String getOldAttributeValue() {
		return oldVal;
	}

	public Element getElement() {
		return element;
	}

	@Override
	public boolean canCombine(Command cmd) {
		if (cmd.getClass() != getClass()) {
			return false;
		}

		SetAttributeCommand setAttributeCommand = (SetAttributeCommand) cmd;
		boolean attributeMatch;
		attributeMatch = setAttributeCommand.getAttributeName() == attName;

		boolean elementMatch = setAttributeCommand.getElement() == element;

		return (elementMatch && attributeMatch);
	}

	@Override
	public Command combine(Command cmd) {
		SetAttributeCommand chainCommand = (SetAttributeCommand) cmd;

		String combinedValue = chainCommand.getAttributeValue();
		SetAttributeCommand combinedCommand;
		combinedCommand = new SetAttributeCommand(element, attName, combinedValue, oldVal);

		return combinedCommand;
	}
}
