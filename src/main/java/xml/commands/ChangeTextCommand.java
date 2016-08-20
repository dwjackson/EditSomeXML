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

public class ChangeTextCommand extends ElementCommand implements Command {
	private String text;
	private String oldText;
	private final String NAME = "Change Text";
	
	public ChangeTextCommand(Element element, String text) {
		this(element, text, new String(element.getText()));
	}

	/**
	 * Initialize this command with the element whose text to change, the new
	 * value of the element's textual content and the old value of the
	 * element's textual content
	 * @param element The element whose text to modify
	 * @param text The new value of the element's text
	 * @param oldText The old value of the element's text
	 */
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

	public Element getElement() {
		return element;
	}

	@Override
	public boolean canCombine(Command cmd) {
		if (cmd.getClass() != getClass()) {
			return false;
		}
		return (((ChangeTextCommand)cmd).getElement() == element);
	}

	@Override
	public Command combine(Command cmd) {
		ChangeTextCommand chainCommand = (ChangeTextCommand) cmd;
		String combinedText = chainCommand.getText();

		ChangeTextCommand combinedCommand;
		combinedCommand = new ChangeTextCommand(element, combinedText, oldText);

		return combinedCommand;
	}
}
