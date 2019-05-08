package Signals;

import java.util.ArrayList;

/**
 * Signal to be received by a Listener.
 * The signal dispatches an event to a Listener with the method dispatch(), this
 * method sends an object to the listeners that this Signal has attached to it 
 * in the listeners list.
 * 
 * T is the type of the object to be sent to an event with the dispatch method.
 * This type must match with the type of the listener.
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 * @date 26/02/2019
 * @version 1.0
 */
public class Signal<T> {
    
    //List of references to the listener instances to which this signal will dispacth the events.
    public ArrayList<Listener<T>> listeners;
    
    public Signal () {
        listeners = new ArrayList<Listener<T>>();
    }

    /**
     * Add a Listener to this Signal
     * @param listener The Listener to be added
     */
    public void add (Listener<T> listener) {
            listeners.add(listener);
    }

    /**
     * Remove a listener from this Signal
     * @param listener The Listener to remove
     */
    public void remove (Listener<T> listener) {
        listeners.remove(listener);
    }

    /** Removes all listeners attached to this {@link Signal}. */
    public void removeAllListeners () {
            listeners.clear();
    }

    /**
     * Dispatches an event to all Listeners registered to this Signal
     * @param object The object to send off
     */
    public void dispatch (T object) {
        final Object[] items = listeners.toArray();
        for (int i = 0, n = listeners.size(); i < n; i++) {
                Listener<T> listener = (Listener<T>)items[i];
                listener.receive(this, object);
        }
    }
    
}
