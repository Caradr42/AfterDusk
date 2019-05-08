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
 * Manages the passive of extra health
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
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
    boolean wasActive;
    boolean wasNotActive;

    /**
     * Constructor
     *
     * @param scene
     * @param active
     */
    public ExtraHealthSystem(Scene scene, boolean active) {
        super(scene, active);
    }

    @Override
    public void update() {

        entities = scene.entityManager.getEntitiesWithComponents(ExtraHealth.class);
        idPlayer = scene.entityManager.getEntitiesWithComponents(Player.class).get(0);
        player = scene.entityManager.getEntityComponentInstance(idPlayer, Player.class);
        passivesInventory = scene.entityManager.getEntityComponentInstance(player.pasivesInventory, Inventory.class);
        idPlayable = idPlayer;
        playable = scene.entityManager.getEntityComponentInstance(idPlayable, Playable.class);
        maxHealthActual = playable.maxHp;

        for (Integer e : entities) {
            health = scene.entityManager.getEntityComponentInstance(e, ExtraHealth.class);
        }

        if (health.isActive && !wasActive)  {
            
            //maxHealthActual = maxHealthActual + health.cost; //increments the health
            playable.hp = playable.hp + health.cost;
            if(playable.hp > playable.maxHp) playable.hp = playable.maxHp;
            System.out.println("playable " + playable.hp);
            
            wasActive = true;
            wasNotActive = false;
        } else if (!health.isActive && !wasNotActive){
            //maxHealthActual = playable.maxHp;
            playable.hp = playable.hp - health.cost;
            //System.out.println("playable out " + playable.hp);
             if(playable.hp < 0) playable.hp = 10;
            wasNotActive = true;
            wasActive = false;
        }
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(ExtraHealth.class);
        health = new ExtraHealth();
        tool = new Tool();
        wasActive = false;
        wasNotActive = false;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

}
