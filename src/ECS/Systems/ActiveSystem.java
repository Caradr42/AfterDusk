package ECS.Systems;

import ECS.Components.Active;
import ECS.Components.Tool;
import ECS.SystemJob;
import Scene.Scene;
import java.util.ArrayList;
import javax.swing.plaf.synth.Region;

/**
 * Manages the actives
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class ActiveSystem extends SystemJob {

    ArrayList<Integer> idTools;
    Tool tool;
    Active active;

    /**
     * Constructor
     *
     * @param scene
     * @param isActive
     */
    public ActiveSystem(Scene scene, boolean isActive) {
        super(scene, isActive);
    }

    @Override
    public void update() {

    }

    @Override
    public void init() {
        tool = new Tool();

        idTools = scene.entityManager.getEntitiesWithComponents(Tool.class);

        for (Integer t : idTools) {
            for (Class c : Active.activesSet) {
                tool = scene.entityManager.getEntityComponentInstance(t, Tool.class);
                if (scene.entityManager.hasComponent(t, c)) {
                    active = (Active) scene.entityManager.getEntityComponentInstance(t, c);

                    tool.arrActives.add(active); //add the active to the tool
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
