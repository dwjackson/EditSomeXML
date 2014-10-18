package xml;

import utility.GenericObservable;
import utility.Observable;
import utility.Observer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

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
     * Get an exact (deep) copy of this element, but without children
     * @return a copy of this element
     */
    public Element cloneWithoutChildren() {
        Element cloneElem = new Element();
        cloneElem.setTag(tag);
        cloneElem.setText(text);
        String attVal;
        for (String attName : attributeNames()) {
            attVal = getAttribute(attName);
            cloneElem.setAttribute(attName, attVal);
        }
        cloneElem.setParent(parent);
        return cloneElem;
    }

    /**
     * Determine if this element is equivalent to another Element. This does
     * not take the element's children into account, just the actual element
     * itself.
     * @param obj The object to which to compare
     * @return true if the elements match, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == getClass()) {
            Element elem = (Element) obj;
            if (!(elem.getTag().equals(tag))) {
                System.out.println("[DEBUG] Element.equals() tags differ");
                return false;
            }

            if (text == null && elem.getText() != null) {
                System.out.println("[DEBUG] Element.equals() text differs");
                return false;
            } else if (text != null && elem.getText() == null) {
                System.out.println("[DEBUG] Element.equals() text differs");
                return false;
            } else if (text != null && elem.getText() != null) {
                if (!text.equals(elem.getText())) {
                    System.out.println("[DEBUG] Element.equals() text differs");
                    return false;
                }
            }

            String attVal;
            for (String attName : elem.attributeNames()) {
                attVal = getAttribute(attName);
                if (!(elem.getAttribute(attName).equals(attVal))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Determine if this element has the same parent as another element
     * @param elem The element to compare with
     * @return true if they are siblings, false if not
     */
    public boolean isSibling(Element elem) {
        if (parent == elem.getParent()) {
            return true;
        }
        return false;
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
     * Get this Elements' children as an Iterable
     * @see java.lang.Iterable
     * @return this Element's children as an Iterable
     */
    public Iterable<Element> children() {
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

    /**
     * Set this element's parent
     * @param parent The new parent to give this element
     */
    public void setParent(Element parent) {
        this.parent = parent;
    }

    /**
     * Get a Set of the attribute names
     * @see java.util.Set
     * @return a Set of the attribute names
     */
    public Set<String> attributeNames() {
        return attributes.keySet();
    }

    /**
     * Get the value of a particular attribute
     * @param key The attribute's name
     * @return The value of the attribute
     */
    public String getAttribute(String key) {
        return attributes.get(key);
    }

    /**
     * Set the value of an attribute
     * @param key The attribute's name
     * @param value The attribute's value/content
     */
    public void setAttribute(String key, String value) {
        attributes.put(key, value);
    }

    /**
     * Get the total number of attributes that the Element has
     * @return the number of attributes that the Element has
     */
    public int getNumberOfAttributes() {
        return attributes.size();
    }

    /**
     * Determine if this element has textual content
     * @return true if this element has assigned text, false if not
     */
    public boolean hasText() {
        return !(text == null);
    }

    /**
     * Get this Element's textual content
     * @return This element's text
     */
    public String getText() {
        return text;
    }

    /**
     * Set this element's textual content
     * @param text The text to assign to this element
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Add a child to this Element
     * @param child The child to add to this Element
     */
    public void addChild(Element child) {
        child.setParent(this);
        children.add(child);
    }

    /**
     * Determine the index of a child element
     * @param child The child Element whose index to search for
     * @return The index of the child if found, -1 in case of any error
     */
    public int getIndexOfChild(Element child) {
        int idx = -1;
        if (child != null) {
            for (int i = 0; i < children.size(); i++) {
                if (child.equals(children.get(i))) {
                    idx = i;
                }
            }
        }
        return idx;
    }

    /**
     * When converting an Element to a String, use the tag
     * @return the element's tag
     */
    @Override
    public String toString() {
        return tag;
    }

    /**
     * Add a sub-element (child) to this element and return the new Element
     * @param tag The child's tag
     * @return the new child element
     */
    public Element newSubElement(String tag) {
        Element child = new Element(tag);
        addChild(child);
        return child;
    }

    /**
     * Determine if this is the root element in a tree (i.e. it has no parent)
     * @return true if this is the root element, false if it's not
     */
    public boolean isRoot() {
        boolean rootStatus = false;
        if (parent == null) {
            rootStatus = true;
        }
        return rootStatus;
    }

    /**
     * Compare an entire tree to see if they are equivalent
     * @param elem The element in the other tree
     * @return true if the trees match, false otherwise
     */
    public boolean equalTree(Element elem) {
        if (!equals(elem)) {
            System.out.println("[DEBUG] equalTree(): Elements differ");
            System.out.println("[DEBUG] element 1: " + tag + ", Element 2: " + elem);
            return false;
        }

        // Verify that all the children are equal and at the correct indices
        Element child, compareChild;
        for (int i = 0; i < getNumberOfChildren(); i++) {
            child = getChildAt(i);
            compareChild = elem.getChildAt(i);
            if (!child.equalTree(compareChild)) {
                return false;
            }
        }
        return true;
    }
}
