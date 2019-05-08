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
import ECS.Entity;
import ECS.SystemJob;
import Maths.Vector3;
import Scene.Scene;
import java.awt.Rectangle;
import static java.lang.Math.abs;
import java.util.ArrayList;

/**
 * System to update the positions of the weapon colliders relative to the player
 * or enemies that helds the weapons(relative to the upper left corner)
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class WeaponColliderPositionSystem extends SystemJob {

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

    /**
     * Constructor
     *
     * @param scene
     * @param active
     */
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
        for (Integer e : entities) {

            playable = scene.entityManager.getEntityComponentInstance(e, Playable.class);
            sprite = scene.entityManager.getEntityComponentInstance(e, Sprite.class);
            transform = scene.entityManager.getEntityComponentInstance(e, Transform.class);

            //width of the entity
            int entityWidth = sprite.width;

            //height of the entity
            int entityHeight = sprite.height;

            //if the entity is using a weapon
            if (playable.hasWeapon) {

                //if it is the player
                if (scene.entityManager.hasComponent(e, Player.class)) {

                    //if it is using a weapon with its right hand
                    if (player.rightOrLeft) {
                        //get the colliders of the weapon
                        attackComponent = scene.entityManager.getEntityComponentInstance(playerLRInventory.slots.get(1), AttackComponent.class);
                    } else {
                        attackComponent = scene.entityManager.getEntityComponentInstance(playerLRInventory.slots.get(0), AttackComponent.class);
                    }
                } //if it is not a player
                else {

                    //getting the inventory component
                    Inventory inventory = scene.entityManager.getEntityComponentInstance(playable.inventory, Inventory.class);

                    attackComponent = scene.entityManager.getEntityComponentInstance(inventory.slots.get(0), AttackComponent.class);
                }

                //for each collider of the weapon
                for (AttackCollider at : attackComponent.arrColliders) {
                    //if it is not an area attack
                    if (!at.areaAttack) {

                        if (playable.up) {
                            at.relativePosition.x = -at.b / 2 + entityWidth / 2;

                            at.relativePosition.y = -entityHeight - at.a + transform.position.z;

                            //the y is the height of the collider
                            at.hitbox.y = at.a;

                            //the x is the width of the collider
                            at.hitbox.x = at.b;
                        } else if (playable.down) {

                            at.relativePosition.x = -at.b / 2 + entityWidth / 2;

                            at.relativePosition.y = transform.position.z;

                            at.hitbox.y = at.a;

                            at.hitbox.x = at.b;
                        } else if (playable.right) {
                            at.relativePosition.x = entityWidth;

                            at.relativePosition.y = -at.b / 2 - entityHeight / 2 + transform.position.z;

                            at.hitbox.y = at.b;

                            at.hitbox.x = at.a;
                        } else if (playable.left) {

                            at.relativePosition.x = -at.a;

                            at.relativePosition.y = -at.b / 2 - entityHeight / 2 + transform.position.z;

                            at.hitbox.y = at.b;

                            at.hitbox.x = at.a;
                        }
                    } //else, if it is an area attack
                    else {

                    }
                }

            }

        }

    }

    public void updateEnemyColliders() {

    }

}
