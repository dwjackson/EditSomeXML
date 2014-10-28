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

    public ElementEvent(EventType eventType) {
        this.eventType = eventType;
    }

    public EventType getEventType() {
        return eventType;
    }
}
