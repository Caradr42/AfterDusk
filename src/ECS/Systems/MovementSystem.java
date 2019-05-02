/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import ECS.Components.Collidable;
import ECS.Components.Inventory;
import ECS.Components.Item;
import ECS.Components.Movement;
import ECS.Components.Playable;
import ECS.Components.Player;
import ECS.Components.Sprite;
import ECS.Components.Transform;
import ECS.SystemJob;
import static ECS.SystemJob.scene;
import Maths.Vector3;
import Scene.Scene;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author pepe_
 */

public class MovementSystem extends SystemJob{
    Transform transform;
    Movement movement;
    Player player;
    Sprite sprite;
    Playable playable;
    
    int leftBorder = 80 - 32;
    int rightBorder = 80;
    int upperBorder = 50;
    int downBorder = 50 - 32;
    
    public MovementSystem(Scene scene, boolean active) {
        super(scene, active);
        entities = new ArrayList<>();
        transform = new Transform();
        player = new Player();
        sprite = new Sprite();
        movement = new Movement();
        //rightHand = new Tool();
        playable = new Playable();
    }
    
    
    
    @Override
    public void update() {
        Collidable collision = new Collidable();
        for (Integer e : entities) {
            player = scene.entityManager.getEntityComponentInstance(e, player.getClass());
            transform = scene.entityManager.getEntityComponentInstance(e, transform.getClass());
            sprite = scene.entityManager.getEntityComponentInstance(e, sprite.getClass());
            playable = scene.entityManager.getEntityComponentInstance(e, playable.getClass());
            movement = scene.entityManager.getEntityComponentInstance(e, movement.getClass());
            
            collision= scene.entityManager.getEntityComponentInstance(e, collision.getClass());
            boolean CollisionCheck=false;
            //Check if is empty the HashSet with the ids of collision bw entity-tiles
            
            if(!collision.setCollidable.isEmpty()){
                //System.out.println(collision.setCollidable);
                CollisionCheck=true;
            }
            
            Vector3 v3=new Vector3();
            v3=movement.velocity;
            
            
            //Check collision
            if(CollisionCheck){
                v3.x=(v3.x*-.5);
                v3.y=(v3.y*-.5);
                if(collision.collisionLeft){
                    transform.position.x = transform.position.x  - 1;
                }
                if(collision.collisionRight){
                    transform.position.x = transform.position.x  + 1;
                }
                if(collision.collisionTop){
                    transform.position.y=transform.position.y-1;
                }
                if(collision.collisionDown){
                    transform.position.y=transform.position.y+1;
                }
                Assets.Assets.collisionWood.play();
            }
            
            transform.position.set(transform.position.add(v3));
        }
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(transform.getClass(), player.getClass(), sprite.getClass());
    }

    @Override
    public void onCreate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onDestroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
