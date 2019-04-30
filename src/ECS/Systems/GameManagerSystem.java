 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import ECS.Component;
import ECS.Components.Sprite;
import ECS.Components.Transform;
import ECS.Components.UIEntity;
import ECS.SystemJob;
import Scene.Scene;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
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
    
    public static volatile boolean gameRunning = false;
    public static volatile boolean gameStarted = false;
    
    public GameManagerSystem(Scene scene, boolean active) {
        super(scene, active);
    }

    @Override
    public void update() {     
        
        /*if(scene.display.keyManager.wasPressed[KeyEvent.VK_F12]){
            scene.display.jframe.dispatchEvent(new WindowEvent(scene.display.jframe, WindowEvent.WINDOW_CLOSING));
        }*/
        
        //executes ony when the  game starts
        //it activates the Systems necesary for GamePlay
        if(gameStarted){
            
            gameStarted = false;
            gameRunning = true;
        }
        if(gameRunning){
            
            if(scene.display.keyManager.wasPressed[KeyEvent.VK_ESCAPE]){
              for(SystemJob sj: scene.systemJobManager.systemsList){
                if(sj.getClass() != (RenderSystem.class) 
                        && sj.getClass() != (GameManagerSystem.class) 
                        && sj.getClass() != (UIEntitiesSystem.class) 
                        && sj.getClass() != (UIButtonSystem.class) 
                        && sj.getClass() != (UITextSystem.class)
                        && sj.getClass() != (SpriteSystem.class)
                        && sj.getClass() != (TransformSystem.class)
                        && sj.getClass() != (MousePointerSystem.class)
                        //Expand here if any other system is necesary when the game is paused
                        ){
                    System.out.println(sj.getClass());
                    if(sj.active){
                        sj.active = false;
                    }else{
                        sj.active = true;
                    }
                }
              }
            }
        }
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
