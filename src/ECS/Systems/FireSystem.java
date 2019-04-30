/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import ECS.Components.Fire;
import ECS.Components.Playable;
import ECS.Components.Player;
import ECS.SystemJob;
import Scene.Scene;
import java.util.ArrayList;

/**
 *Atacas al playable
 * @author tanya
 */
public class FireSystem extends SystemJob {
    
    //Playable enemy;
    private int playerID;
    private Player player;    
    Playable playable;
    Fire fire;
    
    //identidades que almacenen componente fugego
    ArrayList<Integer> arrID = new ArrayList<>();
    ArrayList<Integer> arrPlayables = new ArrayList<>();
    
    
    //Get entities 
    
    public FireSystem(Scene scene) {
        super(scene);
    }
    

    //m+rtodo estatico execute actives donde modificas las activas 
    //usas el execute attack, 
    
    public static void executeActives(int tool, int entity) {
        
        
    }
    
    @Override
    public void update() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    //for 
    }

    @Override
    public void init() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    arrPlayables = scene.entityManager.getEntitiesWithComponents(playable.getClass());
    arrID = scene.entityManager.getEntitiesWithComponents(fire.getClass());
    
    //añadirse a sì mismo al tool
       
    }

    @Override
    public void onCreate() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onDestroy() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
