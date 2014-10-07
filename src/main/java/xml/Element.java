package xml;

import utility.Observable;
import utility.Observer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Element class is used to represent a node in an XML tree. It must have
 * a tag (name) which identifies it. It may optionally have textual content,
 * attributes (key/value pairs) and children (other elements).
 *
 * @see Observable
 * @see Observer
 */
public class Element implements Observable {
    private String tag;
    private String text;
    private HashMap<String, String> attributes;
    private ArrayList<Element> children;
    private ArrayList<Observer> observers;

    /**
     * Initialize an Element with no data in it. This element is invalid until
     * it has a tag.
     */
    public Element() {
        tag = null;
        text = null;
        attributes = new HashMap<String, String>();
        children = new ArrayList<Element>();
        observers = new ArrayList<Observer>();
    }

    /**
     * Create a new element, initialize it with a tag
     * @param tagName The name of the tag to give to this element
     */
    public Element(String tagName) {
        tag = tagName;
        text = null;
        attributes = new HashMap<String, String>();
        children = new ArrayList<Element>();
        observers = new ArrayList<Observer>();
    }

    /**
     * Get the element's tag
     * @return The element's tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * Set this element's tag, which serves as an identifier for this type of
     * element.
     * @param tag The tag to apply to this element
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * @see Observable
     */
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * @see Observable
     */
    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * @see Observable
     */
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.notifyObserver();
        }
    }
}
