package ECS.Systems;

import Assets.Assets;
import ECS.Components.Item;
import ECS.Components.MousePointer;
import ECS.Components.Transform;
import ECS.Components.UIEntity;
import ECS.Components.UIInventory;
import ECS.SystemJob;
import Maths.Vector2;
import Scene.Scene;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * System that executes User interface behabiour
 * 
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class UIEntitiesSystem extends SystemJob{
    
    UIEntity uiEntity;
    ArrayList<Integer> player;
    
    //UIEntity subclasses
    UIInventory uiInventory;
    
    boolean PlayerInventoryBuffer;
    
    //Pointer info
    ArrayList<Integer> mousePointers;
    MousePointer mousePointer;
    boolean pointerOutsideUI = true;
    
    //held item data
    Transform itemTransform; //to update the item position
    Item item; //for the is in inventory boolean
    //the visible boolean in sprite is automatically updated 
    
    public UIEntitiesSystem(Scene scene) {
        super(scene);
        PlayerInventoryBuffer = false;
        
        uiEntity = new UIEntity();
        uiInventory = new UIInventory();
        
        mousePointers = new ArrayList<>();
        mousePointer = new MousePointer();
        
        itemTransform = new Transform();
        item = new Item();
    }

    @Override
    public void update() {
        pointerOutsideUI = true;
        
        for(Integer e: entities){
            uiEntity = scene.entityManager.getEntityComponentInstance(e, uiEntity.getClass());
            
            //System.out.println(mousePointer.position + " " + mousePointer.mouseManager.position.y);
            
            if(uiEntity.UIcollider.contains((int)mousePointer.position.x, (int)mousePointer.position.y) && uiEntity.visible){
                pointerOutsideUI = false;
            }
            
            //render subInterfaces if the parent is visible
            for(UIEntity sub: uiEntity.subInterfacesComponents){
                sub.visible = uiEntity.visible;
            }
                        
            //executes the animation
            if(uiEntity.visible){
                uiEntity.currentFrame = uiEntity.animation[(int)(uiEntity.frameCounter) % uiEntity.animationLenght];
                
                uiEntity.frameCounter += uiEntity.speed;
            }
            
            //input 
            if(uiEntity.name.equals("Player_Inventory")){
                //System.out.println("pI: " + uiEntity.visible);
                if(scene.display.getKeyManager().keys[KeyEvent.VK_X] || scene.display.getKeyManager().keys[KeyEvent.VK_I] || scene.display.getKeyManager().keys[KeyEvent.VK_Q]){
                    if(!PlayerInventoryBuffer){
                        if(uiEntity.visible){
                            uiEntity.visible = false;
                        }else{
                            uiEntity.visible = true;
                        }
                    }
                    PlayerInventoryBuffer = true;
                }else{
                    PlayerInventoryBuffer = false;
                }
            }  
            //if tab is pressed show battle gui
            //if()
        } 
        
        //drop item if outside o UI
        if(pointerOutsideUI){
            if(mousePointer.mouseManager.wasLeftClicked() && mousePointer.heldItem != 0){
                int tempItem = mousePointer.heldItem;
                mousePointer.heldItem = 0;
                itemTransform = scene.entityManager.getEntityComponentInstance(tempItem, itemTransform.getClass());
                item = scene.entityManager.getEntityComponentInstance(tempItem, item.getClass());
                
                itemTransform.position.set(scene.c.UIToWorldCoodinates(mousePointer.position.add(new Vector2(-8, -8))));
                //System.out.println(itemTransform.position.x + " " + itemTransform.position.y);
                item.isInInventory = false;
                
            }
        }
        //System.out.println(pointerOutsideUI);
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(uiEntity.getClass());
        
        mousePointers = scene.entityManager.getEntitiesWithComponents(mousePointer.getClass());
        mousePointer = scene.entityManager.getEntityComponentInstance(mousePointers.get(0), mousePointer.getClass());
        
        for(Integer e: entities){
            //System.out.println(e);
            uiEntity = scene.entityManager.getEntityComponentInstance(e, uiEntity.getClass());
            
            //adds all components instances for all types of uiEntities
            HashSet<UIEntity> instances = new HashSet<>(); //temp set
            for(Integer sub: uiEntity.subInterfaces){
                instances.add(scene.entityManager.getEntityComponentInstance(sub, uiEntity.getClass()));
                instances.add(scene.entityManager.getEntityComponentInstance(sub, uiInventory.getClass()));
                //expand here for all UIEntity subclass...
            }
            instances.remove(null);
            uiEntity.subInterfacesComponents.addAll(instances);
            instances.clear();
            
            //fetch the sprite for the uiEntity and sets it 
            for(int i = 0; i < uiEntity.animationsNames.size(); ++i){
                //System.out.println(uiEntity.animationsNames.get(i));
                uiEntity.animations.add(Assets.animations.get(uiEntity.animationsNames.get(i)));
            }
            
            //System.out.println(uiEntity.animations.size());
            uiEntity.animation = uiEntity.animations.get(0).first;
            uiEntity.animationLenght = uiEntity.animations.get(0).second;
            
            uiEntity.currentFrame = uiEntity.animation[0];
        }
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
    
}
