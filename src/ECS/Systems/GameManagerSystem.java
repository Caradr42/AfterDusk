/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import ECS.SystemJob;
import Scene.Scene;
import java.awt.event.KeyEvent;
import java.util.HashSet;

/**
 * System that executes behabiour not associated with any entity
 * 
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class GameManagerSystem extends SystemJob{

    public GameManagerSystem(Scene scene) {
        super(scene);
    }

    @Override
    public void update() {            
        /*if(scene.display.keyManager.wasPressed[KeyEvent.VK_F]){
            System.out.println("typed");
        }*/
       /* if(scene.display.mouseManager.wasLeftReleased()){
            //System.out.println("leftRelease");
        }*/
    }

    @Override
    public void init() {
        
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
    
}
