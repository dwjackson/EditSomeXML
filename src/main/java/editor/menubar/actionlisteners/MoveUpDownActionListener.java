/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (c) 2014-2016 David Jackson
 */

package editor.menubar.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import xml.Element;
import editor.views.ElementTreeView;

public class MoveUpDownActionListener implements ActionListener {
    private final int NULL_INDEX = -1;
    private ElementTreeView view;
    
    public MoveUpDownActionListener(final ElementTreeView elementTreeView) {
        this.view = elementTreeView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Element elem = view.getSelectedElement();
        Element parent = elem.getParent();
        int originalIndex = parent.getIndexOfChild(elem);
        
        int newIndex;
        if (e.getActionCommand().equals("MOVE_UP")) {
            newIndex = originalIndex - 1;
        } else if (e.getActionCommand().equals("MOVE_DOWN")) {
            newIndex = originalIndex + 1;
        } else {
            newIndex = NULL_INDEX;
        }
        
        parent.moveChild(elem, newIndex);
    }

}
