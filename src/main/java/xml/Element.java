package xml;

import utility.GenericObservable;
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
 * @see GenericObservable
 */
public class Element implements Observable {
    private String tag;
    private String text;
    private HashMap<String, String> attributes;
    private ArrayList<Element> children;
    private GenericObservable observable;

    /**
     * Initialize an Element with no data in it. This element is invalid until
     * it has a tag.
     */
    public Element() {
        tag = null;
        text = null;
        attributes = new HashMap<String, String>();
        children = new ArrayList<Element>();
        observable = new GenericObservable();
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
        observable = new GenericObservable();
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
        observable.registerObserver(observer);
    }

    /**
     * @see Observable
     */
    public void unregisterObserver(Observer observer) {
        observable.unregisterObserver(observer);
    }

    /**
     * @see Observable
     */
    public void notifyObservers() {
        observable.notifyObservers();
    }
}
