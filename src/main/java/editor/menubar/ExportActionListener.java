package editor.menubar;

import xml.Element;
import xml.ElementXMLSerializer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ExportActionListener is used to deal with the user action of exporting
 * an element tree to XML
 */
public class ExportActionListener implements ActionListener {
    private Element root;

    public ExportActionListener(Element root) {
        this.root = root;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ElementXMLSerializer exs = new ElementXMLSerializer();
        JFrame exportFileChooserFrame = new JFrame();
        JFileChooser fc = new JFileChooser();
        fc.showSaveDialog(exportFileChooserFrame);
        String fileName = fc.getSelectedFile().getAbsolutePath();
        System.out.printf("[DEBUG] writing file: \"%s\"\n", fileName);
        exs.serializeToFile(root, fileName);
    }
}
