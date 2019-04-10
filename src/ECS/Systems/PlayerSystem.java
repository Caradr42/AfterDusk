package ECS.Systems;

import ECS.Components.Playable;
import ECS.Components.Player;
import ECS.Components.Transform;
import ECS.SystemJob;
import Scene.Scene;
import UI.UserInterface;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


/**
 *
 * @author carlo
 */
public class PlayerSystem extends SystemJob{
    
    Player player;
    Transform transform;  
    Playable playable;
    
    
    public PlayerSystem(Scene scene) {
        super(scene);
        entities = new ArrayList<>();
        transform = new Transform();
        player = new Player();
    }

    @Override
    public void update() {
        for(Integer e : entities){
            player = scene.entityManager.getEntityComponentInstance(e, player.getClass());
            transform = scene.entityManager.getEntityComponentInstance(e, transform.getClass());
                        
            if(scene.display.getKeyManager().right){
                transform.position.x = transform.position.x + 2;//+ 100 * MainThread.deltaTime);
            }
            
            if(scene.display.getKeyManager().left){
                transform.position.x = transform.position.x -2;//- 100 * MainThread.deltaTime);
            }
            
            if(scene.display.getKeyManager().up){
                transform.position.y = transform.position.y -2;//- 100 * MainThread.deltaTime);
            }
            
            if(scene.display.getKeyManager().down){
                transform.position.y = transform.position.y +2;//+ 100 * MainThread.deltaTime);
            }
            
            if(scene.display.getKeyManager().keys[KeyEvent.VK_P]) {

                
            }
        }
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(transform.getClass(), player.getClass());
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
