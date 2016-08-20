/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (c) 2014-2016  David Jackson
 */

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
    public void notifyObservers(Object obj) {
        for (Observer observer : observers) {
            observer.notifyObserver(obj);
        }
    }
}
