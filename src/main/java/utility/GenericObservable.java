package utility;

import java.util.ArrayList;

/**
 * The GenericObservable class is used to concretize a generic implementation
 * of the Observable interface that can be included as a member of any other
 * object that implements the interface.
 */
public class GenericObservable implements Observable {
    private ArrayList<Observer> observers;

    /**
     * Initialize the GenericObservable with an empty list of observers
     */
    public GenericObservable() {
        observers = new ArrayList<Observer>();
    }

    /**
     * @see Observable
     * @param observer The observer to notify when this object changes
     */
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * @see Observable
     * @param observer The observer to un-register
     */
    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * @see Observable
     */
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.notifyObserver();
        }
    }
}
