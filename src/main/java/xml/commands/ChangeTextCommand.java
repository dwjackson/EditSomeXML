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

public class ChangeTextCommand extends ElementCommand implements Command {
	private String text;
	private String oldText;
	private final String NAME = "Change Text";
	
	public ChangeTextCommand(Element element, String text) {
		this(element, text, new String(element.getText()));
	}

	public ChangeTextCommand(Element element, String text, String oldText) {
		super(element);
		this.oldText = oldText;
		this.text = text;
	}
	
	@Override
	public void perform() {
		element.setText(text);
	}

	@Override
	public void undo() {
		element.setText(oldText);
	}

	public String getName() {
	    return NAME;
	}

	public String getText() {
		return text;
	}

	public String getOldText() {
		return oldText;
	}

	@Override
	public boolean canCombine(Command cmd) {
		return (cmd.getClass() == getClass());
	}

	@Override
	public Command combine(Command cmd) {
		if (cmd.getClass() != getClass()) {
			return null;
		}

		ChangeTextCommand chainCommand = (ChangeTextCommand) cmd;
		String combinedText = text + chainCommand.getText();

		ChangeTextCommand combinedCommand;
		combinedCommand = new ChangeTextCommand(element, text, oldText);

		return combinedCommand;
	}
}
