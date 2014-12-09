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
	 * command
	 * @return true if this command can be combined with other commands of the
	 *         same class
	 */
	boolean canCombine();

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
