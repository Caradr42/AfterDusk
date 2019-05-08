package Scene;

import DataBaseConnection.Insert;
import DataBaseConnection.Select;
import ECS.Components.Sprite;
import ECS.Entity;
import ECS.EntityManager;
import ECS.SystemJob;
import ECS.SystemJobManager;
import graphics.Camera;
import graphics.Display;
import java.util.HashMap;

/**
 * The scene is an abstract class used to create user defined scenes.
 * The scene contains an entityManager to contain the scene's Entities, and a 
 * systemJobManager to contain the scene's Systems.
 * The scene also necessitates a Display on construction, so entities can render
 * in this Display, and so input can be fetched from it.
 * You cannot assign the scene's Entity manager of SysteJobmManager, they 
 * are created on construction.
 * 
 * Entities are added on creation. 
 * Systems are added after entities are added.
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 * @date 12/02/2019
 * @version 1.0
 */

public abstract class Scene {
    
    private boolean active;
    
    public volatile EntityManager entityManager;
    public SystemJobManager systemJobManager;
    public Display display;
    public Camera c;
    public static Insert insert;
    public static Select select;

    /**
     * Constructor
     * @param display
     * @param c 
     */
    public Scene(Display display, Camera c) {
        entityManager = new EntityManager();
        systemJobManager = new SystemJobManager(this, active);
        this.display = display;
        this.c = c;
        active = true;



        addSystems();

        addEntities();

        systemJobManager.init();//initialize this systemJobManager. must be done after adding the systems

    }

    abstract protected void addEntities();
    
    abstract protected void addSystems();
}

