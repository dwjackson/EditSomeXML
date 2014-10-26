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

import editor.views.AttributesPanelView;
import xml.Element;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This listener deals with attemps to delete an attribute
 */
public class DeleteAttributeListener implements ActionListener {
    private int attIndex;
    private Element elem;
    private AttributesPanelView view;

    public DeleteAttributeListener(int attributeIndex, Element element,
                                   AttributesPanelView attributesPanel) {
        attIndex = attributeIndex;
        elem = element;
        view = attributesPanel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String attName = elem.getAttributeName(attIndex);
        elem.removeAttribute(attName);
        view.resetAttributes();
        view.populate();
    }
}
