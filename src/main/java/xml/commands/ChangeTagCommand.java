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

public class ChangeTagCommand extends ElementCommand implements Command {
	private String tag;
	private String oldTag;
	
	public ChangeTagCommand(Element element, String tag) {
		super(element);
		this.tag = tag;
		oldTag = new String(element.getTag());
	}

	@Override
	public void perform() {
		element.setTag(tag);
	}

	@Override
	public void undo() {
		element.setTag(oldTag);
	}

}
