/*
 * System to update the positions of the weapon colliders relative to the
 * player or playable that helds the weapons(relative to the upper left corner)
 */
package ECS.Systems;

import ECS.Components.AttackCollider;
import ECS.Components.AttackComponent;
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

/**
 *
 * @author pablo
 */
public class WeaponColliderPositionSystem extends SystemJob{
    
    Player player;
    Playable playerPlayable;
    
    AttackCollider attackCollider;
    AttackComponent attackComponent;
    Transform transform;
    Sprite sprite;
    
    Rectangle rectangle;
    
    int playerID;

    public WeaponColliderPositionSystem(Scene scene) {
        super(scene);
    }

    @Override
    public void update() {
       updatePlayerColliders(); 
       
       
    }

    @Override
    public void init() {
        
        player = new Player();
        
        playerPlayable = new Playable();
        
        attackCollider = new AttackCollider();
        
        attackComponent = new AttackComponent();
        
        transform = new Transform();
        
        sprite = new Sprite();
        
        //we only have one player
        playerID = scene.entityManager.getEntitiesWithComponents(player.getClass()).get(0);
        
        player = scene.entityManager.getEntityComponentInstance(playerID, player.getClass());
        
        playerPlayable = scene.entityManager.getEntityComponentInstance(playerID, playerPlayable.getClass());
    }

    @Override
    public void onCreate() {
        
    }

    @Override
    public void onDestroy() {

    }
    
    public void updatePlayerColliders() {
        
        
        
        Integer rightTool;
        Integer leftTool;
        
        AttackComponent rightComponent, leftComponent;
        
        rightComponent = new AttackComponent();
        
        leftComponent = new AttackComponent();
        
        //relative position X and Y of the collider to the player
        double relPosX;
        double relPosY;
        
        double height;
        double width;
        
        //vector of the position of the player
        Vector3 posPlayer = scene.entityManager.getEntityComponentInstance(playerID, transform.getClass()).position;
        
        //sprite of the player
        sprite = scene.entityManager.getEntityComponentInstance(playerID, sprite.getClass());
        
        //width of the player
        int playerWidth = sprite.width;
        
        //height of the player
        int playerHeight = sprite.height;
        
        //System.out.println("playerCols");
        
        
        //if the player has a right hand weapon
        if(player.boolRight) {
            //obtain its ID
            rightTool = scene.entityManager.getEntityByID(player.rightHand).getID();
            
            //colliders of the right weapon
            rightComponent= scene.entityManager.getEntityComponentInstance(rightTool, attackComponent.getClass());    
        
            //System.out.println(scene.entityManager.getEntityByID(rightTool).getName());
        }
        
        //if the player has a left hand weapon
        if(player.boolLeft) {
            leftTool = scene.entityManager.getEntityByID(player.leftHand).getID();
            
            //colliders of the left weapon
            leftComponent= scene.entityManager.getEntityComponentInstance(leftTool, attackComponent.getClass());
        }
        
        
        

        //if the player is moving up
        if (playerPlayable.up) {
            if (player.boolRight) {
                //for each collider of its right hand weapon
                for (AttackCollider at : rightComponent.arrColliders) {
                    //System.out.println("Up");
                    at.relativePosition.x = playerWidth;
                    
                    at.relativePosition.y = - playerHeight - at.a + 5;
                    
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

                    at.relativePosition.x= -1 * abs((at.b / 2) - (playerWidth / 2));

                    at.relativePosition.y = at.a;

                    at.hitbox.y = at.a;

                    at.hitbox.x = at.b;
                }
            }
        } //if the player is moving down
        else if (playerPlayable.down) {  
            if (player.boolRight) {
                //for each collider of its right hand weapon
                for (AttackCollider at : rightComponent.arrColliders) {
                   // System.out.println("Down");
                    at.relativePosition.x = playerWidth;
                    
                    at.relativePosition.y = 0;
                    
                    at.hitbox.y = at.a;
                    
                    at.hitbox.x = at.b;
                }
            }

            //
            if (player.boolLeft) {
                //for each collider of its left hand weapon
                for (AttackCollider at : leftComponent.arrColliders) {
                    at.relativePosition.x = -1 * abs((at.b / 2) - (playerWidth / 2));

                    at.relativePosition.y = playerHeight;

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
                    at.relativePosition.x = 8;

                    at.relativePosition.y = - playerHeight + 5;

                    at.hitbox.y = at.b;

                    at.hitbox.x = at.a;
                }
            }

            //
            if (player.boolLeft) {
                //for each collider of its left hand weapon
                for (AttackCollider at : leftComponent.arrColliders) {
                    at.relativePosition.x = -1 * at.a;

                    at.relativePosition.y = -1 * abs((at.b / 2) - (playerHeight / 2));

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
                       
                    at.relativePosition.x = playerWidth + 25;

                    at.relativePosition.y = - playerHeight + 5;

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

}
