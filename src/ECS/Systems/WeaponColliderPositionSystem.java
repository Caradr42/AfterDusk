/*
 * System to update the positions of the weapon colliders relative to the
 * player or enemies that helds the weapons(relative to the upper left corner)
 */
package ECS.Systems;

import ECS.Components.AttackCollider;
import ECS.Components.AttackComponent;
import ECS.Components.Enemy;
import ECS.Components.Inventory;
import ECS.Components.Playable;
import ECS.Components.Player;
import ECS.Components.Sprite;
import ECS.Components.Tool;
import ECS.Components.Transform;
import ECS.SystemJob;
import Maths.Vector3;
import Scene.Scene;
import java.awt.Rectangle;
import static java.lang.Math.abs;
import java.util.ArrayList;

/**
 *
 * @author pablo
 */
public class WeaponColliderPositionSystem extends SystemJob{
    
    Player player;
    Playable playerPlayable;
    Playable playable;
    Inventory playerLRInventory;
    
    AttackCollider attackCollider;
    AttackComponent attackComponent;
    Transform transform;
    Sprite sprite;
    
    Rectangle rectangle;
    
    int playerID;

    public WeaponColliderPositionSystem(Scene scene, boolean active) {
        super(scene, active);
    }

    @Override
    public void update() {
       updatePlayerColliders(); 
       updateEnemyColliders();
    }

