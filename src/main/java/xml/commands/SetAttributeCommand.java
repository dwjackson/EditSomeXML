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
		super(element);
		attName = key;
		attVal = val;
		
		oldVal = element.getAttribute(key);
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

	@Override
	public boolean canCombine() {
		// TODO
		return false;
	}

	@Override
	public Command combine(Command cmd) {
		// TODO
		return null;
	}
}
