/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (c) 2014-2016  David Jackson
 */

package utility;

/**
 * The Commandable interface is used for any data structure on which commands
 * can be performed.
 * @see utility.CommandHistory
 * @see utility.Command
 */
public interface Commandable {
	/**
	 * Perform a command on the data
	 * @param cmd The command to perform
	 */
	public void performCommand(Command cmd);
	
	/**
	 * Undo the most recently performed command
	 */
	public void undo();
	
	/**
	 * Redo the most recently undone command
	 */
	public void redo();
	
	/**
	 * Determine if there's a command to undo
	 * @return true if there is a command available to be undone, false if not
	 */
	public boolean canUndo();
	
	/**
	 * Determine if there is a command to redo
	 * @return true if there is a command available to redo, false if not
	 */
	public boolean canRedo();
}
