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

public class MirrorElementCommand implements Command {
    private Element parent;
    private Element mirroredElement;
    private Element mirroringElement;
    private final String NAME = "Mirror Element";
    
    public MirrorElementCommand(Element elementToMirror) {
        parent = elementToMirror.getParent();
        mirroredElement = elementToMirror;
        mirroringElement = null;
    }

    @Override
    public void perform() {
        mirroringElement = new Element();
        mirroringElement.mirrorElement(mirroredElement);
        parent.addChild(mirroringElement);
    }

    @Override
    public void undo() {
        parent.deleteChild(mirroringElement);
        mirroringElement = null;
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
