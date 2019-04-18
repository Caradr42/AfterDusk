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
import Utility.Point;
import java.awt.Rectangle;
import static java.lang.Integer.min;
import static java.lang.Math.abs;
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
       
        //arr = new ArrayList<>();
    }
    
    @Override
    public void update() {
        
        //System.out.println("update");
        initializeEntities();
        
        
        /*for(int i = 0; i < entities.size(); i++) {
            System.out.println(scene.entityManager.getEntityByID(entities.get(i)).getName());
        }*/
        
        //For each entity
        for(int i = 0; i < entities.size(); i++) {
            
            //check all the entities
            for(int j = i; j < entities.size(); j++) {
                
                //Except itself
                if( i != j) {
                    //System.out.println(scene.entityManager.getEntityByID(entities.get(i)).getName());
                    //System.out.println(scene.entityManager.getEntityByID(entities.get(j)).getName());
                    //System.out.println(i + ", " + j);
                    //System.out.println("size: " + entities.size());
                    //System.out.println("");
                    makeCollision(entities.get(i), entities.get(j));
                    
                }
            }
        }
        
    }

    @Override
    public void init() {

        
        initializeEntities();
    }

    @Override
    public void onCreate() {
        
    }

    @Override
    public void onDestroy() {
        
    }
    
    /**
     * Check if entity i is to the left/right/up/down of entity j
     * @param i
     * @param j 
     */
    public void makeCollision(int i, int j) {

        Transform transformi = new Transform();
        Collidable collidablei = new Collidable();
        
        Transform transformj = new Transform();
        Collidable collidablej = new Collidable();
        
        //Getting th collidable and the tranform of the first entity
        Entity e = scene.entityManager.getEntityByID(i);
        collidablei = scene.entityManager.getEntityComponentInstance(i, collidablei.getClass());
        transformi = scene.entityManager.getEntityComponentInstance(i, transformi.getClass());
        
        //Getting the collidable and the transform of the second entity
        Entity e2 = scene.entityManager.getEntityByID(j);
        collidablej = scene.entityManager.getEntityComponentInstance(j, collidablej.getClass());
        transformj = scene.entityManager.getEntityComponentInstance(j, transformj.getClass());

        //Rectangle of the first entity
        Rectangle firstRect = new Rectangle((int)transformi.position.x, (int)transformi.position.y, (int)collidablei.hitbox.x, (int)collidablei.hitbox.y);
        
        //Rectangle of the second entity
        Rectangle secondRect = new Rectangle((int)transformj.position.x, (int)transformj.position.y, (int)collidablej.hitbox.x, (int)collidablej.hitbox.y);

        //The center of the first object(x, y, z)
        double firstCenterX = transformi.position.x + collidablei.hitbox.x / 2;
;
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

        if (firstRect.intersects(secondRect)) {

            //Point of the upper left corner of the first object
            Point firstPoint1 = new Point(firstCenterX - firstLengthX, firstCenterY - firstLengthY);

            //Point of the upper right corner of the first object
            Point firstPoint2 = new Point(firstCenterX + firstLengthX, firstCenterY - firstLengthY);

            //Point of the bottom left corner of the first object
            Point firstPoint3 = new Point(firstCenterX - firstLengthX, firstCenterY + firstLengthY);

            //Point of the bottom right corner of the first object
            Point firstPoint4 = new Point(firstCenterX + firstLengthX, firstCenterY + firstLengthY);

            //Point of the upper left corner of the first object
            Point secondPoint1 = new Point(secondCenterX - secondLengthX, secondCenterY - secondLengthY);

            //Point of the upper right corner of the first object
            Point secondPoint2 = new Point(secondCenterX + secondLengthX, secondCenterY - secondLengthY);

            //Point of the bottom left corner of the first object
            Point secondPoint3 = new Point(secondCenterX - secondLengthX, secondCenterY + secondLengthY);

            //Point of the bottom right corner of the first object
            Point secondPoint4 = new Point(secondCenterX + secondLengthX, secondCenterY + secondLengthY);
            
            /*
                Distance in the x axis between the ideally closest pair of points of both entities.
                Assuming that the first entity is to the left of the second*/
            int A = abs((int) (secondPoint1.x - firstPoint2.x));
            
            
            /*
                Distance in the x axis between the ideally closest pair of points of both entities.
                Assuming that the first entity is to the right of the second*/
            int B = abs((int) (firstPoint1.x - secondPoint2.x));
            
            /*
                Distance in the y axis between the ideally closest pair of points of both entities.
                Assuming that the first entity is above the second*/              
            int C = abs((int) (secondPoint1.y - firstPoint3.y));
            
            /*
                Distance in the y axis between the ideally closest pair of points of both entities.
                Assuming that the first entity is below the second*/
            int D = abs((int) (firstPoint1.y - secondPoint3.y));

            //entity1 collides in the left border of entity2
            if (A < B && A <= min(C, D)) {
                System.out.println("collision left");
            } //if entity1 collides in the right border of entity2
            else if (B < A && B <= min(C, D)) {
                System.out.println("collision right");
            }
            
            //entity1 collides in the upper limit of entity2
            else if( C < D) {
                System.out.println("collision up");
            }
            
            //else if( D < C) (e1 collides in the bottom limit of e2)
            else {
                System.out.println("collision down");
            }
            System.out.println(e.getName() + ": " + transformi.position.x + ", " + transformi.position.y);
            System.out.println(e2.getName() + ": " + transformj.position.x + ", " + transformj.position.y);
            System.out.println("");
        }
    }
    
    public void initializeEntities() {
        item = new Item();
        playable = new Playable();
        arrItems = new ArrayList<>();
        arrPlayables = new ArrayList<>();
        entities = new ArrayList<>();
        
        //fetching the entities with the playable component
        arrItems = scene.entityManager.getEntitiesWithComponents(item.getClass());
        
        //Fetching the entities with the item component
        arrPlayables = scene.entityManager.getEntitiesWithComponents(playable.getClass());
        
        for(int i = 0; i < arrItems.size(); i++) {
            //System.out.println(arrItems.get(i));
            entities.add(arrItems.get(i));
            //System.out.println(scene.entityManager.getEntityByID(arrItems.get(i)).getName());
        }
        
        for(int i = 0; i < arrPlayables.size(); i++) {
            entities.add(arrPlayables.get(i));
            //System.out.println(arrPlayables.get(i));
            //System.out.println(scene.entityManager.getEntityByID(arrPlayables.get(i)).getName());
        }
        

        
        //System.out.println("size: " + entities.size());
    }
    
}
