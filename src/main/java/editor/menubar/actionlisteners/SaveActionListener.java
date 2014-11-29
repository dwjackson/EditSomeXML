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
