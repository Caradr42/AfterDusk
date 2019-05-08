package ECS.Systems;

import ECS.Components.MousePointer;
import ECS.Components.Sprite;
import ECS.SystemJob;
import Scene.Scene;
import java.awt.Graphics2D;

/**
 * Manages the mpuse pointer System
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class MousePointerSystem extends SystemJob {

    MousePointer mousePointer;

    /**
     * Constructor
     *
     * @param scene
     * @param active
     */
    public MousePointerSystem(Scene scene, boolean active) {
        super(scene, active);
        this.mousePointer = new MousePointer();

    }

    @Override
    public void update() {
        for (Integer e : entities) {

            mousePointer = scene.entityManager.getEntityComponentInstance(e, mousePointer.getClass());

            mousePointer.position.x = scene.display.mouseManager.position.x / scene.c.scale;
            mousePointer.position.y = scene.display.mouseManager.position.y / scene.c.scale;

        }
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(mousePointer.getClass());

        for (Integer e : entities) {
            mousePointer = scene.entityManager.getEntityComponentInstance(e, mousePointer.getClass());

            mousePointer.mouseManager = scene.display.getMouseManager();

        }
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }

}
