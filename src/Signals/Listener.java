package Signals;

/**
 * Interface to implement when creating a signal listener. 
 * A listener is a class that is usually used as a singleton inside other class,
 * the listener executes the method receive() when it receives a signal. The 
 * signal carries with it a reference to the signal object that sent it and an 
 * object that carries the information of that signal.
 * 
 * T is the type of the object to receive. This type must match with the Signal
 * that sends the event that this listener will receive.
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 * @date 26/02/2019
 * @version 1.0
 */
public interface Listener<T> {
    /**
     * Method to be executed when the listener receives a signal.
     * 
     * @param signal The Signal object that triggered the event
     * @param object The object passed on dispatch, it carries the information
     * of the signal.
     */
    public void receive (Signal<T> signal, T object);
}
