package ECS.Systems;

import ECS.Components.DamagePlus;
import ECS.Components.ExtraHealth;
import ECS.Components.Inventory;
import ECS.Components.Pasive;
import ECS.Components.Playable;
import ECS.Components.Player;
import ECS.Components.Tool;
import ECS.Entity;
import ECS.SystemJob;
import static ECS.SystemJob.scene;
import Scene.Scene;

/**
 * Class containing the data of the passive DamagePlusSystem
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 05/05/2019
 * @version 1.0
 */
public class DamagePlusSystem extends SystemJob{
    Entity entity;
    Tool tool;
    Playable playable;

    Inventory passivesInventory;
    DamagePlus damagePlus;
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
    public DamagePlusSystem(Scene scene, boolean active) {
        super(scene, active);
    }

    @Override
    public void update() {
        entities = scene.entityManager.getEntitiesWithComponents(DamagePlus.class);
        idPlayer = scene.entityManager.getEntitiesWithComponents(Player.class).get(0);
        player = scene.entityManager.getEntityComponentInstance(idPlayer, Player.class);
        //passivesInventory = scene.entityManager.getEntityComponentInstance(player.pasivesInventory, Inventory.class);
        //idPlayable = idPlayer;
        playable = scene.entityManager.getEntityComponentInstance(idPlayer, Playable.class);
        
        for(Integer e : entities){
            damagePlus = scene.entityManager.getEntityComponentInstance(e,DamagePlus.class);
        }
       // System.out.println(damagePlus.isActive);
        //System.out.println("active damage mult " + playable.damageMultiplier);
        if (damagePlus.isActive && !wasActive)  {
            
            //maxHealthActual = maxHealthActual + health.cost; //increments the health
            
            playable.damageMultiplier = 10;
            //System.out.println("active damage mult " + idPlayer);           
           
            //playable.hp = playable.hp + health.cost;
            //if(playable.hp > playable.maxHp) playable.hp = playable.maxHp;
            //System.out.println("damage player " + player.damageMultiplier);
            
            wasActive = true;
            wasNotActive = false;
        } else if (!damagePlus.isActive && !wasNotActive){
            //System.out.println("deactivate damage mult");  
            //maxHealthActual = playable.maxHp;
            playable.damageMultiplier = 1; //initializes to the iniitial damage
            //System.out.println("damage player out " + player.damageMultiplier);
            
            wasNotActive = true;
            wasActive = false;
        }
        
        
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(ExtraHealth.class);
        damagePlus = new DamagePlus();
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
