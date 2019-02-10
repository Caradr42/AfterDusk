///Component Based Architecture
package CBA;

/**
 * Component interface.
 * Component is an interface required for the Component based Architecture.
 * In CBA it can also be known as 'Behabiour' and as such all <b>GameObjects</b>
 * are composed of various objects that implements <b>Component</b>.
 * All objects implementing component will Override the init(), Update(), 
 * render(), and dispose(), methods.
 * This methods are executed by the game object that composes the Component and 
 * as such, they are executed in the main thread.
 * 
 * @author Carlos Adrián Guerra Vázquez
 * @date 09/02/2019
 * @version 1.0
 */
public interface  Component {
    /**
     * code to be executed just after the initialization of the Component 
     * instance. Executes once.
     */
    public void init();
    
    /**
     * Code to be executed each update cycle (tick) of the mainThread
     */
    public void update();
    
    /**
     * Code to be executed each render frame at time of render.
     */
    public void render();
    
    /**
     * Code to be executed before the Component object is disposed by the 
     * garbage collector. Executes once.
     */
    public void dispose();
}
