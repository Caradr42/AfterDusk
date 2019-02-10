///Component Based Architecture
package CBA;

import java.util.ArrayList;

/**
 * GameObject Class
 *
 * Class required for the implementation of a CBA system. 
 * GameObject is composed of several <b>Components</b> <i>'Behabiour'</i>.
 * The Game Objects contains an ArrayList of components which  are the 
 * that will execute their <b>Overriden</b> Methods at runtime in the mainThread.
 * The Game object Update method must also be executed in the main thread.
 * 
 * @author Carlos Adrián Guerra Vázquez
 * @date 09/02/2019
 * @version 1.0
 */
public abstract class GameObject {
    
    //List of components to be attached to this GameObject
    protected ArrayList<? super Component> components;
    
    /**
     * Game Object constructor.
     * initializes the list of components
     */
    public GameObject() {
        this.components = new ArrayList<>();
    }
    
    /**
     * adds a component to the list of components
     * @param comp the <b>Component</b> to be added. Exact reference
     */
    public void addComponent(Component comp){
        components.add(comp);
    }
    
    /**
     * Executes the init() method for all the attached components
     */
    public void init(){
        for(int i = 0; i < components.size(); i++){
            ((Component)components.get(i)).init();
        }
    }
    
    /**
     * Executes the Update() method for all the attached components
     */
    public void Update(){
        for(int i = 0; i < components.size(); i++){
            ((Component)components.get(i)).update();
        }
    }
    
    /**
     * Executes the render() method for all the attached components
     */
    public void render(){
        for(int i = 0; i < components.size(); i++){
            ((Component)components.get(i)).render();
        }
    }
    
    /**
     * Executes the dispose() method for all the attached components
     */
    public void dispose(){
        for(int i = 0; i < components.size(); i++){
            ((Component)components.get(i)).dispose();
        }
    }
}
