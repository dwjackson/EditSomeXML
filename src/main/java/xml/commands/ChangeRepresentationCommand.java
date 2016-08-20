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
        case TEXT_VALUE:
            start.setRepresentationToText();
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
