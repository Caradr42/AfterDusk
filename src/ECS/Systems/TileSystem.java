/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class TileSystem extends SystemJob{

    Tile tile;
    Transform tileTransform;
    
    public TileSystem(Scene scene, boolean active) {
        super(scene, active);
        tile = new Tile();
        tileTransform = new Transform();
    }

    @Override
    public void update() {
        //System.err.println("TileSyUpdate Thread: " + Thread.currentThread());
        
        /*Sprite grassSideSprite = new Sprite("grassSide", true, 16, 16, 10, new ArrayList<>(Arrays.asList("grassSide")));
        
        scene.entityManager.createEntityWithComponents("grass",
                new Tile("grass2", grassSideSprite, grassSideSprite),
                new Transform(new Vector3(-16, -16, 32)),
                grassSideSprite,
                new WorldEntity()
        //new Sprite("grass", true, 16, 16, 10, new ArrayList<>(Arrays.asList("grass")))
        );*/
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(tile.getClass());
        
        for(Integer e: entities){
            //System.out.println("aaa");
            tile = scene.entityManager.getEntityComponentInstance(e, tile.getClass());
            tileTransform =scene.entityManager.getEntityComponentInstance(e, tileTransform.getClass());
            //System.out.println(tile.sideSprite);
            
            /*Entity gg = scene.entityManager.createEntityWithComponents(tile.name + "_side", 
                    new Transform(new Vector3(0,0,-16), e),
                    tile.sideSprite,
                    new WorldEntity() 
                );*/
            
            /*if(e == -2147483621){
                System.out.println("child: " + gg.getID());
            }*/
            //System.out.println("sp: " + scene.entityManager.getEntityComponentInstance(gg, tile.sideSprite.getClass()));
        }
        //System.err.println("TileSyInit Thread: " + Thread.currentThread());
        Sprite grassSideSprite = new Sprite("grassSide", true, 16, 16, 0, new ArrayList<>(Arrays.asList("grass")));
        
        Entity ent = scene.entityManager.createEntityWithComponents("grassSide",
                new Tile("grass2", grassSideSprite, grassSideSprite),
                new Transform(new Vector3(-32, -32, 32)),
                grassSideSprite,
                new WorldEntity()
        //new Sprite("grass", true, 16, 16, 10, new ArrayList<>(Arrays.asList("grass")))
        );
        
        System.out.println(ent.getID());
    }

    @Override
    public void onCreate() {
        
    }

    @Override
    public void onDestroy() {
    }
    
}
