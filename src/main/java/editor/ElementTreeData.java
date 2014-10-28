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

package editor;

import utility.Observable;
import utility.Observer;
import xml.Element;

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
