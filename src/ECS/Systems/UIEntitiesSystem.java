package ECS.Systems;

import Assets.Assets;
import ECS.Component;
import ECS.Components.Item;
import ECS.Components.MousePointer;
import ECS.Components.Sprite;
import ECS.Components.Transform;
import ECS.Components.UIButton;
import ECS.Components.UIEntity;
import ECS.Components.UIInventory;
import ECS.SystemJob;
import ECS.interfaces.UIChild;
import Maths.Vector2;
import Maths.Vector3;
import Scene.Scene;
import java.awt.Rectangle;
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
    Transform uiTransform;
    Sprite uiSprite;
    Sprite childSprite;
    Transform childTransform;
    UIEntity childUIEntity;
    
    //player with an inventory to show
    ArrayList<Integer> player;
    
    //UIEntity subclasses //add as needed
    UIInventory uiInventory;
    UIButton uiButton;
    //**//
    
    boolean PlayerInventoryBuffer;
    
    //Pointer info
    ArrayList<Integer> mousePointers;
    MousePointer mousePointer;
    boolean pointerOutsideUI = true;
    
    //held item data
    Transform itemTransform; //to update the item position
    Item item; //for the is in inventory boolean
    //the _visible boolean in sprite is automatically updated 
    
    public UIEntitiesSystem(Scene scene) {
        super(scene);
        PlayerInventoryBuffer = false;
        
        uiEntity = new UIEntity();
        uiSprite = new Sprite();
        uiTransform = new Transform();
        childSprite = new Sprite();
        childTransform = new Transform();
        childUIEntity = new UIEntity();
                
        uiInventory = new UIInventory();
        uiButton = new UIButton();
        
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
            uiSprite = scene.entityManager.getEntityComponentInstance(e, uiSprite.getClass());
            uiTransform = scene.entityManager.getEntityComponentInstance(e, uiTransform.getClass());
            
            //update UI collider Position
            uiEntity.UIcollider.setLocation((int)uiTransform.position.x, (int)uiTransform.position.y);
            
            //updte if the pointer is outside of any UIEntity
            if(uiEntity.UIcollider.contains((int)mousePointer.position.x, (int)mousePointer.position.y) && uiSprite.visible){
                pointerOutsideUI = false;
            }
            
            //Update the visibility of the childs so they render if the parent is _visible and not If notso.
            for(Integer sub: uiEntity.childs){
                childSprite = scene.entityManager.getEntityComponentInstance(sub, childSprite.getClass());
                childSprite.visible = uiSprite.visible;
            }
                                                
            //input 
            //System.out.println(uiEntity.name);
              
            
            if(uiEntity.name.equals("menu")){ 
                if(scene.display.keyManager.wasPressed[KeyEvent.VK_ESCAPE]){
                    Assets.menu.play();
                    if(uiSprite.visible){                            
                            uiSprite.visible = false;
                    }else{
                        uiSprite.visible = true;
                    }
                }
                //System.out.println(uiEntity.windowState);
            }
            if(uiEntity.name.equals("actives_bar") || uiEntity.name.equals("RL_bar")){ 

                if(scene.display.keyManager.wasPressed[KeyEvent.VK_TAB]){
                    Assets.menu.play();
                    if(uiSprite.visible){                            
                            uiSprite.visible = false;
                    }else{
                        uiSprite.visible = true;
                    }
                }
            }
            
            if(uiEntity.name.equals("Player_Inventory")){ 
                
                //ystem.out.println((int)mousePointer.position.x + " " + (int)mousePointer.position.y);
                if(scene.display.keyManager.wasPressed[KeyEvent.VK_X] || scene.display.keyManager.wasPressed[KeyEvent.VK_I] || scene.display.keyManager.wasPressed[KeyEvent.VK_Q]){
                    Assets.menu.play();
                    if(uiSprite.visible){                            
                            uiSprite.visible = false;
                    }else{
                        uiSprite.visible = true;
                    }
                }
                
                /*if(scene.display.keyManager.up){
                    uiTransform.position = uiTransform.position.add(new Vector3(0,-2,0));
                }
                if(scene.display.keyManager.down){
                    uiTransform.position = uiTransform.position.add(new Vector3(0,2,0));
                }
                if(scene.display.keyManager.right){
                    uiTransform.position = uiTransform.position.add(new Vector3(2,0,0));
                }
                if(scene.display.keyManager.left){
                    uiTransform.position = uiTransform.position.add(new Vector3(-2,0,0));
                }*/
            }
            
            uiSprite.animation = uiSprite.animations.get(uiEntity.windowState).first;
        } 
        
        //drop item if outside o UI
        if(pointerOutsideUI){
            if(mousePointer.mouseManager.wasLeftClicked() && mousePointer.heldItem != 0){
                int tempItem = mousePointer.heldItem;
                mousePointer.heldItem = 0;
                itemTransform = scene.entityManager.getEntityComponentInstance(tempItem, itemTransform.getClass());
                item = scene.entityManager.getEntityComponentInstance(tempItem, item.getClass());
                double originalItemZ = itemTransform.position.z;
                itemTransform.position.set(scene.c.UIToWorldCoodinates(mousePointer.position.add(new Vector2(-8, -8))));
                itemTransform.position.y += originalItemZ;
                //System.out.println(itemTransform.position.x + " " + itemTransform.position.y);
                //Play drop sound
                Assets.pickUp.play();
                item.isInInventory = false;
                
            }
        }
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(uiEntity.getClass());
        
        mousePointers = scene.entityManager.getEntitiesWithComponents(mousePointer.getClass());
        mousePointer = scene.entityManager.getEntityComponentInstance(mousePointers.get(0), mousePointer.getClass());
        
        //for each UIentity, it initializes it
        for(Integer e: entities){
            uiEntity = scene.entityManager.getEntityComponentInstance(e, uiEntity.getClass());
            uiSprite = scene.entityManager.getEntityComponentInstance(e, uiSprite.getClass());
            uiTransform = scene.entityManager.getEntityComponentInstance(e, uiTransform.getClass());
            
            //System.out.println("::::::::::: " + uiEntity.name + " -> " + uiSprite + " : " + uiTransform);
            
            //update the sprite and transform references in the inventoryUI
            uiEntity._uiSprite = uiSprite;
            uiEntity._uiTransform = uiTransform;
            
            //Adds the UI entities childs as childs of the Transform
            //System.out.println(uiTransform.childs + " __ " + uiEntity.childs);
            uiTransform.childs.addAll(uiEntity.childs);
            //adds all components instances for all types of uiEntities to the childs list
            HashSet<UIChild> instances = new HashSet<>(); //temp set
            for(Integer sub: uiEntity.childs){
                //Adds this entity as Transform parent in the Transform of the childs
                childTransform = scene.entityManager.getEntityComponentInstance(sub, childTransform.getClass());
                childTransform.parent = e;
                childUIEntity = scene.entityManager.getEntityComponentInstance(sub, childUIEntity.getClass());
                childUIEntity.parent = e;
                //
                instances.add(scene.entityManager.getEntityComponentInstance(sub, uiEntity.getClass()));
                instances.add(scene.entityManager.getEntityComponentInstance(sub, uiInventory.getClass()));
                instances.add(scene.entityManager.getEntityComponentInstance(sub, uiButton.getClass()));
                //expand here for all UIEntity subclass...
            }
            instances.remove(null);
            uiEntity.UIChildsInterfaces.addAll(instances);
            instances.clear();
            
            
            ///sprite stuff
            
            //uiEntity._visible = uiSprite.visible;
            uiEntity.UIcollider = new Rectangle((int)uiTransform.position.x, (int)uiTransform.position.y, uiSprite.width, uiSprite.height);
        }
        
        
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
    
}
