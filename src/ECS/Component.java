package ECS;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Entity Component System class.
 * A component is an encapsulation of data and as such it only contain data and
 * the means to initialize the data. No other methods are allowed in the objects 
 * that extend component.
 * 
 * //To do: make component data Serializable
 * 
 * @author Carlos Adrián Guerra Vázquez
 * @date 09/02/2019
 * @version 1.0
 */
public abstract class Component implements Serializable{
   // private static HashSet<Class> componentExtendors;//all the Instantiated classes that extend component

    //boolean to determine if the data contained in this component must be used
    protected boolean active = true;
    
    /**
     * Returns the active atribute of the component. 
     * @return if the component is active
     */
    public boolean isActive(){
        return active;
    }
}
