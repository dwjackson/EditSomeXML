package editor.menubar.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import xml.Element;
import xml.ElementJSONSerializer;

public class SaveAsActionListener implements ActionListener {
    Element root;
    
    public SaveAsActionListener(Element root) {
        this.root = root;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame fileChooserFrame = new JFrame();
        JFileChooser fc = new JFileChooser();
        fc.showSaveDialog(fileChooserFrame);
        String fileName = fc.getSelectedFile().getAbsolutePath();
        ElementJSONSerializer ejs = new ElementJSONSerializer();
        ejs.serializeToFile(root, fileName);
    }

}
