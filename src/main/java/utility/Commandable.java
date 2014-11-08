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
}
