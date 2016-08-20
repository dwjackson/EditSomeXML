/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/* 
 * Copyright (c) 2014-2016  David Jackson
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