    @Override
    public void init() {
        
        player = new Player();
        playerPlayable = new Playable();
        attackCollider = new AttackCollider();
        attackComponent = new AttackComponent();
        transform = new Transform();
        sprite = new Sprite();
        playerLRInventory = new Inventory();
        entities = new ArrayList<>();
        
        //we only have one player
        playerID = scene.entityManager.getEntitiesWithComponents(player.getClass()).get(0);
        player = scene.entityManager.getEntityComponentInstance(playerID, player.getClass());
        playerPlayable = scene.entityManager.getEntityComponentInstance(playerID, playerPlayable.getClass());
        playerLRInventory = scene.entityManager.getEntityComponentInstance(player.LRInventory, Inventory.class);
    
        entities = scene.entityManager.getEntitiesWithComponents(Playable.class);
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
    
    public void updatePlayerColliders() {   
        
        
        //for each entity
        for(Integer e : entities) {
            playable = scene.entityManager.getEntityComponentInstance(e, Playable.class);
            sprite = scene.entityManager.getEntityComponentInstance(e, Sprite.class);
            
            //width of the entity
            int entityWidth = sprite.width;
            
            //height of the entity
            int entityHeight = sprite.height;
            
            //if the entity is using a weapon
            if(playable.hasWeapon) {
                //get the colliders of the weapon
                AttackComponent attackComponent = scene.entityManager.getEntityComponentInstance(e, AttackComponent.class);
            }
        }
        
        
        
        
        
        
        Integer rightTool;
        Integer leftTool;
        
        AttackComponent rightComponent, leftComponent;
        
        rightComponent = new AttackComponent();
        
        leftComponent = new AttackComponent();
                       
        //sprite of the player
        sprite = scene.entityManager.getEntityComponentInstance(playerID, sprite.getClass());
        
        transform = scene.entityManager.getEntityComponentInstance(playerID, Transform.class);
        
        //width of the player
        int playerWidth = sprite.width;
        
        //height of the player
        int playerHeight = sprite.height;
        
        //if the player has a right hand weapon
        if(player.boolRight) {
            //obtain its ID
            rightTool = playerLRInventory.slots.get(1);
            
            //colliders of the right weapon
            rightComponent = scene.entityManager.getEntityComponentInstance(rightTool, attackComponent.getClass());    
        
            //System.out.println(scene.entityManager.getEntityByID(rightTool).getName());
        }
        
        //if the player has a left hand weapon
        if(player.boolLeft) {
            leftTool =  playerLRInventory.slots.get(0);
            
            //colliders of the left weapon
            leftComponent= scene.entityManager.getEntityComponentInstance(leftTool, attackComponent.getClass());
        }
        
        
        

        //if the player is moving up
        if (playerPlayable.up) {
            if (player.boolRight) {
                //for each collider of its right hand weapon
                for (AttackCollider at : rightComponent.arrColliders) {
                    //System.out.println("Up");
                    at.relativePosition.x = -at.b / 2 + playerWidth / 2;
                    
                    at.relativePosition.y = - playerHeight - at.a + transform.position.z;
                    
                    //the y is the height of the collider
                    at.hitbox.y = at.a;
                    
                    //the x is the width of the collider
                    at.hitbox.x = at.b;
                }
            }

            //
            if (player.boolLeft) {
                //for each collider of its left hand weapon
                for (AttackCollider at : leftComponent.arrColliders) {

                    at.relativePosition.x = -at.b / 2 + playerWidth / 2;
                    
                    at.relativePosition.y = - playerHeight - at.a + transform.position.z;
                    
                    //the y is the height of the collider
                    at.hitbox.y = at.a;
                    
                    //the x is the width of the collider
                    at.hitbox.x = at.b;
                }
            }
        } //if the player is moving down
        else if (playerPlayable.down) {  
            if (player.boolRight) {
                //for each collider of its right hand weapon
                for (AttackCollider at : rightComponent.arrColliders) {
                   // System.out.println("Down");
                    at.relativePosition.x = -at.b / 2 + playerWidth / 2;
                    
                    at.relativePosition.y = transform.position.z;
                    
                    at.hitbox.y = at.a;
                    
                    at.hitbox.x = at.b;
                }
            }

            //
            if (player.boolLeft) {
                //for each collider of its left hand weapon
                for (AttackCollider at : leftComponent.arrColliders) {
                    at.relativePosition.x = -at.b / 2 + playerWidth / 2;
                    
                    at.relativePosition.y = transform.position.z;
                    
                    at.hitbox.y = at.a;
                    
                    at.hitbox.x = at.b;
                }
            }

        } //if the player is moving left
        else if (playerPlayable.left) {
            
            if (player.boolRight) {
                //for each collider of its right hand weapon
                for (AttackCollider at : rightComponent.arrColliders) {
                    //System.out.println("Left");
                    at.relativePosition.x = - at.a;

                    at.relativePosition.y = - at.b / 2 - playerHeight / 2 + transform.position.z;

                    at.hitbox.y = at.b;

                    at.hitbox.x = at.a;
                }
            }

            //
            if (player.boolLeft) {
                //for each collider of its left hand weapon
                for (AttackCollider at : leftComponent.arrColliders) {
                    at.relativePosition.x = - at.a;

                    at.relativePosition.y = - at.b / 2 - playerHeight / 2 + transform.position.z;

                    at.hitbox.y = at.b;

                    at.hitbox.x = at.a;
                }
            }

        }

        //if the player is moving right
        else if (playerPlayable.right) {
            

            if (player.boolRight) {
                //for each collider of its right hand weapon
                for (AttackCollider at : rightComponent.arrColliders) {
                    //System.out.println("Right");
                       
                    at.relativePosition.x = playerWidth;
                    //at.a

                    at.relativePosition.y = - at.b / 2 - playerHeight / 2 + transform.position.z;
                    //- playerHeight

                    at.hitbox.y = at.b;

                    at.hitbox.x = at.a;
                }
            }

            //
            if (player.boolLeft) {
                //for each collider of its left hand weapon
                for (AttackCollider at : leftComponent.arrColliders) {
                    at.relativePosition.x = playerWidth;

                    at.relativePosition.y = -1 * abs((at.b / 2) - (playerHeight / 2));

                    at.hitbox.y = at.b;

                    at.hitbox.x = at.a;
                }
            }
            
        }
    }
    
    public void updateEnemyColliders() {
        /*
        //ID of the weapon of the enemy. It is always in the first position of its inventory
        Integer weapon;
        
        Enemy enemy = new Enemy();
        
        ArrayList<Integer> enemies = scene.entityManager.getEntitiesWithComponents(Enemy.class);
        
        //For each enemy
        for(Integer idEnemy : enemies) {
            enemy = scene.entityManager.getEntityByID(idEnemy);
        }*/
    }

}
