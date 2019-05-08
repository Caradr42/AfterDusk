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
import static ECS.Systems.GameManagerSystem.fullScreen;
import Scene.Scene;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import proyecto_videojuegos.MainThread;

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
                if(mousePointer.mouseManager.wasLeftClick){
                    uiButton.buttonPressed = true;
                }else{
                     uiButton.buttonPressed = false;
                }
            }else{
                uiButton.buttonVisible = false;
            }
            
            
            
            if(uiButton.name.equals("exitButton") && uiButton.buttonPressed){
                Assets.Assets.selection.play();
                scene.display.jframe.dispatchEvent(new WindowEvent(scene.display.jframe, WindowEvent.WINDOW_CLOSING));
            }
            
            if(uiButton.name.equals("fullScreen") && uiButton.buttonPressed){
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
            
            if(uiButton.name.equals("showFPS") && uiButton.buttonPressed){
                if(MainThread.showTPS){
                    MainThread.showTPS = false;
                }else{
                    MainThread.showTPS = true;
                }
            }
            
            if(uiButton.name.equals("showDebug") && uiButton.buttonPressed){
                if(MainThread.showColliders){
                    MainThread.showColliders = false;
                }else{
                    MainThread.showColliders = true;
                }
                
                if(RenderSystem.debugRender){
                    RenderSystem.debugRender = false;
                }else{
                    RenderSystem.debugRender = true;
                }
            }
            
            /*if(uiButton.name.equals("fullScreen")){
                System.out.println("button = " + uiButton.buttonPressed);
            }*/
            
            if(uiButton.name.equals("continueButton") && uiButton.buttonPressed){
                uiButton.buttonPressed =false;
                Assets.Assets.selection.play();
                UIEntity parentUIEntity = scene.entityManager.getEntityComponentInstance(uiEntity.parent, UIEntity.class);
                parentUIEntity._uiSprite.visible = false;
                GameManagerSystem.gameStarted = true;
                
                //Load game from DataBase
                try {
                    scene.entityManager.selectDataBase(scene.entityManager);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PlayerSystem.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(PlayerSystem.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(PlayerSystem.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(uiButton.name.equals("saveButton") && uiButton.buttonPressed){
                uiButton.buttonPressed =false;
                Assets.Assets.selection.play();
                UIEntity parentUIEntity = scene.entityManager.getEntityComponentInstance(uiEntity.parent, UIEntity.class);
                parentUIEntity._uiSprite.visible = false;
                GameManagerSystem.gameStarted = true;
                try {
                    //call function to load to data base
                    scene.entityManager.loadDatabase();
                } catch (IOException ex) {
                    Logger.getLogger(PlayerSystem.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PlayerSystem.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(PlayerSystem.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(uiButton.buttonPressed){
                Assets.Assets.selection.play();
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
