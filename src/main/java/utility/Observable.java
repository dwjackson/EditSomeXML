package utility;

/**
 * Observable objects are watched for changed by Observers
 * @see Observer
 */
public interface Observable {
    /**
     * Register an observer for this observable object. The observer will be
     * notified when this object changes.
     * @param observer The observer to notify when this object changes
     */
    void registerObserver(Observer observer);

    /**
     * Un-register an observer. This observer will no longer be notified if
     * this object changes.
     * @param observer The observer to un-register
     */
    void unregisterObserver(Observer observer);

    /**
     * Notify all obserevrs that this object has changed.
     */
    void notifyObservers();
}
