package ECS.Systems;

import ECS.Components.Inventory;
import ECS.Components.Sprite;
import ECS.Components.UIInventory;
import ECS.SystemJob;
import Scene.Scene;
import java.awt.Rectangle;
import java.util.ArrayList;
import Assets.Assets;
import ECS.Components.MousePointer;
import ECS.Components.Transform;
import Maths.Vector3;

/**
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class UIInventorySystem extends SystemJob{
    
    ArrayList<Integer> mousePointers;
    
    Sprite inventorySprite;
    Transform inventoryTransform;
    
    Transform parentTransform;
    
    UIInventory uiInventory; //temp inventory to hold the Inventory component of the inventories
    Inventory inventory;
    Sprite itemSprite; //the sprite of the items to render in the inventory
    
    
    MousePointer mousePointer;
    
    public UIInventorySystem(Scene scene, boolean active) {
        super(scene, active);
        
        this.inventorySprite = new Sprite();
        this.inventoryTransform = new Transform();
        this.parentTransform = new Transform();
                
        uiInventory = new UIInventory();
        inventory = new Inventory();
        itemSprite = new Sprite();
        mousePointer = new MousePointer();
    }

    @Override
    public void update() {
        for(Integer e: entities){
            uiInventory = scene.entityManager.getEntityComponentInstance(e, uiInventory.getClass());
            
            inventorySprite = scene.entityManager.getEntityComponentInstance(e, inventorySprite.getClass());
            inventoryTransform = scene.entityManager.getEntityComponentInstance(e, inventoryTransform.getClass());
            parentTransform = scene.entityManager.getEntityComponentInstance(inventoryTransform.parent, parentTransform.getClass());
            
            int temp = uiInventory.firstInventory;
            inventory = scene.entityManager.getEntityComponentInstance(temp, inventory.getClass());
            for(int i = 0; temp != 0; ++i){ 
                
                //uiInventory.UISlots.add(new ArrayList<>());
                for(int j = 0; j < inventory.size; ++j){
                    uiInventory.UISlots.get(i).get(j).setLocation((int)(/*parentTransform.position.x +*/ inventoryTransform.position.x + (j * 17) + 1 ), (int)(/*parentTransform.position.y +*/ inventoryTransform.position.y + (i * 17) + 1));
                }
                temp = inventory.nextInventory;
                if(temp != 0)
                    inventory = scene.entityManager.getEntityComponentInstance(temp, inventory.getClass());
            }            
            
            //System.out.println(uiInventory._visible);
            if(inventorySprite.visible){
                for(int i = 0; i < uiInventory.UISlots.size(); ++i){
                    for(int j = 0; j < uiInventory.UISlots.get(i).size(); ++j){
                        //if the mouse is inside the UIslot rectangle
                        if(uiInventory.UISlots.get(i).get(j).contains((int)mousePointer.position.x, (int)mousePointer.position.y)){
                            if(mousePointer.mouseManager.wasLeftClicked()){

                                //Play pick up sound
                                Assets.pickUp.play();

                                int itemBuffer = mousePointer.heldItem;
                                mousePointer.heldItem = getItemFromInventory(uiInventory.firstInventory, i, j);
                                    setItemFromInventory(uiInventory.firstInventory, i, j, itemBuffer);
                            }
                        }
                    }
                }
            }
            //System.out.println(inventoryTransform.position.x + " " + inventoryTransform.position.y + " :: " + inventoryTransform.relativePosition.x + " " + inventoryTransform.relativePosition.y);
            
        }
       // System.out.println(mousePointer.position.x + " " + mousePointer.position.y);
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(uiInventory.getClass());
        mousePointers = scene.entityManager.getEntitiesWithComponents(mousePointer.getClass());
        mousePointer = scene.entityManager.getEntityComponentInstance(mousePointers.get(0), mousePointer.getClass());
        
        //sets the slots  acording to the inventory
        for(Integer e: entities){//iterate on all UIInventories
            uiInventory = scene.entityManager.getEntityComponentInstance(e, uiInventory.getClass());
            inventorySprite = scene.entityManager.getEntityComponentInstance(e, inventorySprite.getClass());
            inventoryTransform = scene.entityManager.getEntityComponentInstance(e, inventoryTransform.getClass());
            parentTransform = scene.entityManager.getEntityComponentInstance(inventoryTransform.parent, parentTransform.getClass());
                    
            //update the sprite and transform references in the inventoryUI
            uiInventory._uiSprite = inventorySprite;
            uiInventory._uiTransform = inventoryTransform;
            
            int temp = uiInventory.firstInventory;
            inventory = scene.entityManager.getEntityComponentInstance(temp, inventory.getClass());
            for(int i = 0; temp != 0; ++i){ 
                
                uiInventory.UISlots.add(new ArrayList<>());
                for(int j = 0; j < inventory.size; ++j){
                    uiInventory.UISlots.get(i).add(new Rectangle((int)(parentTransform.position.x + inventoryTransform.position.x + (j * 17) + 1 ), (int)(parentTransform.position.y + inventoryTransform.position.y + (i * 17) + 1), 16, 16));
                }
                temp = inventory.nextInventory;
                if(temp != 0)
                    inventory = scene.entityManager.getEntityComponentInstance(temp, inventory.getClass());
            }            
        }
    }
    
    @Override
    public void onCreate() {
    }
    
    @Override
    public void onDestroy() {
    }
    
    /**
     * Return the item id from an inventory slot by providing a vertical coordinate i and a horizontal j
     * @param inv
     * @param i
     * @param j
     * @return 
     */
    private Integer getItemFromInventory(Integer e, int i, int j){
        Inventory invComp = new Inventory();
        int temp = uiInventory.firstInventory;
        invComp = scene.entityManager.getEntityComponentInstance(temp, invComp.getClass());
        //follow the inventories LinkedList up to the i list
        for(int inv = 0; temp != 0; ++inv){ 
            
            if(inv == i){
                if(invComp.slots.get(j) == null){
                    return 0;
                }
                return invComp.slots.get(j); //returns the Item id at position j
            }  
            temp = invComp.nextInventory;
            if(temp != 0){
                invComp = scene.entityManager.getEntityComponentInstance(temp, invComp.getClass());
            }
            //System.out.println(inv);
        }
        return 0;
    }
    
    private void setItemFromInventory(Integer e, int i, int j, Integer item){
        Inventory invComp = new Inventory();
        int temp = uiInventory.firstInventory;
        invComp = scene.entityManager.getEntityComponentInstance(temp, invComp.getClass());
        //follow the inventories LinkedList up to the i list
        for(int inv = 0; temp != 0; ++inv){ 
            
            if(inv == i){
                invComp.slots.set(j, item); //returns the Item id at position j
            }  
            temp = invComp.nextInventory;
            if(temp != 0){
                invComp = scene.entityManager.getEntityComponentInstance(temp, invComp.getClass());
            }
            //System.out.println(inv);
        }
    }
}
