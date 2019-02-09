/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import java.lang.System;
import ECS.EntityManager;
import ECS.SystemJob;
import ECS.Entity;
import ECS.Components.RenderComponent;
import java.util.ArrayList;
/**
 *
 * @author carlo
 */
public class GameManager extends SystemJob{
    
    RenderComponent render;
    ArrayList<Entity> entities;
    
    public GameManager(EntityManager entityManager) {
        super(entityManager);
        render = new RenderComponent("");
        //entities = entityManager.getAllEntitiesPosessingComponentOfClass(render.getClass());
    }

    @Override
    public void Update() {
        entities = entityManager.getAllEntitiesPosessingComponentOfClass(render.getClass());
        //System.out.println(entities.get(0));
        for(Entity e : entities){
            render = entityManager.getEntityComponentFromClass(e, render.getClass());
            //entityManager.printEntities();
            //entityManager.addComponetToEntity(e, render);
            System.out.println(render.test);
        }     
    }  
    
}
