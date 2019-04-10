/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import Assets.Assets;
import ECS.Components.Playable;
import ECS.Components.Sprite;
import ECS.Components.Tile;
import ECS.Components.Transform;
import ECS.SystemJob;
import Scene.Scene;
import UI.UserInterface;
import java.awt.Graphics2D;
import java.util.Comparator;
import java.util.PriorityQueue;
import Utility.Pair;
import java.awt.event.KeyEvent;

/**
 * 
 *
 * @author pablo
 */
public class RenderSystem extends SystemJob{
    
    Transform transform;
    public UserInterface inventory;
    Sprite sprite;
    public PriorityQueue <Pair<Transform, Sprite> > queue;
            
    public RenderSystem(Scene scene) {
        super(scene);
        transform = new Transform();
        sprite = new Sprite();
    }

    @Override
    public void update() {
        //To change body of generated methods, choose Tools | Templates.
        for(Integer e:entities){
            transform=scene.entityManager.getEntityComponentInstance(e, transform.getClass());
            sprite = scene.entityManager.getEntityComponentInstance(e, sprite.getClass());
            queue.add(new Pair(transform, sprite));
        }
    }


    @Override
    public void init() {
      entities = scene.entityManager.getEntitiesWithComponents(transform.getClass(), sprite.getClass());
      queue = new PriorityQueue<>(entities.size(), new myComparator());
      
    }

    @Override
    public void onCreate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onDestroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
        @Override
    public void render(Graphics2D g) {
        //System.out.println(entities.get(0));
        for(Pair<Transform, Sprite> t : queue){
            g.drawImage(t.second.bi, (int) t.first.position.x,(int) t.first.position.y,t.second.width,t.second.height,null);
           //g.drawRect((int) t.position.x,(int) t.position.y,16,16);
        } 
        
            if (scene.display.getKeyManager().keys[KeyEvent.VK_P]) {
                //Display the inventory
                inventory = new UserInterface(1);
                
                inventory.render(g);
            }
        
    }
    
    public class myComparator implements Comparator <Pair<Transform, Sprite> >

    {

        @Override
        public int compare
        (Pair<Transform, Sprite> o1, Pair<Transform, Sprite > o2
        
            ) {
         if ((Math.pow(o1.first.position.y, 2) + Math.pow(o1.first.position.z, 2)) > (Math.pow(o2.first.position.y, 2) + Math.pow(o2.first.position.z, 2))) {
                return 1;
            } else {
                return -1;
            }
        }

    }

}
