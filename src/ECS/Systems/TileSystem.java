package ECS.Systems;

import ECS.Components.Sprite;
import ECS.Components.Tile;
import ECS.Components.Transform;
import ECS.Components.WorldEntity;
import ECS.Entity;
import ECS.SystemJob;
import Maths.Vector3;
import Scene.Scene;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Manages the tile System
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class TileSystem extends SystemJob{

    Tile tile;
    Transform tileTransform;
    
    /**
     * Constructor
     * @param scene
     * @param active 
     */
    public TileSystem(Scene scene, boolean active) {
        super(scene, active);
        tile = new Tile();
        tileTransform = new Transform();
    }

    @Override
    public void update() {
        
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(tile.getClass());
        
        for(Integer e: entities){
            
            tile = scene.entityManager.getEntityComponentInstance(e, tile.getClass());
            tileTransform =scene.entityManager.getEntityComponentInstance(e, tileTransform.getClass());   
        }
       
        Sprite grassSideSprite = new Sprite("grassSide", true, 16, 16, 0, new ArrayList<>(Arrays.asList("grass")));
        
        Entity ent = scene.entityManager.createEntityWithComponents("grassSide",
                new Tile("grass2", grassSideSprite, grassSideSprite),
                new Transform(new Vector3(-32, -32, 32)),
                grassSideSprite,
                new WorldEntity()
        );
        
      
    }

    @Override
    public void onCreate() {
        
    }

    @Override
    public void onDestroy() {
    }
    
}
