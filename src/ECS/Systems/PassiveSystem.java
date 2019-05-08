/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import ECS.Components.Pasive;
import ECS.Components.Tool;
import ECS.SystemJob;
import Scene.Scene;
import java.util.ArrayList;

/**
 *
 * @author tanya
 */
public class PassiveSystem extends SystemJob {
    
    Tool tool;
    ArrayList<Integer> idTools;
    Pasive pasive;

    public PassiveSystem(Scene scene, boolean active) {
        super(scene, active);
    }

    @Override
    public void update() {
        
    }

    @Override
    public void init() {
        tool = new Tool();

    idTools = scene.entityManager.getEntitiesWithComponents(Tool.class, Pasive.class);
    
    for (Integer t : idTools){
        for(Class c : Pasive.pasivesSet){
            tool = scene.entityManager.getEntityComponentInstance(t, Tool.class);
            if(scene.entityManager.hasComponent(t, c)){
                pasive = (Pasive)scene.entityManager.getEntityComponentInstance(t, c);
                tool.toolPasive = pasive; // se le añade la pasiva al tool

            }
        }
    }
        
               
       
       
    }

    @Override
    public void onCreate() {
        
    }

    @Override
    public void onDestroy() {
        
    }
    
}
