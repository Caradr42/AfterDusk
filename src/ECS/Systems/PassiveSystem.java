package ECS.Systems;

import ECS.Components.Pasive;
import ECS.Components.Tool;
import ECS.SystemJob;
import Scene.Scene;
import java.util.ArrayList;

/**
 * Manages the passives System
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class PassiveSystem extends SystemJob {

    Tool tool;
    ArrayList<Integer> idTools;
    Pasive pasive;

    /**
     * Constructor
     *
     * @param scene
     * @param active
     */
    public PassiveSystem(Scene scene, boolean active) {
        super(scene, active);
    }

    @Override
    public void update() {

    }

    @Override
    public void init() {
        tool = new Tool();

        idTools = scene.entityManager.getEntitiesWithComponents(Tool.class, Pasive.class);

        for (Integer t : idTools) {
            for (Class c : Pasive.pasivesSet) {
                tool = scene.entityManager.getEntityComponentInstance(t, Tool.class);
                if (scene.entityManager.hasComponent(t, c)) {
                    pasive = (Pasive) scene.entityManager.getEntityComponentInstance(t, c);
                    tool.toolPasive = pasive; //adds a passive to the tool

                }
            }
        }

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

}
