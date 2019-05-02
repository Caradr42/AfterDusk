 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import ECS.Components.Player;
import ECS.Components.Transform;
import ECS.SystemJob;
import Maths.Vector2;
import Scene.Scene;
import java.awt.event.KeyEvent;
import Assets.Assets;
import ECS.Components.Playable;
import ECS.Components.Sprite;
import Maths.Vector3;
import javax.swing.JFrame;

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
    int width;
    int height;
    int state;
    boolean fullScreen = false;
    boolean visibleDialogBlock = false;
    
    public GameManagerSystem(Scene scene, boolean active) {
        super(scene, active);
    }

    @Override
    public void update() {     
        
        if(scene.display.keyManager.wasPressed[KeyEvent.VK_F12]){
            if(!fullScreen){
                fullScreen = true;
                scene.display.jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
                scene.display.jframe.setUndecorated(true);
            }else{
                fullScreen = false;
                scene.display.jframe.setExtendedState(JFrame.NORMAL);
                scene.display.jframe.setSize(scene.display.width, scene.display.height);
                scene.display.jframe.setUndecorated(false);
            }
        }
        
        //executes ony when the  game starts
        //it activates the Systems necesary for GamePlay
        if(gameStarted){
            scene.c.ortogonalPosition.set(scene.entityManager.getEntityComponentInstance(scene.entityManager.getEntitiesWithComponents(Player.class).get(0), Transform.class).position.toVector2().scalar(scene.c.scale).sub(new Vector2(scene.display.width/2 - 16,scene.display.height/2 + 32)));

            for(SystemJob sj: scene.systemJobManager.systemsList){
                sj.active = true;
            }
            Assets.houseTheme.stop();
            Assets.fatherTheme.play();
            gameStarted = false;
            gameRunning = true;
            
        }
        if(gameRunning){
            //System.out.println(ConversationSystem.visibleDialogBox);
            //game pause if in game menu
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
                        //&& sj.getClass() != (ConversationSystem.class)
                        //Expand here if any other system is necesary when the game is paused
                        ){
                    //System.out.println(sj.getClass());
                    if(sj.active){
                        sj.active = false;
                    }else{
                        sj.active = true;
                    }
                }
              }
            }else if(ConversationSystem.visibleDialogBox && !visibleDialogBlock){ //game puse if in conversation
                
                visibleDialogBlock = true;
                for(SystemJob sj: scene.systemJobManager.systemsList){
                if(sj.getClass() != (RenderSystem.class) 
                        && sj.getClass() != (GameManagerSystem.class) 
                        && sj.getClass() != (UIEntitiesSystem.class) 
                        && sj.getClass() != (UIButtonSystem.class) 
                        && sj.getClass() != (UITextSystem.class)
                        //&& sj.getClass() != (SpriteSystem.class)
                        && sj.getClass() != (TransformSystem.class)
                        && sj.getClass() != (MousePointerSystem.class)
                        && sj.getClass() != (ConversationSystem.class)
                        //Expand here if any other system is necesary when the game is paused
                        ){
                    //System.out.println(sj.getClass());
                    
                        sj.active = false;
                    
                }
              }
            }else if(!ConversationSystem.visibleDialogBox){
                if(visibleDialogBlock){
                    for(SystemJob sj: scene.systemJobManager.systemsList){
                    if(sj.getClass() != (RenderSystem.class) 
                            && sj.getClass() != (GameManagerSystem.class) 
                            && sj.getClass() != (UIEntitiesSystem.class) 
                            && sj.getClass() != (UIButtonSystem.class) 
                            && sj.getClass() != (UITextSystem.class)
                            //&& sj.getClass() != (SpriteSystem.class)
                            && sj.getClass() != (TransformSystem.class)
                            && sj.getClass() != (MousePointerSystem.class)
                            && sj.getClass() != (ConversationSystem.class)
                            //Expand here if any other system is necesary when the game is paused
                            ){
                        //System.out.println(sj.getClass());

                            sj.active = true;

                        }
                    }
                    visibleDialogBlock = false;
                }
            }
        }else{
            scene.c.ortogonalPosition.x ++;
        }
        
        //if the player has no life
        Integer player = scene.entityManager.getEntitiesWithComponents(Player.class).get(0);
        
        Playable playable = scene.entityManager.getEntityComponentInstance(player, Playable.class);
        
        Sprite sprite = scene.entityManager.getEntityComponentInstance(player, Sprite.class);
        
        Transform transform = scene.entityManager.getEntityComponentInstance(player, Transform.class);
        
        if(playable.hp <= 0 ) {
            playable.isAlive = true;
            playable.hp = playable.maxHp;
            playable.energy = playable.maxEnergy;
            sprite.visible = true;
            transform.position = new Vector3(100, 100, 32);
        }
    }

    @Override
    public void init() {
        Assets.houseTheme.play();

    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
    
}
