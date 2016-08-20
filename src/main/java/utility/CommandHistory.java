/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (c) 2014-2016  David Jackson
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
	private void deleteHistoryToEndFromNext() {
		int numCommands = commands.size();
		for (int i = currCommandIdx + 1; i < numCommands; i++) {
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
		} else {
			Command mostRecentCommand = commands.get(currCommandIdx);
			if (cmd.canCombine(mostRecentCommand)) {
				if (!currCommandIsMostRecent) {
					deleteHistoryToEndFromNext();
				}
				Command combinedCommand = mostRecentCommand.combine(cmd);
				undo();
				commands.remove(currCommandIdx + 1);
				combinedCommand.perform();
				commands.add(combinedCommand);
				currCommandIdx = commands.indexOf(combinedCommand);
			} else if (currCommandIsMostRecent) {
				cmd.perform();
				commands.add(cmd);
				currCommandIdx = commands.indexOf(cmd);
			} else {
				deleteHistoryToEndFromNext();
				assert (commands.size() == currCommandIdx + 1);
				cmd.perform();
				commands.add(cmd);
				currCommandIdx++;
			}
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
