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
