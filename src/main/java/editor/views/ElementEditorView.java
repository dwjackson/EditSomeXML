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
import xml.Element;

import javax.swing.*;
import javax.swing.text.Document;
import java.util.ArrayList;

/**
 * The ElementEditorView is used to edit the currently-selected element.
 */
public class ElementEditorView extends JPanel {
    private JTextField tagField;
    private AttributesPanelView attributesPanel;
    private JTextArea elementTextArea;
    private Element elem;
    private ArrayList<ElementDocumentListener> documentListeners;

    /**
     * Create the ElementEditorView and leave all of its fields blank
     */
    public ElementEditorView() {
        elem = null;
        documentListeners = new ArrayList<ElementDocumentListener>();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Tag
        JPanel tagPanel = new JPanel();
        tagPanel.add(new JLabel("Tag"));
        tagField = new JTextField("", 20);
        TagDocumentListener tagDocumentListener;
        tagDocumentListener = new TagDocumentListener(elem);
        documentListeners.add(tagDocumentListener);
        tagField.getDocument().addDocumentListener(tagDocumentListener);
        tagPanel.add(tagField);
        add(tagPanel);

        // Attributes
        attributesPanel = new AttributesPanelView(elem);
        add(attributesPanel);

        // Text
        JPanel textPanel = new JPanel();
        textPanel.add(new JLabel("Text"));
        JScrollPane textAreaScrollPane = new JScrollPane();
        elementTextArea = new JTextArea();
        elementTextArea.setRows(4);
        elementTextArea.setColumns(40);
        textAreaScrollPane.setViewportView(elementTextArea);
        textPanel.add(textAreaScrollPane);
        TextDocumentListener textDocumentListener;
        textDocumentListener = new TextDocumentListener(elem);
        documentListeners.add(textDocumentListener);
        Document doc = elementTextArea.getDocument();
        doc.addDocumentListener(textDocumentListener);
        add(textPanel);

        setVisible(true);
    }

    /**
     * Populate this view with the data of a particular element
     * @param element The element with whose data to populate this view
     */
    public void populateWithElementData(Element element) {
        depopulateAllData();
        elem = element;
        attributesPanel.setElement(elem);
        for (ElementDocumentListener listener : documentListeners) {
            listener.setElement(elem);
        }
        tagField.setText(elem.getTag());
        elementTextArea.setText(elem.getText());
    }

    /**
     * Remove all data from this view
     */
    public void depopulateAllData() {
        elem = null;
        attributesPanel.unsetElement();
        for (ElementDocumentListener listener : documentListeners) {
            listener.unsetElement();
        }
        tagField.setText("");
        attributesPanel.resetAttributes();
        elementTextArea.setText("");
    }
}
