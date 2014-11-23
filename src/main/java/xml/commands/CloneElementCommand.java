package xml.commands;

import utility.Command;
import xml.Element;

public class CloneElementCommand implements Command {
	Element parent;
	Element clonedElement;
	int numberOfClones;
	Element[] clones;
	
	public CloneElementCommand(Element parent, Element childToClone,
                               int numberOfClones) {
		this.parent = parent;
		clonedElement = childToClone.cloneWithoutChildren();
		this.numberOfClones = numberOfClones;
		
		clones = null;
	}

	@Override
	public void perform() {
		clones = new Element[numberOfClones];
		for (int i = 0; i < numberOfClones; i++) {
			clones[i] = clonedElement.cloneWithoutChildren();
			parent.addChild(clones[i]);
		}
	}

	@Override
	public void undo() {
		for (int i = 0; i < numberOfClones; i++) {
			parent.deleteChild(clones[i]);
		}
		clones = null;
	}

}
