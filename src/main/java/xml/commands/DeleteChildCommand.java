package xml.commands;

import utility.Command;
import xml.Element;

public class DeleteChildCommand implements Command {
	private Element parent;
	private Element child;
	int childIndex;
	
	public DeleteChildCommand(Element parent, Element child) {
		this.parent = parent;
		this.child = child;
		childIndex = parent.getIndexOfChild(child);
	}

	@Override
	public void perform() {
		parent.deleteChild(child);
	}

	@Override
	public void undo() {
		parent.addChildAtIndex(child, childIndex);
	}

}
