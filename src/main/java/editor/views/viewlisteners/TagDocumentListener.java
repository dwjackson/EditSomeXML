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

package editor.views.viewlisteners;

import xml.Element;
import xml.commands.ChangeTagCommand;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The TagDocumentListener updates an element's tag when the user edits it
 */
public class TagDocumentListener extends ElementDocumentListener implements DocumentListener {
    public TagDocumentListener(Element element) {
        super(element);
    }

    private void updateElementTag(DocumentEvent documentEvent) {
        if (elem != null) {
            String str = getStringFromEvent(documentEvent);
            //elem.setTag(str); // TODO: Remove
            ChangeTagCommand cmd = new ChangeTagCommand(elem, str);
            elem.performCommand(cmd);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        updateElementTag(documentEvent);
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        updateElementTag(documentEvent);
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
        updateElementTag(documentEvent);
    }
}
