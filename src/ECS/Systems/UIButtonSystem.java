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
import static ECS.SystemJob.scene;
import Scene.Scene;
import java.awt.event.WindowEvent;
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
    
    public UIButtonSystem(Scene scene, boolean active) {
        super(scene, active);
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
            
            
            
            if(uiButton.name.equals("exitButton") && uiButton.buttonPressed){
                scene.display.jframe.dispatchEvent(new WindowEvent(scene.display.jframe, WindowEvent.WINDOW_CLOSING));
            }
            
            if(uiButton.name.equals("continueButton") && uiButton.buttonPressed){
                uiButton.buttonPressed =false;
                UIEntity parentUIEntity = scene.entityManager.getEntityComponentInstance(uiEntity.parent, UIEntity.class);
                parentUIEntity._uiSprite.visible = false;
                GameManagerSystem.gameStarted = true;
            }
            
            if(uiButton.buttonPressed){
                parentUIEntity = scene.entityManager.getEntityComponentInstance(uiEntity.parent, parentUIEntity.getClass());
                parentUIEntity.window = uiButton.parentState;
            }
            
            /*if(uiEntity.name.equals("newGameButton")){
                //System.out.println(uiButton.buttonVisible);
            }*/
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
