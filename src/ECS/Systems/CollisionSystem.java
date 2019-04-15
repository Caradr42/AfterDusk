/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import ECS.Components.Collidable;
import ECS.Components.Item;
import ECS.Components.Playable;
import ECS.Components.Transform;
import ECS.Entity;
import ECS.SystemJob;
import Scene.Scene;
import java.util.ArrayList;

/**
 *
 * @author pablo
 */
public class CollisionSystem extends SystemJob{
    
    //An item must have a collidable assigned by reference
    private Item item;
    
    //A playable must have a collidable assigned by reference
    private Playable playable;
    
    
    
    private ArrayList<Integer> arrPlayables;
    
    private ArrayList<Integer> arrItems;
    
    //private ArrayList<Integer> arr;

    public CollisionSystem(Scene scene) {
        super(scene);
        
        item = new Item();
        playable = new Playable();
        arrItems = new ArrayList<>();
        arrPlayables = new ArrayList<>();
        //arr = new ArrayList<>();
    }
    
    @Override
    public void update() {
        
        //For each entity
        for(int i = 0; i < entities.size(); i++) {
            
            //check all the entities
            for(int j = i; j < entities.size(); j++) {
                
                //Except itself
                if( i != j) {
                    makeCollision(i, j);
                }
            }
        }
    }

    @Override
    public void init() {
        //fetching the entities with the playable component
        arrItems = scene.entityManager.getEntitiesWithComponents(item.getClass());
        
        //Fetching the entities with the item component
        arrPlayables = scene.entityManager.getEntitiesWithComponents(playable.getClass());
        
        for(int i = 0; i < arrItems.size(); i++) {
            entities.add(arrItems.get(i));
        }
        
        for(int i = 0; i < arrPlayables.size(); i++) {
            entities.add(arrPlayables.get(i));
        }
    }

    @Override
    public void onCreate() {
        
    }

    @Override
    public void onDestroy() {
        
    }
    
    public void makeCollision(int i, int j) {
        Transform transformi = new Transform();
        Collidable collidablei = new Collidable();
        
        Transform transformj = new Transform();
        Collidable collidablej = new Collidable();
        
        //boolean to know if there is the first entity collides being at the right of b
        Boolean bRight;
        
        //boolean to know if there is the first entity collides being at the right of b
        Boolean bLeft;
        
        //boolean to know if there is the first entity collides being at the right of b
        Boolean bRight;
        
        //boolean to know if there is the first entity collides being at the right of b
        Boolean bRight;
        
        //Getting th collidable and the tranform of the first entity
        Entity e = scene.entityManager.getEntityByID(i);
        collidablei = scene.entityManager.getEntityComponentInstance(i, collidablei.getClass());
        transformi = scene.entityManager.getEntityComponentInstance(i, transformi.getClass());
        
        //Getting the collidable and the transform of the second entity
        Entity e2 = scene.entityManager.getEntityByID(j);
        collidablei = scene.entityManager.getEntityComponentInstance(i, collidablej.getClass());
        transformi = scene.entityManager.getEntityComponentInstance(i, transformj.getClass());
        
        //The center of the first object(x, y, z)
        double firstCenterX = transformi.position.x + collidablei.hitbox.x / 2;
        double firstCenterY = transformi.position.y + collidablei.hitbox.y / 2;
        double firstCenterZ;
        
        //The length of the first object in the x axis from the center to the border
        double firstLengthX = collidablei.hitbox.x / 2;
        
        //The length of the first object in the y axis from the center to the border
        double firstLengthY = collidablei.hitbox.y / 2;

        
        //The length of the first object in the z axis from the center to the border
        double firstLengthZ;
        
        //The center of the second object(x, y, z)
        double secondCenterX = transformj.position.x + collidablej.hitbox.x / 2;
        double secondCenterY = transformj.position.y + collidablej.hitbox.y / 2;
        double secondCenterZ;

        
        //The length of the second object in the x axis from the center to the border
        double secondLengthX = collidablej.hitbox.x / 2;
        
        //The length of the second object in the y axis from the center to the border
        double secondLengthY = collidablej.hitbox.y / 2;

        
        //The length of the second object in the z axis from the center to the border
        double secondLengthZ;
        
        
        
    }
    
}
