/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import ECS.Components.MousePointer;
import ECS.Components.Sprite;
import ECS.Components.Transform;
import ECS.Components.UIButton;
import ECS.Components.UIEntity;
import ECS.SystemJob;
import Scene.Scene;
import java.util.ArrayList;

/**
 *
 * @author carlo
 */
public class UIButtonSystem extends SystemJob{
    
    UIButton uiButton;
    Sprite buttonSprite;
    Transform buttonTransform;
    
    UIEntity uiEntity;
    
    UIEntity parentUIEntity;
    
    MousePointer mousePointer;
    
    //ArrayList<MousePointer> mousePointers;
    
    public UIButtonSystem(Scene scene) {
        super(scene);
        uiButton = new UIButton();
        mousePointer = new MousePointer();
        buttonSprite = new Sprite();
        buttonTransform = new Transform();
        uiEntity = new UIEntity();
        parentUIEntity = new UIEntity();
    }

    @Override
    public void update() {
        for(Integer e: entities){
            uiButton = scene.entityManager.getEntityComponentInstance(e, uiButton.getClass());
            uiEntity = scene.entityManager.getEntityComponentInstance(e, uiEntity.getClass());
            
            buttonSprite = scene.entityManager.getEntityComponentInstance(e, buttonSprite.getClass());
            buttonTransform = scene.entityManager.getEntityComponentInstance(e, buttonTransform.getClass());
            
            //uiButton._buttonSprite = buttonSprite;
           // uiButton._buttonTransform = buttonTransform;
            
            //System.out.println(" --> " + uiEntity.UIcollider + " --> " + mousePointer.position.x + " " + mousePointer.position.y);
            if(uiEntity.UIcollider.contains((int)mousePointer.position.x, (int)mousePointer.position.y) && buttonSprite.visible){
                uiButton.buttonVisible = true;
                if(mousePointer.mouseManager.left){
                    uiButton.buttonPressed = true;
                }else{
                     uiButton.buttonPressed = false;
                }
            }else{
                uiButton.buttonVisible = false;
            }
            
            if(uiButton.buttonPressed){
                //System.out.println(uiEntity.parent);
                parentUIEntity = scene.entityManager.getEntityComponentInstance(uiEntity.parent, parentUIEntity.getClass());
                //System.out.println(parentUIEntity);
                parentUIEntity.windowState = uiButton.parentState;
            }
            //System.out.println(uiButton.buttonPressed);
        }
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(uiButton.getClass());
        for(Integer e: entities){
            uiButton = scene.entityManager.getEntityComponentInstance(e, uiButton.getClass());
            uiEntity = scene.entityManager.getEntityComponentInstance(e, uiEntity.getClass());
            buttonSprite = scene.entityManager.getEntityComponentInstance(e, buttonSprite.getClass());
            buttonTransform = scene.entityManager.getEntityComponentInstance(e, buttonTransform.getClass());
            
            uiButton._buttonSprite = buttonSprite;
            uiButton._buttonTransform = buttonTransform;
        }
        mousePointer = scene.entityManager.getEntityComponentInstance(scene.entityManager.getEntitiesWithComponents(mousePointer.getClass()).get(0), mousePointer.getClass());
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
    
}
