package editor.menubar.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import xml.Element;
import xml.ElementEvent;
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
        // TODO: Move the following two lines into the data.setRoot() method
        ElementEvent.EventType eventType = ElementEvent.EventType.NEW_ROOT;
        data.notifyObservers(new ElementEvent(eventType, root));
    }

}
