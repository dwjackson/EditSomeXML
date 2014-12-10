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

/**
 * This class is used to determine what has happened to an Element when it has
 * been changed
 */
public class ElementEvent {
    public enum EventType {
        NONE, ADD_CHILD, REMOVE_CHILD, DATA_CHANGE, NEW_ROOT
    }
    
    private static final int NULL_INDEX = -1;

    private EventType eventType;
    private Element parent;
    private Element child;
    int childIndex;

    public ElementEvent(EventType eventType, Element parent) {
        this(eventType, parent, null, NULL_INDEX);
    }
    
    public ElementEvent(EventType eventType, Element parent, Element child,
                        int childIndex) {
    	this.eventType = eventType;
    	this.parent = parent;
    	this.child = child;
    	this.childIndex = childIndex;
    }

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
    
    public Element getParent() {
    	return parent;
    }
    
    public Element getChild() {
    	return child;
    }
    
    public int getChildIndex() {
    	return childIndex;
    }
}
