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

	@Override
	public boolean canCombine() {
		return true;
	}

	@Override
	public Command combine(Command cmd) {
		if (cmd.getClass() != getClass()) {
			return null;
		}

		ChangeTagCommand chainCommand = (ChangeTagCommand) cmd;
		String combinedTag = tag + chainCommand.getTag();

		ChangeTagCommand combinedCommand;
		combinedCommand = new ChangeTagCommand(element, combinedTag, oldTag);

		return combinedCommand;
	}
}
