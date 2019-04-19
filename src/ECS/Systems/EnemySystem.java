/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import ECS.Components.Playable;
import ECS.Components.Player;
import ECS.SystemJob;
import Scene.Scene;
import java.util.ArrayList;

/**
 *
 * @author pablo
 */
public class EnemySystem extends SystemJob{

    ArrayList<Playable> arrPlayable;
    Player player;
    
    
    public EnemySystem(Scene scene) {
        super(scene);
        arrPlayable = new ArrayList<>();
        player = new Player();
    }

    @Override
    public void update() {
       
    }

    @Override
    public void init() {
    }

    @Override
    public void onCreate() {
       
    }

    @Override
    public void onDestroy() {
        
    }
    
    
    
}
