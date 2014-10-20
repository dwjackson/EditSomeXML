package editor.menubar;

import editor.ElementTreeData;
import xml.Element;
import xml.ElementXMLDeserializer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ImportActionListener is used to deal with the user action of importing
 * an XML file to use as the element tree
 */
public class ImportActionListener implements ActionListener {
    private ElementTreeData data;

    public ImportActionListener(ElementTreeData data) {
        this.data = data;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFrame f = new JFrame();
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(f);
        String fileName = fc.getSelectedFile().getAbsolutePath();
        ElementXMLDeserializer deserializer = new ElementXMLDeserializer();
        Element elem = deserializer.deserializeFromFile(fileName);
        System.out.println("[DEBUG] imported root.tag = " + elem);
        data.setRoot(elem);
        data.notifyObservers();
    }
}
