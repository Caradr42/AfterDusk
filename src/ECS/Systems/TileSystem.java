/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import ECS.Components.Tile;
import ECS.SystemJob;
import Scene.Scene;
import Assets.Assets;

/**
 *
 * @author pablo
 */
public class TileSystem extends SystemJob {
    
    Tile tile;

    public TileSystem(Scene scene) {
        super(scene);
        tile = new Tile();
    }

    @Override
    public void update() {
      
    }
    
    

        
    @Override
    public void init() {
      entities = scene.entityManager.getEntitiesWithComponents(tile.getClass());
      /*for(Integer e:entities){
            tile=scene.entityManager.getEntityComponentInstance(e, tile.getClass());
            for(int i=0;i<tile.topTextures.size();i++){
                //tile.topTextureRef.add(i, Assets.animations.get(tile.topTextures.get(i))[0]);
            }
        }*/
    }

    @Override
    public void onCreate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onDestroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
