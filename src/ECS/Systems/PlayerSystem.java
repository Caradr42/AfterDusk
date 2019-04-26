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
 * System that executes player behabiour
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class PlayerSystem extends SystemJob{
    
    Player player;
    Transform transform;  
    Playable playable;
    Sprite sprite;
    
    /**
     * Constructor
     * @param scene 
     */
    public PlayerSystem(Scene scene) {
        super(scene);
        entities = new ArrayList<>();
        transform = new Transform();
        player = new Player();
        sprite = new Sprite();
    }

    /**
     * Updates the position and animation of the player
     */
    @Override
    public void update() {
        for(Integer e : entities){
            player = scene.entityManager.getEntityComponentInstance(e, player.getClass());
            transform = scene.entityManager.getEntityComponentInstance(e, transform.getClass());
            sprite = scene.entityManager.getEntityComponentInstance(e, sprite.getClass());
            
            
            //if the player goes to the right change the position and the animation to the right
            if(scene.display.getKeyManager().right){
                //check loop and play the grass walking sound.
                if(!Assets.Assets.grassWalk.getLooping())
                {
                    Assets.Assets.grassWalk.setLooping(active);
                    Assets.Assets.grassWalk.play();
                }
                transform.position.x = transform.position.x + 2;//+ 100 * MainThread.deltaTime);
                sprite.animation = sprite.animations.get(3).first;
                sprite.animationLenght = sprite.animations.get(3).second;
            }
            
            //if the player goes to the left change the position and the animation to the left
            if(scene.display.getKeyManager().left){
                //check loop and play the grass walking sound.
                if(!Assets.Assets.grassWalk.getLooping())
                {
                    Assets.Assets.grassWalk.setLooping(active);
                    Assets.Assets.grassWalk.play();
                }
                transform.position.x = transform.position.x -2;//- 100 * MainThread.deltaTime);
                sprite.animation = sprite.animations.get(2).first;
                sprite.animationLenght = sprite.animations.get(2).second;
            }
           
            //if the player goes to up change the position and the animation to up
            if(scene.display.getKeyManager().up){
               //check loop and play the grass walking sound.
                if(!Assets.Assets.grassWalk.getLooping())
                {
                    Assets.Assets.grassWalk.setLooping(active);
                    Assets.Assets.grassWalk.play();
                }
                transform.position.y = transform.position.y -2;//- 100 * MainThread.deltaTime);
                sprite.animation = sprite.animations.get(1).first;
                sprite.animationLenght = sprite.animations.get(1).second;
            }
 
            //if the player goes down change the position and the animation to down
            if(scene.display.getKeyManager().down){
                //check loop and play the grass walking sound.
                if(!Assets.Assets.grassWalk.getLooping())
                {
                    Assets.Assets.grassWalk.setLooping(active);
                    Assets.Assets.grassWalk.play();
                }
                transform.position.y = transform.position.y +2;//+ 100 * MainThread.deltaTime);
                sprite.animation = sprite.animations.get(0).first;
                sprite.animationLenght = sprite.animations.get(0).second;
            }
            
            //if its not moving, stop sound
            if(!(scene.display.getKeyManager().down||scene.display.getKeyManager().up||scene.display.getKeyManager().left||scene.display.getKeyManager().right)){
                Assets.Assets.grassWalk.setLooping(false);
                Assets.Assets.grassWalk.stop();
            }
            
            //if its not moving, stop the animation
            if(!(scene.display.getKeyManager().right || scene.display.getKeyManager().left || scene.display.getKeyManager().up || scene.display.getKeyManager().down)){
                sprite.frameCounter = 0;
            }
            
            MainThread.c.ortogonalPosition.set(
                    transform.position.x * MainThread.c.scale + 32 - (scene.display.width / 2), 
                    transform.position.y * MainThread.c.scale + 32 - (scene.display.height / 2));
        }
    }

    /**
     * initializes the entities
     */
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

