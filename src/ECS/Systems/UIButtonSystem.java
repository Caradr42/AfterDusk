/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import ECS.Components.UIButton;
import ECS.SystemJob;
import Scene.Scene;

/**
 *
 * @author carlo
 */
public class UIButtonSystem extends SystemJob{
    
    UIButton uiButton;
    
    public UIButtonSystem(Scene scene) {
        super(scene);
        uiButton = new UIButton();
    }

    @Override
    public void update() {
        
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(uiButton.getClass());
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
    
}
