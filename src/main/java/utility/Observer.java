package utility;

/**
 * Observer objects are always notified when Observable objects change.
 * @see Observable
 */
public interface Observer {
    /**
     * Notify the observer that the Observable object has changed.
     */
    void notifyObserver();
}
