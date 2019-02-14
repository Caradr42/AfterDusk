/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene.Scenes;

import ECS.Components.Sprite;
import ECS.Entity;
import Scene.Scene;

/**
 *
 * @author carlo
 */
public class MainWorld extends Scene{
    
    
    
    @Override
    protected void addEntities() {
        Sprite spriteComp = new Sprite("sprite_1", 100, 100,"/Resources/Images/coso.png");
        Entity e = entityManager.createEntity("COSO");
        entityManager.addComponetToEntity(e,spriteComp);
    }
    
}
