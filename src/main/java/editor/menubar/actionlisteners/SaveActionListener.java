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

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import xml.ElementJSONSerializer;
import editor.ElementTreeData;

public class SaveActionListener implements ActionListener {
    ElementTreeData data;
    private String fileName;
    
    public SaveActionListener(ElementTreeData data) {
        this.data = data;
        fileName = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (fileName == null || e.getActionCommand().equals("SAVE_AS")) {
            JFrame fileChooserFrame = new JFrame();
            JFileChooser fc = new JFileChooser();
            fc.showSaveDialog(fileChooserFrame);
            fileName = fc.getSelectedFile().getAbsolutePath();
        }
        ElementJSONSerializer ejs = new ElementJSONSerializer();
        ejs.serializeToFile(data.getRoot(), fileName);
    }

}
