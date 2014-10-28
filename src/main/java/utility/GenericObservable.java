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
            observer.notifyObserver(null);
        }
    }
}
