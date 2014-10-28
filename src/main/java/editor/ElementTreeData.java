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
 * The ElementTreeData class is used to encapsulate a tree of Elements
 */
public class ElementTreeData implements Observable {
    private Element root;
    private ArrayList<Observer> observers;

    public ElementTreeData() {
        root = null;
        observers = new ArrayList<Observer>();
    }

    public ElementTreeData(Element elem) {
        root = elem;
        observers = new ArrayList<Observer>();
    }

    public Element newRoot() {
        root = new Element();
        return root;
    }

    public Element setRoot(Element elem) {
        root = elem;
        for (Observer observer : observers) {
            root.registerObserver(observer);
        }
        return root;
    }

    public Element getRoot() {
        return root;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
        root.registerObserver(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
        root.unregisterObserver(observer);
    }

    @Override
    public void notifyObservers(Object obj) {
        root.notifyObservers(null);
    }
}
