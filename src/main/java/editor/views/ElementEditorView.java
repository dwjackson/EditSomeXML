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

package editor.views;

import editor.views.viewlisteners.ElementDocumentListener;
import editor.views.viewlisteners.TagDocumentListener;
import editor.views.viewlisteners.TextDocumentListener;
import utility.Observer;
import xml.Element;
import xml.ElementEvent;

import javax.swing.*;
import javax.swing.text.Document;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * The ElementEditorView is used to edit the currently-selected element.
 */
public class ElementEditorView extends JPanel implements Observer {
    private JPanel tagPanel;
    private JTextField tagField;
    private AttributesPanelView attributesPanel;
    private JPanel textPanel;
    private JTextArea elementTextArea;
    private Element elem;
    private ArrayList<ElementDocumentListener> documentListeners;
    private JLabel mirrorLabel;
    private boolean observing;

    /**
     * Create the ElementEditorView and leave all of its fields blank
     */
    public ElementEditorView() {
        elem = null;
        observing = false;
        documentListeners = new ArrayList<ElementDocumentListener>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Tag
        tagPanel = new JPanel();
        tagPanel.add(new JLabel("Tag"));
        tagField = new JTextField("", 20);
        ClassLoader classLoader = getClass().getClassLoader();
        File fontFile;
        String fileName = "Inconsolata.ttf";
        try {
            InputStream inputStream = classLoader.getResourceAsStream(fileName);
            Font inconsolata = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            tagField.setFont(inconsolata.deriveFont(14f));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
        TagDocumentListener tagDocumentListener;
        tagDocumentListener = new TagDocumentListener(elem, this);
        documentListeners.add(tagDocumentListener);
        tagField.getDocument().addDocumentListener(tagDocumentListener);
        tagPanel.add(tagField);
        add(tagPanel);

        // Attributes
        attributesPanel = new AttributesPanelView(elem, this);
        add(attributesPanel);

        // Text
        textPanel = new JPanel();
        textPanel.add(new JLabel("Text"));
        JScrollPane textAreaScrollPane = new JScrollPane();
        elementTextArea = new JTextArea();
        elementTextArea.setRows(4);
        elementTextArea.setColumns(40);
        textAreaScrollPane.setViewportView(elementTextArea);
        textPanel.add(textAreaScrollPane);
        TextDocumentListener textDocumentListener;
        textDocumentListener = new TextDocumentListener(elem, this);
        documentListeners.add(textDocumentListener);
        Document doc = elementTextArea.getDocument();
        doc.addDocumentListener(textDocumentListener);
        add(textPanel);
        
        // Is this element mirroring another element? If so, can't edit it
        mirrorLabel = new JLabel("Element is mirroring and is not editable");

        setVisible(true);
    }

    /**
     * Populate this view with the data of a particular element
     * @param element The element with whose data to populate this view
     */
    public void populateWithElementData(Element element) {
        if (elem != null) {
            depopulateAllData();
        }

        elem = element;
        elem.registerObserver(this);
        observing = true;

        attributesPanel.setElement(elem);

        tagField.setText(elem.getTag());

        elementTextArea.setText(elem.getText());
        
        if (elem.isMirroring()) {
            add(mirrorLabel);
            mirrorLabel.setVisible(true);
        } else {
            enableAllFields();
            mirrorLabel.setVisible(false);
        }

        for (ElementDocumentListener listener : documentListeners) {
            listener.setElement(elem);
        }
    }

    /**
     * Remove all data from this view
     */
    public void depopulateAllData() {
        elem.unregisterObserver(this);
        elem = null;
        attributesPanel.unsetElement();
        for (ElementDocumentListener listener : documentListeners) {
            listener.unsetElement();
        }
        tagField.setText("");
        attributesPanel.resetAttributes();
        elementTextArea.setText("");
        disableAllFields();
    }
    
    /**
     * Disable every field in the editor
     */
    public void disableAllFields() {
        tagPanel.setEnabled(false);
        for (Component child : tagPanel.getComponents()) {
            child.setEnabled(false);
        }
        
        attributesPanel.setEnabled(false);
        for (Component child : attributesPanel.getComponents()) {
            child.setEnabled(false);
        }
        
        textPanel.setEnabled(false);
        for (Component child : textPanel.getComponents()) {
            child.setEnabled(false);
        }
    }
    
    /**
     * Enable every field in the editor
     */
    public void enableAllFields() {
        tagPanel.setEnabled(true);
        for (Component child : tagPanel.getComponents()) {
            child.setEnabled(true);
        }
        
        attributesPanel.setEnabled(true);
        for (Component child : attributesPanel.getComponents()) {
            child.setEnabled(true);
        }
        
        textPanel.setEnabled(true);
        for (Component child : textPanel.getComponents()) {
            child.setEnabled(true);
        }
    }

    /**
     * Stop observing the element
     *
     * This is used when the editor is modifying the element, thus preventing
     * a circular reference (editor modifies element modifies editor modifies
     * element...)
     */
    public void stopObserving() {
        observing = false;
    }

    /**
     * Start observing the element again when editing the element is complete
     */
    public void startObserving() {
        observing = true;
    }
    /**
     * The editor has to change to reflect changes in the elements when they
     * are changed by something other than the editor itself
     * @param obj An object passed by the Observable
     */
    @Override
    public void notifyObserver(Object obj) {
        ElementEvent ev = (ElementEvent) obj;
        Element elem = ev.getElement();
        if (observing) {
            populateWithElementData(elem);
        }
    }
}
