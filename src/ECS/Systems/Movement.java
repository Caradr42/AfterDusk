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
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import maths.Vector2;
import proyecto_videojuegos.MainThread;

/**
 *
 * @author carlo
 */
public class Movement extends SystemJob{
    
    //private ArrayList<Integer> entities; //A List of references to each Eantity that also has the necesarie components
    Random rng;
    Transform transform;  
    boolean removed = false;
    
    public Movement(Scene scene) {
        super(scene);
        entities = new ArrayList<>();
        transform = new Transform();
        rng = new Random(System.currentTimeMillis());
    }

    @Override
    public void update() {
        //entities = scene.entityManager.getEntitiesWithComponents(transform.getClass());
        
        for(Integer e : entities){
            transform = scene.entityManager.getEntityComponentInstance(e, transform.getClass());
                        
            if(scene.display.getKeyManager().right){
                transform.position.x = transform.position.x +2;//+ 100 * MainThread.deltaTime);
            }
            
            if(scene.display.getKeyManager().left){
                transform.position.x = transform.position.x -2;//- 100 * MainThread.deltaTime);
            }
            
            if(scene.display.getKeyManager().up){
                transform.position.y = transform.position.y -2;//- 100 * MainThread.deltaTime);
            }
            
            if(scene.display.getKeyManager().down){
                transform.position.y = transform.position.y +2;//+ 100 * MainThread.deltaTime);
            }
            
            if(scene.display.getKeyManager().keys[KeyEvent.VK_SPACE] && !removed){
                scene.entityManager.removeEntity(e);     
                removed = true;
            }
            
            //System.out.println("ID: " +  e + " Pos: (" + transform.position.getX() + ", " + transform.position.getY() + ")");
        }
                        
        removed = false;
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
