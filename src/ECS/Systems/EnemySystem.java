/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import ECS.Components.Playable;
import ECS.Components.Player;
import ECS.Components.Sprite;
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
    Sprite sprite;
    
    //If this margin is passed, the enemy will move diagonally
    int marginDistance = 56;
    
    public EnemySystem(Scene scene, boolean active) {
        super(scene, active);
        arrPlayable = new ArrayList<>();
        player1 = new Player();
        playable = new Playable();
        playerPos = new Transform();
        sprite = new Sprite();
    }

    @Override
    public void update() {
        
        for(Integer entity : entities) {
            //Each entity should follow the player 
            updateEntityPosition(entity);
            
            
        }
       
    }

    @Override
    public void init() {
 
        player = scene.entityManager.getEntitiesWithComponents(player1.getClass()).get(0);
        
        entities = new ArrayList<>();
        
        entities = scene.entityManager.getEntitiesWithComponents(playable.getClass());
        
        //Getting the transform of the player
        playerPos = scene.entityManager.getEntityComponentInstance(player, playerPos.getClass());
    }

    @Override
    public void onCreate() {
       
    }

    @Override
    public void onDestroy() {
        
    }
    
    private void updateEntityPosition(Integer entity) {
                    
            //true if the enemy is to the right of the player
            boolean right;
            
            //true if the enemy is above the player
            boolean up;
            
            //distance in the x axis
            double distanceX;
            
            //distance in the y axis
            double distanceY;
            
            //transform of the enemy
            Transform enemyPos = scene.entityManager.getEntityComponentInstance(entity, playerPos.getClass());
            
            //Playable of the enemy
            Playable enemyPlay = scene.entityManager.getEntityComponentInstance(entity, playable.getClass());
            
            //Sprite of the enemy
            Sprite enemySprite = scene.entityManager.getEntityComponentInstance(entity, sprite.getClass());
            
            distanceX = enemyPos.position.x - playerPos.position.x;
            
            distanceY = enemyPos.position.y - playerPos.position.y;

            right = distanceX > 0;
            
            up = 0 > distanceY;
            
            distanceX = abs(distanceX);
            
            distanceY = abs(distanceY);
            
            //Vector3 newDist = enemyPos.position.sub(playerPos.position).norm().scalar(enemyPlay.velocity.x);
            Vector3 newDist = playerPos.position.sub(enemyPos.position).norm().scalar(enemyPlay.velocity.x);
         
            
            
            if(abs(playerPos.position.x - enemyPos.position.x) > marginDistance && abs(playerPos.position.y - enemyPos.position.y) > marginDistance) {
                enemyPos.position = enemyPos.position.add(newDist);
            }
            
            else if (abs(playerPos.position.x - enemyPos.position.x) > marginDistance && abs(playerPos.position.y - enemyPos.position.y) < marginDistance) {
                
                if(playerPos.position.x > enemyPos.position.x) {
                    
                    if(playerPos.position.x - enemyPos.position.x > enemySprite.width + 20) {
                        enemyPos.position.x += abs(enemyPlay.velocity.x);
                    }
                }
                
                else {
                    enemyPos.position.x -= abs(enemyPlay.velocity.x);
                }
                
            }

            else if (abs(playerPos.position.y - enemyPos.position.y) > marginDistance && abs(playerPos.position.x - enemyPos.position.x) < marginDistance) {
             
                if (playerPos.position.y > enemyPos.position.y) {
                    
                    if(playerPos.position.y - enemyPos.position.y > enemySprite.height) {
                        enemyPos.position.y += abs(enemyPlay.velocity.y);
                    }
                } 
                
                else {
                    enemyPos.position.y -= abs(enemyPlay.velocity.y);
                }

  
            }
            
            //else we will make an attack if possible
            else {
                
            }
            
            //System.out.println(enemyPos.position.x);
    }
    
}
