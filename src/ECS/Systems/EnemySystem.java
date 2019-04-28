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
    int marginDistance = 7;
    
    public EnemySystem(Scene scene) {
        super(scene);
        arrPlayable = new ArrayList<>();
        player1 = new Player();
        playable = new Playable();
        playerPos = new Transform();
        sprite = new Sprite();
    }

    @Override
    public void update() {
        //Each entity should follow the player 
        for(Integer entity : entities) {
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
            
            //If this is true tue enemy moves diagonally
            if(distanceX > marginDistance && distanceY > marginDistance) {
                
                if(right) {
                    //since java works a lot by reference, this will modify directly the transform of the enemy
                    enemyPos.position.x -= enemyPlay.velocity.x;
                }
                
                else {
                    enemyPos.position.x += enemyPlay.velocity.x;
                }
                
                if(up) {
                    enemyPos.position.y += enemyPlay.velocity.y;
                }
                
                else {
                    enemyPos.position.y -= enemyPlay.velocity.y;
                }
            }
            
            //Else if the enemy is further apart in the x axis than in the y axis, move in X
            else if(distanceX > distanceY && distanceX > enemySprite.width - 10) {
                if(right) {
                    enemyPos.position.x -= enemyPlay.velocity.x;
                }
                
                else {
                    enemyPos.position.x += enemyPlay.velocity.x;
                }
            }
            
            //else move in y
            else if(distanceY > distanceX && distanceY > enemySprite.height - 10) {
                if(up) {
                    enemyPos.position.y += enemyPlay.velocity.y;
                }
                
                else {
                    enemyPos.position.y -= enemyPlay.velocity.y;
                }
            }
            
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
    
    
    
}
