/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import ECS.Components.Transform;
import ECS.Entity;
import ECS.SystemJob;
import Scene.Scene;
import java.util.ArrayList;
import java.util.Random;
import maths.Vector2;

/**
 *
 * @author carlo
 */
public class Movement extends SystemJob{
    
    private ArrayList<Integer> entities; //A List of references to each Eantity that also has the necesarie components
    Random rng;
    
    Transform transform;  
    
    public Movement(Scene scene) {
        super(scene);
        entities = new ArrayList<>();
        transform = new Transform();
        rng = new Random(System.currentTimeMillis());
    }

    @Override
    public void update() {
        entities = scene.entityManager.getEntitiesWithComponents(transform.getClass());
        
        for(Integer e : entities){
            transform = scene.entityManager.getEntityComponentInstance(e, transform.getClass());
            
            //transform.position.add(new Vector2(rng.nextInt(5),rng.nextInt(5)));
            //transform.position.setX(transform.position.getX() + rng.nextInt(5));
           // transform.position.setY(transform.position.getY() + rng.nextInt(5));
            
            if(scene.display.getKeyManager().right){
                transform.position.setX(transform.position.getX() + rng.nextInt(15));
            }
            
            if(scene.display.getKeyManager().left){
                transform.position.setX(transform.position.getX() - rng.nextInt(15));
            }
            
            if(scene.display.getKeyManager().up){
                transform.position.setY(transform.position.getY() - rng.nextInt(15));
            }
            
            if(scene.display.getKeyManager().down){
                transform.position.setY(transform.position.getY() + rng.nextInt(15));
            }
            //System.out.println("ID: " +  e + " Pos: (" + transform.position.getX() + ", " + transform.position.getY() + ")");
        }
    }

    @Override
    public void fixedUpdate() {
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(transform.getClass());
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
}
