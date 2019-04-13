package ECS.Systems;

import ECS.Components.Playable;
import ECS.Components.Player;
import ECS.Components.Sprite;
import ECS.Components.Transform;
import ECS.SystemJob;
import Scene.Scene;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import proyecto_videojuegos.MainThread;


/**
 *
 * @author carlo
 */
public class PlayerSystem extends SystemJob{
    
    Player player;
    Transform transform;  
    Playable playable;
    Sprite sprite;
    
    public PlayerSystem(Scene scene) {
        super(scene);
        entities = new ArrayList<>();
        transform = new Transform();
        player = new Player();
        sprite = new Sprite();
    }

    @Override
    public void update() {
        for(Integer e : entities){
            player = scene.entityManager.getEntityComponentInstance(e, player.getClass());
            transform = scene.entityManager.getEntityComponentInstance(e, transform.getClass());
            sprite = scene.entityManager.getEntityComponentInstance(e, sprite.getClass());
            System.out.println(transform.position.x);
            
            if(scene.display.getKeyManager().right){
                transform.position.x = transform.position.x + 2;//+ 100 * MainThread.deltaTime);
                sprite.animation = sprite.animations.get(3).first;
                sprite.animationLenght = sprite.animations.get(3).second;
            }
            
            if(scene.display.getKeyManager().left){
                transform.position.x = transform.position.x -2;//- 100 * MainThread.deltaTime);
                sprite.animation = sprite.animations.get(2).first;
                sprite.animationLenght = sprite.animations.get(2).second;
            }
            
            if(scene.display.getKeyManager().up){
                transform.position.y = transform.position.y -2;//- 100 * MainThread.deltaTime);
                sprite.animation = sprite.animations.get(1).first;
                sprite.animationLenght = sprite.animations.get(1).second;
            }
            
            if(scene.display.getKeyManager().down){
                transform.position.y = transform.position.y +2;//+ 100 * MainThread.deltaTime);
                sprite.animation = sprite.animations.get(0).first;
                sprite.animationLenght = sprite.animations.get(0).second;
            }
            
            MainThread.c.ortogonalPosition.set(
                    transform.position.x * MainThread.c.scale + 32 - (scene.display.width / 2), 
                    transform.position.y * MainThread.c.scale + 32 - (scene.display.height / 2));
        }
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(transform.getClass(), player.getClass(), sprite.getClass());
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
