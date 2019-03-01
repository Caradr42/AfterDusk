package Scene;

import ECS.Components.Sprite;
import ECS.Entity;
import ECS.EntityManager;
import ECS.SystemJob;
import ECS.SystemJobManager;
import ECS.Systems.SpriteRender;
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
 * 
 * @author Carlos Adrián Guerra Vázquez
 * @date 12/02/2019
 * @version 1.0
 */

public abstract class Scene {
    
    private boolean active;
    
    public EntityManager entityManager;
    public SystemJobManager systemJobManager;
    public Display display;
    
    public Scene(Display display) {
        entityManager = new EntityManager();
        systemJobManager = new SystemJobManager(this);
        this.display = display;
        active = true;
        addSystems();
        addEntities();
        systemJobManager.init();//initialize this systemJobManager. must be done after adding the systems
    }
    
    abstract protected void addEntities();
    
    abstract protected void addSystems();
}

