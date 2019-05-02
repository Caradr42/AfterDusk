/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import ECS.Components.AttackCollider;
import ECS.Components.AttackComponent;
import ECS.Components.Collidable;
import ECS.Components.Electricity;
import ECS.Components.Inventory;
import ECS.Components.Item;
import ECS.Components.Playable;
import ECS.Components.Sprite;
import ECS.Components.Tool;
import ECS.Entity;
import ECS.SystemJob;
import Scene.Scene;
import java.util.ArrayList;
import sun.security.pkcs11.wrapper.Functions;

/**
 *
 * @author tanya
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

    public ElectricSystem(Scene scene, boolean active) {
        super(scene, active);
    }

    @Override
    public void update() {
        entities = scene.entityManager.getEntitiesWithComponents(AttackComponent.class, Electricity.class);
        for (Integer e : entities) {
            tool = scene.entityManager.getEntityComponentInstance(e, Tool.class);
            //System.out.println(scene.entityManager.getEntityByID(e).getName());
            // System.out.println("currentActv electricsus");
            //System.out.println("currentActv electricsus" + tool.currentActive);
            if (tool.currentActive != -1) {
                //System.out.println("DFGSFDGD");
                
                tool.currentActive = -1;
                
                attack = scene.entityManager.getEntityComponentInstance(e, AttackComponent.class);
                //System.out.println("arrColilders " + attack.arrColliders.size());
                for (AttackCollider a : attack.arrColliders) {
                    
                    for (Integer b : a.collidesWith) {
                        entity = scene.entityManager.getEntityByID(b);

                        playable = scene.entityManager.getEntityComponentInstance(b, Playable.class);

                        if (playable.isAlive) {
                            playable.hp = playable.hp - electricity.cost;
                            System.out.println("playable " + playable.hp);
                            if (playable.hp <= 0) {
                                playable.isAlive = false;
                                sprite = scene.entityManager.getEntityComponentInstance(b, Sprite.class);
                                System.out.println("AAA");
                                sprite.visible = false;
                                //collidable = scene.entityManager.getEntityComponentInstance(b, AttackCollider.class);
                                //collidable.active = false;
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
                                        //inventory.slots.set(0, 0);
                                        //item.isInInventory = false;

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
