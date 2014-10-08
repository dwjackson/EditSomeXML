package xml;

import utility.GenericObservable;
import utility.Observable;
import utility.Observer;

import java.util.ArrayList;
import java.util.Collection;
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
    private Element parent;

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
        parent = null;
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
        parent = null;
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

    /**
     * Return this element's children as a collection
     * @see java.util.Collection
     * @return This element's children, as a Collection
     */
    public Collection<Element> getChildren() {
        return children;
    }

    /**
     * Determine if this element has children
     * @return true if this element has childre, false if it doesn't
     */
    public boolean hasChildren() {
        if (children.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Get the child at a particular index. Child elements have an order:
     * those added earlier will precede those added later.
     * @param index The index of the child
     * @return The child Element if it's found, null if it isn't
     */
    public Element getChildAt(int index) {
        Element child = null;
        if (index >= 0 && index < children.size()) {
            child = children.get(index);
        }
        return child;
    }

    /**
     * Determine the number of children that this Element has
     * @return the number of children that this Element has
     */
    public int getNumberOfChildren() {
        return children.size();
    }

    /**
     * Get the index of a known child
     * @param elem The child element to look for
     * @return the index of the child if it's found, -1 if not
     */
    public int getChildIndex(Element elem) {
        int index = -1;
        if (children.contains(elem)) {
            index = children.indexOf(elem);
        }
        return index;
    }

    /**
     * Get this Element's parent element, if it exists
     * @return This Element's parent or null if it doesn't have one
     */
    public Element getParent() {
        return parent;
    }
}
