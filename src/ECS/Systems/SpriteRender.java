package ECS.Systems;

import java.lang.System;
import ECS.*;
import ECS.Components.Sprite;
import java.awt.Graphics;
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
public class SpriteRender extends SystemJob{
    
    ArrayList<Entity> entities; //A List of references to each Eantity that also has the necesarie components
    
    //COMPONENTS
        Sprite sprite; //this Sprite only exist so that our System can use it's class name to search in the EntityManager
    //----------
    
    /**
     * Constructor of our system
     * Initializes all the components to be used.
     * Initializes the list of entities to be used.
     * @param entityManager 
     */
    public SpriteRender(EntityManager entityManager) {
        super(entityManager);
        sprite = new Sprite();
    }
    
    /**
     * Override to the Update function
     * 
     * This code is just an example that prints data from the one component.
     */
    @Override
    public void update() {
        entities = entityManager.getAllEntitiesPosessingComponentOfClass(sprite.getClass());
        for(Entity e : entities){
            sprite = entityManager.getEntityComponentFromClass(e, sprite.getClass());
            //entityManager.printEntities();
            //System.out.println(sprite.name);
        }     
    }  

    @Override
    public void fixedUpdate() {
    }

    @Override
    public void init() {
        entities = entityManager.getAllEntitiesPosessingComponentOfClass(sprite.getClass());
        /**if we had more components we would do
         * entities.addAll(entityManager.getAllEntitiesPosessingComponentOfClass(MyComponent.getClass()));
         */
        /**
         * Technically the code:
         * entities = entityManager.getAllEntitiesPosessingComponentOfClass(render.getClass());
         * because we shouldn't be reloading our components each frame. Only each time a component is deleted or added
         * //TODO: handle entity deletion and addition with messages between EntityManager and Systems (maybe SystemManager?)
         */
    }

    @Override
    public void render(Graphics g) {
        //System.out.println(entities.get(0));
        for(Entity e : entities){
            sprite = entityManager.getEntityComponentFromClass(e, sprite.getClass());
            g.drawImage(sprite.bi, 600, 600, sprite.width, sprite.width, null);
        }    
    }

    @Override
    public void dispose() {
    }
    
}
