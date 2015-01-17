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
import xml.Element.RepresentationType;

public class ChangeRepresentationCommand implements Command {
    private Element element;
    private Element.RepresentationType representationType;
    private String attributeName;
    private boolean setForAll;
    private final String NAME = "Change Representation";

    public ChangeRepresentationCommand(Element element,
            RepresentationType representationType, String attributeName,
            boolean setForAll) {
        super();
        this.element = element;
        this.representationType = representationType;
        this.attributeName = attributeName;
        this.setForAll = setForAll;
    }

    /**
     * Change the element(s)'s representation
     */
    @Override
    public void perform() {
        Element start;
        if (!setForAll) {
            start = element;
        } else {
            start = element.getRoot();
        }
        
        switch(representationType) {
        case TAG:
            start.setRepresentationToTag(setForAll);
            break;
        case ATTRIBUTE_VALUE:
            start.setRepresentationToAttributeValue(attributeName, setForAll, start.getTag());
            break;
        default:
            // Do nothing
        }
    }

    /**
     * Reset the representation to be the element's tag
     */
    @Override
    public void undo() {
        Element start;
        if (!setForAll) {
            start = element;
        } else {
            start = element.getRoot();
        }
        start.setRepresentationToTag(setForAll);
    }

    public String getName() {
        return NAME;
    }

    @Override
    public boolean canCombine(Command cmd) {
        return false;
    }

    @Override
    public Command combine(Command cmd) {
        return null;
    }
}
