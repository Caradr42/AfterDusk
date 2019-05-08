package ECS.Systems;

import ECS.Components.AttackCollider;
import ECS.Components.AttackComponent;
import ECS.Components.Collidable;
import ECS.Components.Electricity;
import ECS.Components.Inventory;
import ECS.Components.Item;
import ECS.Components.Playable;
import ECS.Components.Player;
import ECS.Components.Sprite;
import ECS.Components.Tool;
import ECS.Entity;
import ECS.SystemJob;
import Scene.Scene;
import java.util.ArrayList;
import sun.security.pkcs11.wrapper.Functions;

/**
 * Manages the Active Electric
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class ElectricSystem extends SystemJob {

    Sprite sprite;
    AttackComponent attack;
    int id;
    Entity entity;
    Tool tool;
    Electricity electricity;
    Playable playable;
    AttackCollider collidable;
    AttackCollider collider;
    Inventory inventory;

    /**
     * Constructor
     *
     * @param scene
     * @param active
     */
    public ElectricSystem(Scene scene, boolean active) {
        super(scene, active);
    }

    @Override
    public void update() {
        entities = scene.entityManager.getEntitiesWithComponents(AttackComponent.class, Electricity.class);
        for (Integer e : entities) {
            tool = scene.entityManager.getEntityComponentInstance(e, Tool.class);
            if (tool.currentActive != -1) {

                tool.currentActive = -1;

                attack = scene.entityManager.getEntityComponentInstance(e, AttackComponent.class);
                for (AttackCollider a : attack.arrColliders) {

                    for (Integer b : a.collidesWith) {
                        entity = scene.entityManager.getEntityByID(b);

                        playable = scene.entityManager.getEntityComponentInstance(b, Playable.class);

                        if (playable.isAlive) {
                            playable.hp = playable.hp - electricity.cost;
                            
                            Playable pl = scene.entityManager.getEntityComponentInstance( scene.entityManager.getEntitiesWithComponents(Playable.class).get(0), Playable.class);
                            Player lyr = scene.entityManager.getEntityComponentInstance( scene.entityManager.getEntitiesWithComponents(Player.class).get(0), Player.class);
                            
                            if(pl.currentWeapon == e){
                                System.out.println("aaaaaaaaaaaaaaaaaaawsdads");
                            }
                            
                           //if(e==lyr.)
                           

                            if (playable.hp <= 0) {
                                playable.isAlive = false;
                                sprite = scene.entityManager.getEntityComponentInstance(b, Sprite.class);

                                sprite.visible = false;

                                Collidable coll = scene.entityManager.getEntityComponentInstance(b, Collidable.class);
                                coll.active = false;

                                if (playable.inventory != null) {
                                    inventory = scene.entityManager.getEntityComponentInstance(playable.inventory, Inventory.class);
                                    if (playable.inventory != 0 && inventory.slots.get(0) != 0) {
                                        tool = scene.entityManager.getEntityComponentInstance(inventory.slots.get(0), Tool.class);
                                        tool.currentActive = -1;
                                        AttackComponent attackComponent = scene.entityManager.getEntityComponentInstance(inventory.slots.get(0), AttackComponent.class);

                                        for (AttackCollider i : attackComponent.arrColliders) {
                                            i.active = false;
                                        }
                                        Item item = scene.entityManager.getEntityComponentInstance(inventory.slots.get(0), Item.class);

                                    }
                                }

                            }
                        }
                    }
                    a.collidesWith.clear();
                }

            }
        }

    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(AttackComponent.class, Electricity.class);
        electricity = new Electricity();
        tool = new Tool();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

}
