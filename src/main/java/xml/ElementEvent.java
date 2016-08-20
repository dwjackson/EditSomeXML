/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (c) 2014-2016  David Jackson
 */

package xml;

/**
 * This class is used to determine what has happened to an Element when it has
 * been changed
 */
public class ElementEvent {
    public enum EventType {
        NONE,
        ADD_CHILD,
        REMOVE_CHILD,
        DATA_CHANGE,
        NEW_ROOT,
        ATTRIBUTE_CHANGE,
        TEXT_CHANGE
    }
    
    private static final int NULL_INDEX = -1;

    private EventType eventType;
    private Element parent;
    private Element child;
    int childIndex;

    /**
     * Initialize the element event by specifying its type and the element to
     * which this event applies.
     * @param eventType The type of event
     * @param element The element to which this event applies
     */
    public ElementEvent(EventType eventType, Element element) {
        this(eventType, element, null, NULL_INDEX);
    }

    /**
     * Initialize this element event by supplying the type of event, the parent
     * of the element to which this event applies, the child element to which
     * this event applies, and the index of the child element to which this
     * event applies.
     * @param eventType The type of the event
     * @param parent The parent of the element to which this event applies
     * @param child The element to which this event applies
     * @param childIndex The index of the element to which this event applies
     */
    public ElementEvent(EventType eventType, Element parent, Element child,
                        int childIndex) {
    	this.eventType = eventType;
    	this.parent = parent;
    	this.child = child;
    	this.childIndex = childIndex;
    }

    /**
     * Get the type of this event
     * @return the event type
     */
    public EventType getEventType() {
        return eventType;
    }
    
    /**
     * Get the element associated with this event
     * @return the element associated with this event
     */
    public Element getElement() {
    	return parent;
    }

    /**
     * Get the parent of the element to which this event applies
     * @return the parent of the element to which this event applies
     */
    public Element getParent() {
    	return parent;
    }

    /**
     * Get the child element to which this event applies
     * @return the child element to which this event applies
     */
    public Element getChild() {
    	return child;
    }

    /**
     * Get the index of the child element to which this event applies
     * @return The index of the child element to which this event applies
     */
    public int getChildIndex() {
    	return childIndex;
    }
}
