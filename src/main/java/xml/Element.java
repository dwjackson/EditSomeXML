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

package xml;

import java.util.ArrayList;
import java.util.Collection;

import utility.GenericObservable;
import utility.Observable;
import utility.Observer;

/**
 * The Element class is used to represent a node in an XML tree. It must have
 * a tag (name) which identifies it. It may optionally have textual content,
 * attributes (key/value pairs) and children (other elements).
 *
 * @see Observable
 * @see GenericObservable
 */
public class Element extends GenericObservable implements Observable, Observer {
	public enum RepresentationType {
		NONE, TAG, ATTRIBUTE_VALUE
	}
	
    private String tag;
    private String text;
    private ElementAttributes attributes;
    private ArrayList<Element> children;
    private GenericObservable observable;
    private Element parent;
    private Element mirroredElement;
    private RepresentationType representationType;
    private String representationAttribute;

    /**
     * Initialize an Element with no data in it. This element is invalid until
     * it has a tag.
     */
    public Element() {
        super();
        tag = null;
        text = null;
        attributes = new ElementAttributes();
        children = new ArrayList<Element>();
        observable = new GenericObservable();
        parent = null;
        mirroredElement = null;
        representationType = RepresentationType.NONE;
        representationAttribute = null;
    }

    /**
     * Create a new element, initialize it with a tag
     * @param tagName The name of the tag to give to this element
     */
    public Element(String tagName) {
        this();
        representationType = RepresentationType.TAG;
        setTag(tagName);
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
        if (obj == null) {
            return false;
        }

        if (obj.getClass() == getClass()) {
            Element elem = (Element) obj;
            if (elem.getTag() == null || !(elem.getTag().equals(tag))) {
                return false;
            }

            if (text == null && elem.getText() != null) {
                return false;
            } else if (text != null && elem.getText() == null) {
                return false;
            } else if (text != null) {
                if (!text.equals(elem.getText())) {
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
        return parent == elem.getParent();
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
        if (tag != null) {
            this.tag = tag.replace(' ', '-');
            if (representationType == RepresentationType.NONE) {
            	representationType = RepresentationType.TAG;
            }
            ElementEvent.EventType eventType;
            eventType = ElementEvent.EventType.DATA_CHANGE;
            notifyObservers(new ElementEvent(eventType, this));
        }
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
    public void notifyObservers(Object obj) {
        observable.notifyObservers(obj);
        if (parent != null) {
            parent.notifyObservers(obj);
        }
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
     * Get this element's youngest (most-recently-added) child
     * @return the youngest child or null if none
     */
    public Element getYoungestChild() {
        Element child = null;
        int idx = children.size() - 1;
        if (idx >= 0) {
            child = children.get(idx);
        }
        return child;
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
    public Iterable<String> attributeNames() {
        return attributes.names();
    }

    /**
     * Get the value of a particular attribute
     * @param key The attribute's name
     * @return The value of the attribute
     */
    public String getAttribute(String key) {
        return attributes.getValue(key);
    }

    /**
     * Get an attribute's name from its index
     * @param index The attribute's index
     * @return the attribute's name
     */
    public String getAttributeName(int index) {
        return attributes.getName(index);
    }

    /**
     * Set the value of an attribute
     * @param key The attribute's name
     * @param value The attribute's value/content
     */
    public void setAttribute(String key, String value) {
        attributes.set(key, value);
        ElementEvent.EventType eventType;
        eventType = ElementEvent.EventType.DATA_CHANGE;
        notifyObservers(new ElementEvent(eventType, this));
    }

    /**
     * Set an attribute's name and value, based on the index of that attribute
     * @param index The attribute's index
     * @param key The attribute's name
     * @param value The attribute's value/content
     */
    public void setAttribute(int index, String key, String value) {
        attributes.setAttribute(index, key, value);
        ElementEvent.EventType eventType;
        eventType = ElementEvent.EventType.DATA_CHANGE;
        notifyObservers(new ElementEvent(eventType, this));
    }

    /**
     * Remove an attribute from this element
     * @param key The attribute's key
     */
    public void removeAttribute(String key) {
        attributes.remove(key);
    }

    /**
     * Rename an existing attribute
     * @param oldKey The current/old key for the attribute
     * @param newKey The new key for the attribute
     */
    public void renameAttribute(String oldKey, String newKey) {
        attributes.rename(oldKey, newKey);
    }

    /**
     * Rename an attribute based on its index
     * @param index The attribute's index
     * @param newKey The attribute's new name
     */
    public void renameAttribute(int index, String newKey) {
        attributes.rename(index, newKey);
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
        if (text != null && text.trim().length() > 0) {
            this.text = text;
            ElementEvent.EventType eventType;
            eventType = ElementEvent.EventType.DATA_CHANGE;
            notifyObservers(new ElementEvent(eventType, this));
        }
    }

    /**
     * Add a child to this Element
     * @param child The child to add to this Element
     */
    public void addChild(Element child) {
        child.setParent(this);
        children.add(child);
        ElementEvent.EventType eventType;
        eventType = ElementEvent.EventType.ADD_CHILD;
        notifyObservers(new ElementEvent(eventType, this));
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
    	String representation;
    	switch(representationType) {
    	case TAG:
    		representation = tag;
    		break;
    	case ATTRIBUTE_VALUE:
    		representation = attributes.getValue(representationAttribute);
    		break;
    	case NONE:
    	default:
    		representation = null;
    	}
        return representation;
    }

    /**
     * Add a sub-element (child) to this element and return the new Element
     * @param tag The child's tag
     * @return the new child element
     */
    public Element newSubElement(String tag) {
        Element child = new Element(tag);
        addChild(child);
        ElementEvent.EventType eventType;
        eventType = ElementEvent.EventType.ADD_CHILD;
        notifyObservers(new ElementEvent(eventType, this));
        return child;
    }

    /**
     * Determine if this is the root element in a tree (i.e. it has no parent)
     * @return true if this is the root element, false if it's not
     */
    public boolean isRoot() {
    	return (parent == null);
    }

    /**
     * Compare an entire tree to see if they are equivalent
     * @param elem The element in the other tree
     * @return true if the trees match, false otherwise
     */
    public boolean equalTree(Element elem) {
        if (!equals(elem)) {
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

    /**
     * Delete a child from this element
     * @param child The child to delete
     */
    public void deleteChild(Element child) {
        if (children.contains(child)) {
            children.remove(child);
            ElementEvent.EventType eventType;
            eventType = ElementEvent.EventType.REMOVE_CHILD;
            notifyObservers(new ElementEvent(eventType, this));
        }
    }

    /**
     * Delete every subelement of this element (the entire subtree is removed)
     */
    public void deleteSubelements() {
        if (getNumberOfChildren() > 0) {
            for (int i = 0; i < children.size(); i++) {
                Element child = children.get(i);
                child.deleteSubelements();
                deleteChild(child);
            }
        }
    }

    /**
     * When an element is mirroring another element, this method is called
     * when the data of the mirrored element changes and requires that the
     * mirroring element to change its own data.
     */
    protected void updateFromMirror() {
        tag = mirroredElement.getTag();

        int numOfAtts = getNumberOfAttributes();
        int numMirroredAtts = mirroredElement.getNumberOfAttributes();
        if (numOfAtts < numMirroredAtts) {
            for (int i = 0; i < numOfAtts; i++) {
                String attName = mirroredElement.getAttributeName(i);
                String attVal = mirroredElement.getAttribute(attName);
                setAttribute(i, attName, attVal);
            }

            for (int i = numOfAtts; i < numMirroredAtts; i++) {
                String attName = mirroredElement.getAttributeName(i);
                String attVal = mirroredElement.getAttribute(attName);
                setAttribute(attName, attVal);
            }
        } else if (numOfAtts == numMirroredAtts) {
            for (int i = numOfAtts; i < numMirroredAtts; i++) {
                String attName = mirroredElement.getAttributeName(i);
                String attVal = mirroredElement.getAttribute(attName);
                setAttribute(attName, attVal);
            }
        } else {
            for (int i = 0; i < numMirroredAtts; i++) {
                String attName = mirroredElement.getAttributeName(i);
                String attVal = mirroredElement.getAttribute(attName);
                setAttribute(i, attName, attVal);
            }
            for (int i = numMirroredAtts; i < numOfAtts; i++) {
                attributes.remove(i);
            }
        }

        text = mirroredElement.getText();

        int numOfChildren = getNumberOfChildren();
        int numOfMirroredChildren = mirroredElement.getNumberOfChildren();
        if (numOfChildren < numOfMirroredChildren) {
            Element newestChild = mirroredElement.getYoungestChild();
            Element child = new Element();
            child.mirrorElement(newestChild);
            addChild(child);
        } else if (numOfChildren > numOfMirroredChildren) {
            int childIndex = numOfChildren - 1;
            children.remove(childIndex);
        }
    }

    /**
     * Mirror another element. When the mirrored element changes, this element
     * will change so that it has the same content as the mirrored element.
     * @param element The element to mirror
     */
    public void mirrorElement(Element element) {
        if (mirroredElement == null) {
            mirroredElement = element;
            element.registerObserver(this);
            for (Element mirroredChild : element.children()) {
                Element child = new Element();
                child.mirrorElement(mirroredChild);
                addChild(child);
            }
            updateFromMirror();
        }
    }

    /**
     * @see utility.Observer
     */
    @Override
    public void notifyObserver(Object obj) {
        if (mirroredElement != null) {
            updateFromMirror();
        }
    }
    
    /**
     * Get an array of Elements that starts at the root of the tree and goes
     * down from parent-to-child until this element.
     * @return the ancestry of this element, as an array of elements
     */
    public Element[] getAncestry() {
    	int numAncestors = 1;
    	Element currElem = this;
    	while ((currElem = currElem.getParent()) != null) {
    		numAncestors++;
    	}
    	currElem = this;
    	Element[] ancestors = new Element[numAncestors];
    	for (int i = numAncestors - 1; i >= 0; i--) {
    		ancestors[i] = currElem;
    		currElem = currElem.getParent();
    	}
    	return ancestors;
    }
    
    /**
     * Move a child of this element to a new section of the tree
     * @param childToMove The child element to move
     * @param newParent the new parent of the child element
     */
    public void moveElement(Element childToMove, Element newParent) {
    	if (children.contains(childToMove) && newParent != null) {
    		children.remove(childToMove);
    		newParent.addChild(childToMove);
    	}
    }

    /**
     * Change the representation of this element to the value of an attribute
     * of this element
     * @param attName The name of the attribute to use as this element's
     *                textual representation
     * @param setForChildren true if should set all children with the same tag
     *                       to have the same type of representation
     */
    public void setRepresentationToAttributeValue(String attName,
    	boolean setForChildren) {
	    if (attName != null && attributes.contains(attName)) {
	        representationType = RepresentationType.ATTRIBUTE_VALUE;
	        representationAttribute = attName;
	    }
    	if (setForChildren) {
    		for (Element child : children) {
    			child.setRepresentationToAttributeValue(attName, true);
    		}
    	}
    }

    /**
     * Set the textual representation of this element to be its tag
     * @param setForChildren true if should set all children with the same tag
     *                       to have the same type of representation
     */
    public void setRepresentationToTag(boolean setForChildren) {
    	representationType = RepresentationType.TAG;
    	if (setForChildren) {
    		for (Element child : children) {
    			child.setRepresentationToTag(true);
    		}
    	}
    }
    
    /**
     * Given any arbitrary element in the tree, return the root of that tree
     * @return the root element of the tree of which this element is a part
     */
    public Element getRoot() {
    	Element elem = this;
    	while (!elem.isRoot()) {
    		elem = elem.getParent();
    	}
    	return elem;
    }
}
