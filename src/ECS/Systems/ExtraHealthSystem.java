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
import ECS.Components.Pasive;
import ECS.Components.Playable;
import ECS.Components.Player;
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

    Entity entity;
    Tool tool;
    Playable playable;

    Inventory passivesInventory;
    ExtraHealth health;
    Pasive passive;
    int idPlayer;
    Player player;
   int idPlayable;
   int maxHealthActual;
   
    public ExtraHealthSystem(Scene scene, boolean active) {
        super(scene, active);
    }

    @Override
    public void update() {

        
        entities = scene.entityManager.getEntitiesWithComponents(ExtraHealth.class);      
        idPlayer = scene.entityManager.getEntitiesWithComponents(Player.class).get(0);
        player = scene.entityManager.getEntityComponentInstance(idPlayer, Player.class);
        passivesInventory = scene.entityManager.getEntityComponentInstance(player.pasivesInventory,Inventory.class);  
        idPlayable =scene.entityManager.getEntitiesWithComponents(Playable.class).get(0);
        playable = scene.entityManager.getEntityComponentInstance(idPlayable, Playable.class); 
        maxHealthActual = playable.maxHp;
        
        for(Integer e : entities)
            health = scene.entityManager.getEntityComponentInstance(e, ExtraHealth.class);

        if(health.isActive){
            if(playable.isAlive){
               maxHealthActual = maxHealthActual + health.cost;
               playable.hp = maxHealthActual;
            } 
        } else if(!health.isActive){
            maxHealthActual = playable.maxHp;
        }
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(ExtraHealth.class);
        health = new ExtraHealth();
        tool = new Tool();
    }

    @Override
    public void onCreate() {
        
    }

    @Override
    public void onDestroy() {
        
    }
    
}
