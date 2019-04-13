/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import ECS.Components.Sprite;
import ECS.Components.Transform;
import ECS.Components.UIEntity;
import ECS.SystemJob;
import Scene.Scene;
import java.awt.Graphics2D;
import java.util.Comparator;
import java.util.PriorityQueue;
import Utility.Pair;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import proyecto_videojuegos.MainThread;

/**
 * 
 *
 * @author pablo
 */
public class RenderSystem extends SystemJob{
    
    private ArrayList<Integer> UIentities; //this are entities that can not be errased or 
    private UIEntity uiEntity;
    
    private Transform transform;
    private Sprite sprite;
    //Pririty Queue of entities to render
    private PriorityQueue <Pair<Transform, Sprite> > queue;
            
    public RenderSystem(Scene scene) {
        super(scene);
        transform = new Transform();
        sprite = new Sprite();
        uiEntity = new UIEntity();
    }

    @Override
    public void update() {
        //adds components Pairs to the queue
        for(Integer e:entities){
            transform = scene.entityManager.getEntityComponentInstance(e, transform.getClass());
            sprite = scene.entityManager.getEntityComponentInstance(e, sprite.getClass());
            queue.add(new Pair(transform, sprite));
        }
    }

    @Override
    public void init() {
      //Fetch entities with the Transform and Sprite components
      entities = scene.entityManager.getEntitiesWithComponents(transform.getClass(), sprite.getClass());
      //Fetch User interface entities //This entities cannot be deleted or added after init()
      UIentities = scene.entityManager.getEntitiesWithComponents(uiEntity.getClass());
              
      queue = new PriorityQueue<>(entities.size(), new myComparator());
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
    
    @Override
    public void render(Graphics2D g) {
        
        //Render all entities with a Transform and Sprite Component
        for(Pair<Transform, Sprite> t : queue){
            if(t.second.visible) {
                g.drawImage(t.second.currentFrame, (int) t.first.position.x,(int) t.first.position.y,t.second.width,t.second.height,null);
            }
        }
        //resets the camera transform
        MainThread.c.tick(g);
        
        //Renders UIEntities aafter rendering the game entities
        for(Integer ui: UIentities){
            uiEntity = scene.entityManager.getEntityComponentInstance(ui, uiEntity.getClass());
            
            if(uiEntity.visible) {
                g.drawImage(uiEntity.currentFrame, (int) uiEntity.position.x,(int) uiEntity.position.y, uiEntity.width, uiEntity.height, null);
            }
        }
        
        /*if (scene.display.getKeyManager().keys[KeyEvent.VK_P]) {
            //Display the inventory
            inventory = new UserInterface(1);

            inventory.render(g);
        }*/
        
        queue.clear();
    }
    
    private class myComparator implements Comparator <Pair<Transform, Sprite> >{

        @Override
        public int compare
        (Pair<Transform, Sprite> o1, Pair<Transform, Sprite > o2) {
            if ((Math.pow(o1.first.position.y, 2) + Math.pow(o1.first.position.z, 2)) > (Math.pow(o2.first.position.y, 2) + Math.pow(o2.first.position.z, 2))) {
                return 1;
            } else {
                return -1;
            }
        }
    }

}
