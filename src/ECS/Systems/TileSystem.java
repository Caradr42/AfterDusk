/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import ECS.Components.Playable;
import ECS.Components.Tile;
import ECS.SystemJob;
import Scene.Scene;
import java.awt.Graphics2D;

/**
 * 
 *
 * @author pablo
 */
public class TileSystem extends SystemJob{
    
    Tile tile;
    
    public TileSystem(Scene scene) {
        super(scene);
        tile = new Tile();
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fixedUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init() {
      entities = scene.entityManager.getEntitiesWithComponents(tile.getClass());
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
        for(Integer e : entities){
            tile = scene.entityManager.getEntityComponentInstance(e, tile.getClass());
            
            
            
        }
        
    }
    

int compare(Tile o1, Tile o2){
    o1.
    
    return 
   //a negative integer, zero, 
  //or a positive integer as the first argument is less than, equal to, or greater than the second
    }
    
    
    
    
}
