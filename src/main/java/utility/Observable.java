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
