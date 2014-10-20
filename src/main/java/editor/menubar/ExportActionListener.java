package editor.menubar;

import xml.Element;
import xml.ElementXMLSerializer;

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
        String fileName = "tree.xml";
        System.out.printf("[DEBUG] writing file: %s\n", fileName);
        exs.serializeToFile(root, fileName);
    }
}
