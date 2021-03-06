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

import xml.Element;
import xml.ElementJSONDeserializer;
import editor.ElementTreeData;

public class OpenActionListener implements ActionListener {
    private ElementTreeData data;
    
    public OpenActionListener(ElementTreeData data) {
        this.data = data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Element root = null;
        String fileName = null;
        
        JFrame openFrame = new JFrame();
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(openFrame);
        fileName = fc.getSelectedFile().getAbsolutePath();
        
        ElementJSONDeserializer deserializer;
        deserializer = new ElementJSONDeserializer();
        root = deserializer.deserializeFromFile(fileName);
        
        data.setRoot(root);
    }

}
