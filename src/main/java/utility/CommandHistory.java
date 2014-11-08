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
		boolean currCommandIsMostRecent = (numCmds == currCommandIdx - 1);
		
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
		if (commands.size() > 0) {
			Command cmd = commands.get(currCommandIdx);
			cmd.undo();
			currCommandIdx--;
		}
	}
	
	/**
	 * Redo the most recently undone command
	 */
	public void redo() {
		int numCmds = commands.size();
		boolean currCommandIsMostRecent = (numCmds == currCommandIdx - 1);
		if (!currCommandIsMostRecent) {
			currCommandIdx++;
			Command cmd = commands.get(currCommandIdx);
			cmd.perform();
		}
	}
}
