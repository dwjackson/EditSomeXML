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
