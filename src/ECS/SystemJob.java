package ECS;

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
    
    //A reference to the EntityManager attached to the system.
    protected EntityManager entityManager;
    
    //Used to activate or deactate the system. if so wanted.
    protected boolean active = true;
    
    /**
     * Constructor of the SystemJob
     * All Systems must be attached to an EntityManager
     * @param entityManager the EntityManager to be attached to the system 
     * created.
     */
    public SystemJob(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    /**
     * Changes the EntityManager attached to this system.
     * @param entityManager the EntityManager to be attached to the system.
     */
    public void sethEntityManager(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    
    /**
     * Returns the EntityManager attached to the system.
     * @return EntityManager attached to the system.
     */
    public EntityManager getEntityManager(){
        return entityManager;
    }
    
    /*
      Methods to be overriden by the systems implemented =======================
      Used by the MainThread? to be executed each frame update
        //TODO: implement a System Manager to handle the collention of systems
    */
    
    /*
     * Code to be executed each update cycle (tick) of the mainThread
     */
    abstract public void update();
    
    /**
     * Code to be executed on a Fixed interval independent of frame rate,
     * eg. Fisics 
     */
    abstract public void fixedUpdate();
    
    /**
     * code to be executed just after the initialization of the Component 
     * instance. Executes once.
     */
    abstract public void init();
        
    /**
     * Code to be executed each render frame at time of render.
     */
    abstract public void render();
    
    /**
     * Code to be executed before the Entity or Component? (idk  ¯\_(ツ)_/¯) is 
     * disposed by the garbage collector. Executes once.
     */
    abstract public void dispose();
}
