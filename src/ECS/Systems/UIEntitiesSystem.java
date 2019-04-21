package ECS.Systems;

import Assets.Assets;
import ECS.Components.UIEntity;
import ECS.Components.UIInventory;
import ECS.SystemJob;
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
    
    public UIEntitiesSystem(Scene scene) {
        super(scene);
        PlayerInventoryBuffer = false;
        
        uiEntity = new UIEntity();
        uiInventory = new UIInventory();
    }

    @Override
    public void update() {
             
        for(Integer e: entities){
            uiEntity = scene.entityManager.getEntityComponentInstance(e, uiEntity.getClass());
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
                    if(PlayerInventoryBuffer == false){
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
        } 
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(uiEntity.getClass());
        
        
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
