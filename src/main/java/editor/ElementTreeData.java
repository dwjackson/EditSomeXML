/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (C) 2014-2016  David Jackson
 */

package editor;

import utility.Observable;
import utility.Observer;
import xml.Element;
import xml.ElementEvent;

import java.util.ArrayList;

/**
 * The ElementTreeData class is used to encapsulate a tree of Elements. It
 * contains the tree and observers of the tree. Primarily this class is used
 * to enable the ElementTreeModel to access the tree.
 */
public class ElementTreeData implements Observable {
    private Element root;
    private ArrayList<Observer> observers;

    /**
     * Initialize the tree data with no root and no observers
     */
    public ElementTreeData() {
        this(null);
    }

    /**
     * Initialize the tree data with a particular root element
     * @param elem The root element of the tree
     */
    public ElementTreeData(Element elem) {
        root = elem;
        observers = new ArrayList<Observer>();
    }

    /**
     * Generate a new root element for the tree
     * @return the new root element
     */
    public Element newRoot() {
        root = new Element();
        return root;
    }

    /**
     * Set the root of the tree to a particular element
     * @param elem the root element of the tree
     * @return the root element of the tree
     */
    public Element setRoot(Element elem) {
        root = elem;
        for (Observer observer : observers) {
            root.registerObserver(observer);
        }
        
        ElementEvent.EventType eventType = ElementEvent.EventType.NEW_ROOT;
        notifyObservers(new ElementEvent(eventType, root));
        
        return root;
    }

    /**
     * Get the root element of the tree
     * @return the root element of the tree
     */
    public Element getRoot() {
        return root;
    }

    /**
     * @see utility.Observable
     */
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
        root.registerObserver(observer);
    }

    /**
     * @see utility.Observable
     */
    @Override
    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
        root.unregisterObserver(observer);
    }

    /**
     * @see utility.Observable
     */
    @Override
    public void notifyObservers(Object obj) {
        root.notifyObservers(obj);
    }
}
