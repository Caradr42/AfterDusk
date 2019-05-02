/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import ECS.Components.AttackCollider;
import ECS.Components.AttackComponent;
import ECS.Components.ExtraHealth;
import ECS.Components.Inventory;
import ECS.Components.Playable;
import ECS.Components.Sprite;
import ECS.Components.Tool;
import ECS.Entity;
import ECS.SystemJob;
import Scene.Scene;

/**
 *
 * @author tanya
 */
public class ExtraHealthSystem extends SystemJob {
    Sprite sprite;
    AttackComponent attack;
    int id;
    Entity entity;
    Tool tool;
    ExtraHealth extraHealth;
    Playable playable;
    AttackCollider collidable;
    AttackCollider collider;
    Inventory inventory;

    public ExtraHealthSystem(Scene scene, boolean active) {
        super(scene, active);
    }

    @Override
    public void update() {
        
        entities = scene.entityManager.getEntitiesWithComponents(ExtraHealth.class);
        for(Integer e : entities){
            
            playable = scene.entityManager.getEntityComponentInstance(e, Playable.class);
           playable.hp = playable.hp + extraHealth.cost;
            System.out.println("player hp " + playable.hp);
        }
        
        
       /* for (Integer e : entities) {

            //System.out.println(scene.entityManager.getEntityByID(e).getName());
            // System.out.println("currentActv electricsus");
            //System.out.println("currentActv electricsus" + tool.currentActive);
            if (tool.currentActive != -1) {

                tool = scene.entityManager.getEntityComponentInstance(e, Tool.class);

                attack = scene.entityManager.getEntityComponentInstance(e, AttackComponent.class);
                //System.out.println("arrColilders " + attack.arrColliders.size());
                for (AttackCollider a : attack.arrColliders) {
                    for (Integer b : a.collidesWith) {
                        entity = scene.entityManager.getEntityByID(b);

                        playable = scene.entityManager.getEntityComponentInstance(b, Playable.class);

                        playable.hp = playable.hp - electricity.cost;
                        System.out.println("playable " + playable.hp);
                        if (playable.hp <= 0) {

                            sprite = scene.entityManager.getEntityComponentInstance(b, Sprite.class);
                            sprite.visible = false;
                            collidable = scene.entityManager.getEntityComponentInstance(b, AttackCollider.class);
                            collidable.active = false;
                            inventory = scene.entityManager.getEntityComponentInstance(playable.inventory, Inventory.class);
                            if (inventory.slots.get(0) != 0) {
                                tool = scene.entityManager.getEntityComponentInstance(inventory.slots.get(0), Tool.class);
                                AttackComponent attackComponent = scene.entityManager.getEntityComponentInstance(inventory.slots.get(0), AttackComponent.class);

                                for (AttackCollider i : attackComponent.arrColliders) {
                                    i.active = false;
                                }
                                Item item = scene.entityManager.getEntityComponentInstance(inventory.slots.get(0), Item.class);
                                item.isInInventory = false;
                                playable.isAlive = false;
                            }
                        }
                    }
                    a.collidesWith.clear();
                }
                tool.currentActive = -1;

            }
        }*/

        
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(ExtraHealth.class);
        extraHealth = new ExtraHealth();
    }

    @Override
    public void onCreate() {
        
    }

    @Override
    public void onDestroy() {
        
    }
    
}
