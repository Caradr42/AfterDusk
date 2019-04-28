package ECS;

import Scene.Scene;
import Signals.Listener;
import Signals.Signal;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Entity Component System class.
 * The SystemJob class allows for the creation of  Systems.
 * Systems provide the functionality to entities.
 * Systems work with the data of specific components and as such we have a 
 * different system for different collections of components.
 * It is usually recommended to create systems and components that facilitate
 * their reutilization.
 * 
 * A system must use an EntityManager attached in order to have access to a collection of
 * Entities and their components.
 * 
 * @author Carlos Adrián Guerra Vázquez
 * @date 09/02/2019
 * @version 1.0
 */
public abstract class SystemJob {
    
    protected ArrayList<Integer> entities; //A List of references to each Eantity that also has the necesarie components
    public static Scene scene; //Scene to which this System is attached and where it is executed
    protected boolean active = true; //Used to activate or deactate the system. if so wanted.
    
    //anonimous class that executes receive, every time it receives a signal with some entities to remove from the entities list.
    public Listener<ArrayList<Integer>> onRemoveEntitiesListener = new Listener<ArrayList<Integer>>(){
        @Override
        public void receive(Signal<ArrayList<Integer>> signal, ArrayList<Integer> entitiesToRemove) {
            System.out.println("Removed: " + entitiesToRemove.size() + " \tLeft: " + entities.size());
            entities.removeAll(entitiesToRemove);   
        }        
    };
    
    public Listener<ArrayList<Integer>> onAddEntitesListener = new Listener<ArrayList<Integer>>(){
        @Override
        public void receive(Signal<ArrayList<Integer>> signal, ArrayList<Integer> entitiesToAdd) {
            entities.addAll(entitiesToAdd);
        }        
    };
    
    /**
     * Constructor of the SystemJob
     * All Systems must be attached to an EntityManager
     * @param scene the scene to be attached to the system created.
     */
    public SystemJob(Scene scene) {
        this.scene = scene;
    }
      
    /*
     * Code to be executed each update cycle (tick) of the mainThread
     */
    abstract public void update();
        
    /**
     * code to be executed just after the initialization of the scene. Executes once.
     */
    abstract public void init();
        
    /**
     * Code to be executed each render frame at time of render.
     * @param g
     */
    public void render(Graphics2D g){
    }
    
    /**
     * code to be executed just after the initialization of the entity. Executes once.
     */
    abstract public void onCreate();
    
    /**
     * Code to be executed before the Entity or Component(?) (idk  ¯\_(ツ)_/¯) 
     * is disposed by the garbage collector. Executes once.
     */
    abstract public void onDestroy();

    public boolean isActive() {
        return active;
    }
    
    
}
