package ECS;

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
public abstract class Component {
    
    /**
     * boolean to determine if the data contained in this component must be used
     */
    protected boolean active = true;
    
    /**
     * get the component 'active' value
     * @return if the Component is active
     */
    public boolean isActive() {
        return active;
    }
}
