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
    public ActiveSystem(Scene scene) {
        super(scene);
    }

    @Override
    public void update() {
        
    }

    @Override
    public void init() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    tool = new Tool();
    idTools = scene.entityManager.getEntitiesWithComponents(tool.getClass());
    
    for (int i = 0 ; i < idTools.size(); i++){
        for (int j = 0 ; j < Active.activesSet.size() ; j++){
            
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
