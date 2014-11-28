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

import java.util.ArrayList;

/**
 * A CommandHistory objects holds a history of previously-issued commands for
 * a piece of data such that they can be undone/redone.
 * 
 * @see utility.Command
 */
public class CommandHistory {
	private final int NULL_INDEX = -1;
	private ArrayList<Command> commands;
	private int currCommandIdx;
	
	/**
	 * Initialize the command history as empty
	 */
	public CommandHistory() {
		commands = new ArrayList<Command>();
		currCommandIdx = NULL_INDEX;
	}
	
	/**
	 * Delete the history of commands from the current command to the end, but
	 * not deleting the current command itself
	 */
	private void deleteHistoryToEndFromCurrent() {
		int numCommands = commands.size();
		for (int i = currCommandIdx; i < numCommands; i++) {
			commands.remove(i);
		}
	}
	
	/**
	 * Perform a command, adding it to the command history
	 * @param cmd The command to perform
	 */
	public void performCommand(Command cmd) {
		int numCmds = commands.size();
		boolean commandHistoryIsEmpty = (numCmds == 0);
		boolean currCommandIsMostRecent = (numCmds == currCommandIdx + 1);
		
		if (commandHistoryIsEmpty) {
			cmd.perform();
			commands.add(cmd);
			currCommandIdx = 0;
		} else if (currCommandIsMostRecent) {
			cmd.perform();
			commands.add(cmd);
			currCommandIdx = commands.indexOf(cmd);
		} else {
			deleteHistoryToEndFromCurrent();
			assert(commands.size() == currCommandIdx + 1);
			cmd.perform();
			commands.add(cmd);
			currCommandIdx++;
		}
	}
	
	/**
	 * Undo the most recently performed command
	 */
	public void undo() {
		if (canUndo()) {
			Command cmd = commands.get(currCommandIdx);
			cmd.undo();
			currCommandIdx--;
		}
	}
	
	/**
	 * Redo the most recently undone command
	 */
	public void redo() {
		if (canRedo()) {
			currCommandIdx++;
			Command cmd = commands.get(currCommandIdx);
			cmd.perform();
		}
	}
	
	/**
	 * Determine if there is a command to undo
	 * @return true if there is a command to undo, false if not
	 */
	public boolean canUndo() {
		return (currCommandIdx >= 0);
	}
	
	/**
	 * Determine if there is a command to redo
	 * @return true if there is a command to redo, false if not
	 */
	public boolean canRedo() {
		return (currCommandIdx < commands.size() - 1);
	}
	
	/**
	 * Get the name of the current command that can be undone
	 * @return the name of the undo-able command, null if can't undo
	 */
	public String getNameOfUndoCommand() {
	    if (canUndo()) {
	        return commands.get(currCommandIdx).getName();
	    }
	    return null;
	}
	
	/**
	 * Get the name of the current command that can be redone
	 * @return the name of the redo-able command, null if can't redo
	 */
	public String getNameOfRedoCommand() {
	    if (canRedo()) {
	        return commands.get(currCommandIdx + 1).getName();
	    }
	    return null;
	}
}
