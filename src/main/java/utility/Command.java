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
}
