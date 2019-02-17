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
 * This is what we could call the manager
 * it should allow us to create new EntityManager 
 * 
 * @author Carlos Adrián Guerra Vázquez
 * @date 09/02/2019
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
    }
    
    abstract protected void addEntities();
    
    abstract protected void addSystems();
}

