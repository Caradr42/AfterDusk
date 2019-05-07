package ECS.Systems;

import ECS.Components.MousePointer;
import ECS.Components.Sprite;
import ECS.SystemJob;
import Scene.Scene;
import java.awt.Graphics2D;

/**
 *
 * @author carlo
 */
public class MousePointerSystem extends  SystemJob{
    
    MousePointer mousePointer;

    
    public MousePointerSystem(Scene scene, boolean active) {
        super(scene, active);
        this.mousePointer = new MousePointer();
        
    }

    @Override
    public void update() {
        for(Integer e: entities){
                        

            mousePointer = scene.entityManager.getEntityComponentInstance(e, mousePointer.getClass());
            //System.out.println(mousePointer.mouseManager);
            //System.out.println(mousePointer.mouseManager.moving);

            //mousePointer.mouseManager = scene.display.mouseManager;
            mousePointer.position.x = scene.display.mouseManager.position.x / scene.c.scale;
            mousePointer.position.y = scene.display.mouseManager.position.y / scene.c.scale;
            //System.out.println(mousePointer.position.x + " " + mousePointer.position.y);
            //mousePointer.left = scene.display.getMouseManager().left;
            //mousePointer.right = scene.display.getMouseManager().right;
            
            //if(mousePointer.mouseManager.left) System.out.println("L");
            //if(mousePointer.mouseManager.right) System.out.println("R");
        }
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(mousePointer.getClass());
        
        for(Integer e: entities){
            mousePointer = scene.entityManager.getEntityComponentInstance(e, mousePointer.getClass());
            
            mousePointer.mouseManager = scene.display.getMouseManager();
            /*mousePointer.position.x = scene.display.mouseManager.position.x / scene.c.scale;
            mousePointer.position.y = scene.display.mouseManager.position.y / scene.c.scale;
            mousePointer.left = scene.display.mouseManager.left;
            mousePointer.right = scene.display.mouseManager.right;*/
        }
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
       
}
