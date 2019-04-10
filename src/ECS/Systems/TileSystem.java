/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import Assets.Assets;
import ECS.Components.Playable;
import ECS.Components.Tile;
import ECS.SystemJob;
import Scene.Scene;
import java.awt.Graphics2D;
import java.util.Comparator;
import java.util.PriorityQueue;
import javafx.util.Pair;

/**
 * 
 *
 * @author pablo
 */
public class TileSystem extends SystemJob{
    
    Tile tile;
    public PriorityQueue <Tile> queue;
            
    public TileSystem(Scene scene) {
        super(scene);
        tile = new Tile();
    }

    @Override
    public void update() {
        //To change body of generated methods, choose Tools | Templates.
        for(Integer e:entities){
            tile=scene.entityManager.getEntityComponentInstance(e, tile.getClass());
            queue.add(tile);
        }
    }


    @Override
    public void init() {
      entities = scene.entityManager.getEntitiesWithComponents(tile.getClass());
      queue = new PriorityQueue<Tile>(entities.size(), new myComparator());
      for(Integer e:entities){
            tile=scene.entityManager.getEntityComponentInstance(e, tile.getClass());
            for(int i=0;i<tile.topTextures.size();i++){
                tile.topTextureRef.add(i, Assets.animations.get(tile.topTextures.get(i))[0]);
            }
        }
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
        for(Tile t : queue){
            g.drawImage(t.topTextureRef.get(0), (int) t.position.x,(int) t.position.y,16,16,null);
           //g.drawRect((int) t.position.x,(int) t.position.y,16,16);
        } 
        
    }
    
    
    
 public class myComparator implements Comparator <Tile> {

    
        @Override
        public int compare(Tile o1, Tile o2) {
         if((Math.pow(o1.position.y , 2) + Math.pow(o1.position.z , 2)) > (Math.pow(o2.position.y , 2) + Math.pow(o2.position.z , 2))){
        return 1;
    } else {
        return -1;
    }
        }
     
     
 }
    
}
