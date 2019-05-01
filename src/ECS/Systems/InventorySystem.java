package ECS.Systems;

import ECS.Components.Inventory;
import ECS.Components.Playable;
import ECS.Components.Player;
import ECS.Components.Transform;
import ECS.SystemJob;
import static ECS.SystemJob.scene;
import Maths.Vector3;
import Scene.Scene;

/**
 *
 * @author carlo
 */
public class InventorySystem extends SystemJob{
    
    Player player;
    Integer playerID;
    Playable playerPlayable;
    Transform itemTransform;
    
    public InventorySystem(Scene scene, boolean active) {
        super(scene, active);
        player = new Player();
        itemTransform = new Transform();
        playerPlayable = new Playable();
    }

    @Override
    public void update() {
    }

    @Override
    public void init() {
        playerID = scene.entityManager.getEntitiesWithComponents(Player.class).get(0);
        player = scene.entityManager.getEntityComponentInstance(playerID, Player.class);
        playerPlayable = scene.entityManager.getEntityComponentInstance(playerID, Playable.class);
       
        setItemsTransformAsChildOfPlayer(player.LRInventory);
        setItemsTransformAsChildOfPlayer(player.pasivesInventory);
        setItemsTransformAsChildOfPlayer(playerPlayable.inventory);
        
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
    
    private void setItemsTransformAsChildOfPlayer(Integer inventoryID){
        int tempID = inventoryID;
        Inventory inventory = scene.entityManager.getEntityComponentInstance(tempID, Inventory.class);
        
        for(int inv = 0; tempID != 0; ++inv){ 
            for(int j = 0; j < inventory.size; ++j){
                //if the slot has an item
                if(inventory.slots.get(j) != 0){
                    itemTransform = scene.entityManager.getEntityComponentInstance(inventory.slots.get(j), Transform.class);
                    itemTransform.relativePosition = new Vector3();
                    if(itemTransform != null){
                        itemTransform.parent = playerID;
                    }
                }
            }
            
            tempID = inventory.nextInventory;
            if(tempID != 0){
                inventory = scene.entityManager.getEntityComponentInstance(tempID, inventory.getClass());
            }
        }
    }
    
}
