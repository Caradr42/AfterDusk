
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import ECS.Components.MousePointer;
import ECS.Components.Sprite;
import ECS.Components.Transform;
import ECS.Components.UIEntity;
import ECS.Components.WorldEntity;
import ECS.SystemJob;
import Scene.Scene;
import java.awt.Graphics2D;
import java.util.Comparator;
import java.util.PriorityQueue;
import Utility.Pair;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import proyecto_videojuegos.MainThread;

/**
 * System that renders all entities with Sprite and Transform Components
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class RenderSystem extends SystemJob{
    
    private ArrayList<Integer> UIentities; //this are entities that are UI elements
    private ArrayList<Integer> mousePointers;
    private MousePointer mousePointer;
    
    //archetype of the enitites lits
    private Transform transform;
    private Sprite sprite;
    private WorldEntity worldEntity;
    
    //archetype if the UI Entities list
    private UIEntity uiEntity;
    
    //Pririty Queue of entities to render
    private PriorityQueue <Pair<Transform, Sprite> > queue;
            
    public RenderSystem(Scene scene) {
        super(scene);
        transform = new Transform();
        sprite = new Sprite();
        worldEntity = new WorldEntity();
        
        uiEntity = new UIEntity();
        mousePointer = new MousePointer();
    }

    @Override
    public void update() {
        //adds components Pairs to the queue
        for(Integer e: entities){
            transform = scene.entityManager.getEntityComponentInstance(e, transform.getClass());
            sprite = scene.entityManager.getEntityComponentInstance(e, sprite.getClass());
            queue.add(new Pair(transform, sprite));
        }
    }

    @Override
    public void init() {
      //Fetch entities with the Transform and Sprite components
      entities = scene.entityManager.getEntitiesWithComponents(transform.getClass(), sprite.getClass(), worldEntity.getClass());
      //Fetch User interface entities //This entities cannot be deleted or added after init()
      UIentities = scene.entityManager.getEntitiesWithComponents(uiEntity.getClass());
      //Fetch mouse pointers
      mousePointers = scene.entityManager.getEntitiesWithComponents(mousePointer.getClass());
      
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
        
        //Render all entities with a Transform and Sprite Component (but no UIEntity)
        for(Pair<Transform, Sprite> t : queue){
            if(t.second.visible) {
                g.drawImage(t.second.currentFrame, (int) t.first.position.x,(int) (t.first.position.y - t.first.position.z) ,t.second.width,t.second.height,null);
            }
        }
        
        AffineTransform at = new AffineTransform();
        at.setTransform(4, 0, 0, 4, 1, 0);
        g.setTransform(at);  
        
        //Renders UIEntities after rendering the game entities
        for(Integer ui: UIentities){
            uiEntity = scene.entityManager.getEntityComponentInstance(ui, uiEntity.getClass());
            
            if(uiEntity._uiSprite.visible && uiEntity.mainUI ) {
                //g.drawImage(uiEntity.currentFrame, (int) uiEntity.position.x,(int) uiEntity.position.y, uiEntity.width, uiEntity.height, null);
                uiEntity.UIRender(g, scene);
            }
        }
        
        //render mouse pointer
        for(Integer mp: mousePointers){
            mousePointer = scene.entityManager.getEntityComponentInstance(mp, mousePointer.getClass());
            //draws held item
            mousePointer.UIRender(g, scene);
            //draws tthe poiter sprite if it has one
            if(scene.entityManager.hasComponent(mp, sprite.getClass())){
                sprite = scene.entityManager.getEntityComponentInstance(mp, sprite.getClass());
                g.drawImage(sprite.currentFrame,(int)mousePointer.position.x, (int)mousePointer.position.y, sprite.width, sprite.height, null);
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
        (Pair<Transform, Sprite> p1, Pair<Transform, Sprite > p2) {
            //System.out.println(p1.second.name + " : " + p1.first.position.z);
            if(p1.first.position.z > p2.first.position.z){
                return 1;
            }else if (p1.first.position.z < p2.first.position.z){
                return -1;
            }else return 0;
        }
    }

}