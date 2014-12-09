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
