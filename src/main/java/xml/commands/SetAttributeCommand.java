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
