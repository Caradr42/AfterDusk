/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Systems;

import ECS.SystemJob;
import Scene.Scene;

/**
 *
 * @author carlo
 */
public class GameManagerSystem extends SystemJob{

    public GameManagerSystem(Scene scene) {
        super(scene);
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
