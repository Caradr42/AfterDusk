package ECS.Systems;

import java.lang.System;
import ECS.*;
import ECS.Components.Sprite;
import ECS.Components.Transform;
import Scene.Scene;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

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
    
    //private ArrayList<Integer> entities; //A List of references to each Eantity that also has the necesarie components
    //private HashMap<Integer, ? extends Component> spriteMap;
    //private HashMap<Integer, ? extends Component> transformMap;
    //private ArrayList<Component> componentGroup;
    
    //COMPONENTS
    Sprite sprite; //this Sprite only exist so that our System can use it's class name to search in the EntityManager
    Transform transform;
    //----------
    
    /**
     * Constructor of our system
     * Initializes all the components to be used.
     * Initializes the list of entities to be used.
     * @param scene 
     */
    public SpriteRender(Scene scene) {
        super(scene);
        sprite = new Sprite();
        transform = new Transform();
    }
    
    /**
     * Override to the Update function
     * 
     * This code is just an example that prints data from the one component.
     */
    @Override
    public void update() {
       
    }  

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(sprite.getClass(), transform.getClass());
        //spriteMap = scene.entityManager.getComponentMap(sprite.getClass());
        //transformMap = scene.entityManager.getComponentMap(transform.getClass());
        
        /**
         * Technically we shouldn't be reloading our components each frame. Only each time a component is deleted or added
         * //TODO: handle entity deletion and addition with messages between EntityManager and Systems (maybe SystemManager?)
         */
    }

    @Override
    public void render(Graphics2D g) {
        //System.out.println(entities.get(0));
        for(Integer e : entities){
            sprite = scene.entityManager.getEntityComponentInstance(e, sprite.getClass());
            transform = scene.entityManager.getEntityComponentInstance(e, transform.getClass());
            
            //not render outside of camera
            /*if(transform.position.x + sprite.width >= scene.c.getPosition().x  
                    && transform.position.x <= scene.c.getPosition().x + scene.display.getWidth() 
                    && transform.position.y + sprite.height >= scene.c.getPosition().y 
                    && transform.position.y <= scene.c.getPosition().y + scene.display.getHeight()){*/
            
                g.drawImage(sprite.bi, (int)transform.position.x,  (int)transform.position.y, sprite.width, sprite.height, null);
            //} 
        }
    }
    
    @Override 
    public void onCreate(){
        
    }

    @Override
    public void onDestroy() {
    }
    
}
