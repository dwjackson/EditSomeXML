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

/**
 * The RenameAttributeCommand is used to make the renaming of attributes
 * undo-able and redo-able
 */
public class RenameAttributeCommand implements Command {
    private final String NAME = "Rename Attribute";

    private Element element;
    private String attributeName;
    private String oldAttributeName;
    private int attributeIndex;

    public RenameAttributeCommand(Element element, String attributeName,
                                  String oldAttributeName, int attributeIndex) {
        this.element = element;
        this.attributeIndex = attributeIndex;
        this.attributeName = attributeName;
        this.oldAttributeName = oldAttributeName;
    }

    @Override
    public void perform() {
        element.renameAttribute(attributeIndex, attributeName);
    }

    @Override
    public void undo() {
        element.renameAttribute(attributeIndex, oldAttributeName);
    }

    @Override
    public String getName() {
        return NAME;
    }

    public Element getElement() {
        return element;
    }

    public String getAttributeName() {
        return attributeName;
    }

    @Override
    public boolean canCombine(Command cmd) {
        if (cmd.getClass() != getClass()) {
            return false;
        }
        if (((RenameAttributeCommand)cmd).getElement() != element) {
            return false;
        }
        return true;
    }

    @Override
    public Command combine(Command cmd) {
        RenameAttributeCommand chainCommand;
        chainCommand = (RenameAttributeCommand) cmd;

        RenameAttributeCommand combinedCommand;
        combinedCommand = new RenameAttributeCommand(element,
                chainCommand.getAttributeName(), oldAttributeName,
                attributeIndex);
        return combinedCommand;
    }
}
