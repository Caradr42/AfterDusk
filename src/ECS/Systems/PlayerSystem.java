package ECS.Systems;

import ECS.Components.Playable;
import ECS.Components.Player;
import ECS.SystemJob;
import Scene.Scene;
import java.awt.Graphics2D;


/**
 *
 * @author carlo
 */
public class PlayerSystem extends SystemJob{
    
    Player player;
    Playable playable;
    
    public PlayerSystem(Scene scene) {
        super(scene);
    }

    @Override
    public void update() {
        for(Integer e : entities){
            player = scene.entityManager.getEntityComponentInstance(e, player.getClass());
            playable = scene.entityManager.getEntityComponentInstance(e, playable.getClass());
                        
            if(scene.display.getKeyManager().right){
                playable.position.x = playable.position.x + 2;//+ 100 * MainThread.deltaTime);
            }
            
            if(scene.display.getKeyManager().left){
                playable.position.x = playable.position.x -2;//- 100 * MainThread.deltaTime);
            }
            
            if(scene.display.getKeyManager().up){
                playable.position.y = playable.position.y -2;//- 100 * MainThread.deltaTime);
            }
            
            if(scene.display.getKeyManager().down){
                playable.position.y = playable.position.y +2;//+ 100 * MainThread.deltaTime);
            }
        }
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(player.getClass());
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
    
    @Override
    public void render(Graphics2D g) {
        
    }
    
}
