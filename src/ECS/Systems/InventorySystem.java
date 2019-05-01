package ECS.Systems;

import ECS.Components.Player;
import ECS.SystemJob;
import Scene.Scene;

/**
 *
 * @author carlo
 */
public class InventorySystem extends SystemJob{
    
    Player player;
    
    public InventorySystem(Scene scene, boolean active) {
        super(scene, active);
        player = new Player();
    }

    @Override
    public void update() {
    }

    @Override
    public void init() {
       // player = scene.entityManager.
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
    
}
