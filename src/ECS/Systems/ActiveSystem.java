/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import ECS.Components.Active;
import ECS.Components.Tool;
import ECS.SystemJob;
import Scene.Scene;
import java.util.ArrayList;
import javax.swing.plaf.synth.Region;

/**
 *
 * @author tanya
 */
public class ActiveSystem extends SystemJob{

    
    ArrayList<Integer> idTools;
    Tool tool; 
    Active active;
    
    public ActiveSystem(Scene scene, boolean isActive) {
        super(scene, isActive);
    }

    @Override
    public void update() {
        
    }

    @Override
    public void init() {
    tool = new Tool();

    idTools = scene.entityManager.getEntitiesWithComponents(Tool.class);
    
    for (Integer t : idTools){
        for(Class c : Active.activesSet){
            tool = scene.entityManager.getEntityComponentInstance(t, Tool.class);
            if(scene.entityManager.hasComponent(t, c)){
                active = (Active)scene.entityManager.getEntityComponentInstance(t, c);
                
                tool.arrActives.add(active);
            }
        }
    }
        
    }

    @Override
    public void onCreate() {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onDestroy() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
