package xml;

/**
 * This class is used to determine what has happened to an Element when it has
 * been changed
 */
public class ElementEvent {
    public enum EventType {
        NONE, ADD_CHILD, REMOVE_CHILD, DATA_CHANGE, NEW_ROOT
    }

    private EventType eventType;
    private Element element;

    public ElementEvent(EventType eventType, Element element) {
        this.eventType = eventType;
        this.element = element;
    }

    public EventType getEventType() {
        return eventType;
    }
    
    public Element getElement() {
    	return element;
    }
}
