/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import ECS.Components.MousePointer;
import ECS.Components.UIButton;
import ECS.SystemJob;
import Scene.Scene;
import java.util.ArrayList;

/**
 *
 * @author carlo
 */
public class UIButtonSystem extends SystemJob{
    
    UIButton uiButton;
    
    MousePointer mousePointer;
    
    //ArrayList<MousePointer> mousePointers;
    
    public UIButtonSystem(Scene scene) {
        super(scene);
        uiButton = new UIButton();
        mousePointer = new MousePointer();
    }

    @Override
    public void update() {
        for(Integer e: entities){
            uiButton = scene.entityManager.getEntityComponentInstance(e, uiButton.getClass());
            if(uiButton.UIcollider.contains((int)mousePointer.position.x, (int)mousePointer.position.y)){
                uiButton.visible = false;
            }else{
                uiButton.visible = true;
            }
            //System.out.println(uiButton.visible);
        }
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(uiButton.getClass());
        mousePointer = scene.entityManager.getEntityComponentInstance(scene.entityManager.getEntitiesWithComponents(mousePointer.getClass()).get(0), mousePointer.getClass());
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
    
}
