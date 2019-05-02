/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import ECS.Components.Enemy;
import ECS.Components.Inventory;
import ECS.Components.Playable;
import ECS.Components.Player;
import ECS.Components.Sprite;
import ECS.Components.Tool;
import ECS.Components.Transform;
import ECS.SystemJob;
import Maths.Vector2;
import Maths.Vector3;
import Scene.Scene;
import static java.lang.Math.abs;
import java.util.ArrayList;

/**
 *
 * @author pablo
 */
public class EnemySystem extends SystemJob{

    ArrayList<Integer> arrPlayable;
    Integer player;
    
    Player player1;
    Playable playable;
    Transform playerPos;
    Sprite playerSprite;
    Enemy enemy;
    
    Sprite sprite;
    Transform transform;
    
    //If this margin is passed, the enemy will move diagonally
    //int marginDistance = 56;
    int minDistance = 60;
    int maxDistance = 200;
    
    public EnemySystem(Scene scene, boolean active) {
        super(scene, active);
        arrPlayable = new ArrayList<>();
        player1 = new Player();
        playable = new Playable();
        playerPos = new Transform();
        sprite = new Sprite();
        transform = new Transform();
        playerSprite  =new Sprite();
    }

    @Override
    public void update() {
        
        for(Integer entity : entities) {
            //Each entity should follow the player 
            updateEntityPosition(entity);
            /*
            playable = scene.entityManager.getEntityComponentInstance(entity, Playable.class);
            sprite = scene.entityManager.getEntityComponentInstance(entity, Sprite.class);
            transform = scene.entityManager.getEntityComponentInstance(entity, Transform.class);
            if(sprite.name.equals("enemy")){
                System.out.println(sprite.name +  " Z: " + transform.position.z + "\t renderedY: " + transform._renderedY);
            }*/
            
            
        }
       
    }

    @Override
    public void init() {
 
        player = scene.entityManager.getEntitiesWithComponents(player1.getClass()).get(0);
        
        entities = new ArrayList<>();
        
        entities = scene.entityManager.getEntitiesWithComponents(playable.getClass(), Enemy.class);
        
        //Getting the transform of the player
        playerPos = scene.entityManager.getEntityComponentInstance(player, Transform.class);
        playerSprite = scene.entityManager.getEntityComponentInstance(player, Sprite.class);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    private void updateEntityPosition(Integer entity) {
        transform = scene.entityManager.getEntityComponentInstance(entity, Transform.class);
        sprite = scene.entityManager.getEntityComponentInstance(entity, Sprite.class);
        playable = scene.entityManager.getEntityComponentInstance(entity, Playable.class);
        enemy = scene.entityManager.getEntityComponentInstance(entity, Enemy.class);

        if(playable.isAlive) {
            double distance = abs(playerPos._renderedPosition.toVector2().add(playerSprite.dimensions.div(2)).dist(transform._renderedPosition.toVector2().add(sprite.dimensions.div(2))));
            if (distance < maxDistance && distance > minDistance) {
                //System.out.println(distance);
                Vector2 direction = playerPos._renderedPosition.toVector2().add(playerSprite.dimensions.div(2)).sub(transform._renderedPosition.toVector2().add(sprite.dimensions.div(2))).norm().scalar(playable.speedScalar);
                //System.out.println(direction.x + " : " + direction.y);

                enemy.prev = direction;

                transform.position.set(transform.position.add(direction));

                if (abs(direction.x) > abs(direction.y)) {
                    if (direction.x < 0) {
                        playable.left = true;
                        playable.up = false;
                        playable.right = false;
                        playable.down = false;

                        sprite.animation = sprite.animations.get(3).first;
                        sprite.animationLenght = sprite.animations.get(3).second;
                    } else {
                        playable.left = false;
                        playable.up = false;
                        playable.right = true;
                        playable.down = false;
                        sprite.animation = sprite.animations.get(4).first;
                        sprite.animationLenght = sprite.animations.get(4).second;
                    }
                } else {
                    if (direction.y < 0) {
                        playable.left = false;
                        playable.up = true;
                        playable.right = false;
                        playable.down = false;
                        sprite.animation = sprite.animations.get(2).first;
                        sprite.animationLenght = sprite.animations.get(2).second;
                    } else {
                        playable.left = false;
                        playable.up = false;
                        playable.right = false;
                        playable.down = true;
                        sprite.animation = sprite.animations.get(1).first;
                        sprite.animationLenght = sprite.animations.get(1).second;
                    }
                }
            }

            //else if the enemy does not move
            else {
                sprite.animation = sprite.animations.get(0).first;
                sprite.animationLenght = sprite.animations.get(0).second;
                
                if(distance < maxDistance) {
                    //System.out.println("ATTACK OF THE ENEMY " + distance);
                    
                    //weapon of the enemy
                    Integer weapon = scene.entityManager.getEntityComponentInstance(playable.inventory, Inventory.class).slots.get(0);
                
                    //attack
                    Tool tool = scene.entityManager.getEntityComponentInstance(weapon, Tool.class);
                    
                    //with a basic attack
                    tool.currentActive = 0;
                }
            }



        }
    }
    
}
