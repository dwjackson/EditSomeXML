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
 * Observer objects are always notified when Observable objects change.
 * @see Observable
 */
public interface Observer {
    /**
     * Notify the observer that the Observable object has changed.
     * @param obj An object passed by the Observable
     */
    void notifyObserver(Object obj);
}
