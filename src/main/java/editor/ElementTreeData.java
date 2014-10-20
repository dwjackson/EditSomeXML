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
    public void notifyObservers() {
        root.notifyObservers();
    }
}
