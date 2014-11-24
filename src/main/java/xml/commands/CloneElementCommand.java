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
