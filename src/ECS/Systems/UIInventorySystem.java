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
    
    UIInventory uiInventory; //temp inventory to hold the Inventory component of the inventories
    Inventory inventory;
    Sprite itemSprite; //the sprite of the items to render in the inventory
    MousePointer mousePointer;
    
    public UIInventorySystem(Scene scene) {
        super(scene);
        uiInventory = new UIInventory();
        inventory = new Inventory();
        itemSprite = new Sprite();
        mousePointer = new MousePointer();
    }

    @Override
    public void update() {
        for(Integer e: entities){
                 
            uiInventory = scene.entityManager.getEntityComponentInstance(e, uiInventory.getClass());
            //System.out.println(uiInventory.visible);
            if(uiInventory.visible){
                for(int i = 0; i < uiInventory.UISlots.size(); ++i){
                    for(int j = 0; j < uiInventory.UISlots.get(i).size(); ++j){
                        //if the mouse is inside the UIslot rectangle
                        if(uiInventory.UISlots.get(i).get(j).contains((int)mousePointer.position.x, (int)mousePointer.position.y)){
                            //System.out.println("col: " + i + " " + j +  " : " + getItemFromInventory(uiInventory.firstInventory, i, j));
                            if(mousePointer.mouseManager.wasLeftClicked()){
                                /*if(mousePointer.heldItem == 0){
                                    mousePointer.heldItem = getItemFromInventory(uiInventory.firstInventory, i, j);
                                    setItemFromInventory(uiInventory.firstInventory, i, j, 0);
                                }*/
                                //Play pick up sound
                                Assets.pickUp.play();
                                int itemBuffer = mousePointer.heldItem;
                                mousePointer.heldItem = getItemFromInventory(uiInventory.firstInventory, i, j);
                                    setItemFromInventory(uiInventory.firstInventory, i, j, itemBuffer);
                            }/*else if(mousePointer.mouseManager.wasLeftReleased()){
                                if(mousePointer.heldItem != 0 && getItemFromInventory(uiInventory.firstInventory, i, j) == 0){
                                    setItemFromInventory(uiInventory.firstInventory, i, j, mousePointer.heldItem);
                                    mousePointer.heldItem = 0;
                                }
                            }*/
                        }
                    }
                }
            }
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
            
            int temp = uiInventory.firstInventory;
            inventory = scene.entityManager.getEntityComponentInstance(temp, inventory.getClass());
            for(int i = 0; temp != 0; ++i){ 
                
                uiInventory.UISlots.add(new ArrayList<>());
                for(int j = 0; j < inventory.size; ++j){
                    uiInventory.UISlots.get(i).add(new Rectangle((int)uiInventory.position.x + (j * 17) + 1, (int)uiInventory.position.y + (i * 17) + 1, 16, 16));
                }
                temp = inventory.nextInventory;
                if(temp != 0)
                    inventory = scene.entityManager.getEntityComponentInstance(temp, inventory.getClass());
            }            
        }
        
        //fetch the sprite for the uiEntity and sets it 
        for(Integer e: entities){
            uiInventory = scene.entityManager.getEntityComponentInstance(e, uiInventory.getClass());
            
            for(int i = 0; i < uiInventory.animationsNames.size(); ++i){
                uiInventory.animations.add(Assets.animations.get(uiInventory.animationsNames.get(i)));
            }
            
            uiInventory.animation = uiInventory.animations.get(0).first;
            uiInventory.animationLenght = uiInventory.animations.get(0).second;
            
            uiInventory.currentFrame = uiInventory.animation[0];
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
