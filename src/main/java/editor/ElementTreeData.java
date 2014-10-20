package editor;

import utility.Observable;
import utility.Observer;
import xml.Element;

/**
 * The ElementTreeData class is used to encapsulate a tree of Elements
 */
public class ElementTreeData implements Observable {
    private Element root;

    public ElementTreeData() {
        root = null;
    }

    public ElementTreeData(Element elem) {
        root = elem;
    }

    public Element newRoot() {
        root = new Element();
        return root;
    }

    public Element setRoot(Element elem) {
        root = elem;
        return root;
    }

    public Element getRoot() {
        return root;
    }

    @Override
    public void registerObserver(Observer observer) {
        root.registerObserver(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        root.unregisterObserver(observer);
    }

    @Override
    public void notifyObservers() {
        root.notifyObservers();
    }
}
