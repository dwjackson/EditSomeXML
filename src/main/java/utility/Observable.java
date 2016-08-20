/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * Copyright (c) 2014-2016  David Jackson
 */

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
     * Notify all observers that this object has changed.
     * @param obj An object to pass to the observers
     */
    void notifyObservers(Object obj);
}
