package Scene;

import ECS.Components.Sprite;
import ECS.Entity;
import ECS.EntityManager;
import ECS.SystemJob;
import ECS.SystemJobManager;
import ECS.Systems.SpriteRender;
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
    
    protected EntityManager entityManager;
    public SystemJobManager systemJobManager;
    
    public Scene() {
        entityManager = new EntityManager();
        systemJobManager = new SystemJobManager(entityManager);
        active = true;
        addEntities();
    }
    
    abstract protected void addEntities();
}
