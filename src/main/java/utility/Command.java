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
 * The Command interface is used to represent actions taken on data in such a
 * way as to make them undo-able and redo-able.
 */
public interface Command {
	
	/**
	 * Perform the action represented by the command
	 */
	void perform();
	
	/**
	 * Undo the action represented by the command
	 */
	void undo();
	
	/**
	 * Get the name of this command (can appear in undo lists)
	 * @return the name of this command
	 */
	String getName();

	/**
	 * Determine if commands of this class can be combined into a single
	 * command with another command
	 * @param cmd The command to check against to determine if this command
	 *            can be combined
	 * @return true if this command can be combined with other commands of the
	 *         same class
	 */
	boolean canCombine(Command cmd);

	/**
	 * Combine this command with another command of the same class. The object
	 * that calls this method should be the first one in the chronological
	 * sequence of commands.
	 *
	 * @param cmd The command with which to combine this command
	 * @return the combined command or null if the combination is impossible
	 */
	Command combine(Command cmd);
}
