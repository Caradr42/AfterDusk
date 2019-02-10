package ECS.Systems;

import java.lang.System;
import ECS.*;
import ECS.Components.RenderComponent;
import java.util.ArrayList;

/**
 * Example ECS System
 * 
 * the Game Manager is the most general system. 
 * 
 * @author Carlos Adrián Guerra Vázquez
 * @date 09/02/2019
 * @version 1.0
 */
public class GameManager extends SystemJob{
    
    ArrayList<Entity> entities; //A List of references to each Eantity that also has the necesarie components
    
    //COMPONENTS
        RenderComponent render; //this RenderComponent only exist so that our System can use it's class name to search in the EntityManager
    //----------
    
    /**
     * Constructor of our system
     * Initializes all the components to be used.
     * Initializes the list of entities to be used.
     * @param entityManager 
     */
    public GameManager(EntityManager entityManager) {
        super(entityManager);
        render = new RenderComponent("");
        
        entities = entityManager.getAllEntitiesPosessingComponentOfClass(render.getClass());
        /**if we had more components we would do
         * entities.addAll(entityManager.getAllEntitiesPosessingComponentOfClass(MyComponent.getClass()));
        */
    }
    
    /**
     * Override to the Update function
     * 
     * This code is just an example that prints data from the one component.
     */
    @Override
    public void update() {
        entities = entityManager.getAllEntitiesPosessingComponentOfClass(render.getClass());
        //System.out.println(entities.get(0));
        for(Entity e : entities){
            render = entityManager.getEntityComponentFromClass(e, render.getClass());
            entityManager.printEntities();
            System.out.println(render.test);
        }     
    }  

    @Override
    public void fixedUpdate() {
    }

    @Override
    public void init() {
        /**
         * Technically the code:
         * entities = entityManager.getAllEntitiesPosessingComponentOfClass(render.getClass());
         * must be here, as entities must be updated before each frame update in case one has added or deleted
         * //TODO: handle entity deletion and addition with messages between EntityManager and Systems (maybe SystemManager?)
         */
    }

    @Override
    public void render() {
    }

    @Override
    public void dispose() {
    }
    
}
