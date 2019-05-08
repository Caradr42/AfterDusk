package ECS;

import Scene.Scene;
import Signals.Listener;
import Signals.Signal;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Entity Component System class. The SystemJob class allows for the creation of
 * Systems. Systems provide the functionality to entities. Systems work with the
 * data of specific components and as such we have a different system for
 * different collections of components. It is usually recommended to create
 * systems and components that facilitate their reutilization.
 *
 * A system must use an EntityManager attached in order to have access to a
 * collection of Entities and their components.
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 * @date 09/02/2019
 * @version 1.0
 */
public abstract class SystemJob {

    protected ArrayList<Integer> entities; //A List of references to each Eantity that also has the necesarie components
    public static Scene scene; //Scene to which this System is attached and where it is executed
    public boolean active = true; //Used to activate or deactate the system. if so wanted.

    /**
     * Constructor of the SystemJob All Systems must be attached to an
     * EntityManager
     *
     * @param scene the scene to be attached to the system created.
     * @param active
     */
    public SystemJob(Scene scene, boolean active) {
        this.scene = scene;
        this.active = active;
    }

    /*
     * Code to be executed each update cycle (tick) of the mainThread
     */
    abstract public void update();

    /**
     * code to be executed just after the initialization of the scene. Executes
     * once.
     */
    abstract public void init();

    /**
     * Code to be executed each render frame at time of render.
     *
     * @param g
     */
    public void render(Graphics2D g) {
    }

    /**
     * code to be executed just after the initialization of the entity. Executes
     * once.
     */
    abstract public void onCreate();

    /**
     * Code to be executed before the Entity or Component(?) (idk ¯\_(ツ)_/¯) is
     * disposed by the garbage collector. Executes once.
     */
    abstract public void onDestroy();

    /**
     * isActive Shows if the System is active or not.
     *
     * @return active
     */
    public boolean isActive() {
        return active;
    }

}
