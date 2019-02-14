package ECS;

import ECS.Systems.SpriteRender;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Singleton static Class
 * 
 * 
 * @author carlo
 */
public class SystemJobManager extends SystemJob{
    
    private ArrayList<SystemJob> systemsMap;

    public SystemJobManager(EntityManager entityManager) {
        super(entityManager);
        systemsMap = new ArrayList<>();
        systemsMap.add(new SpriteRender(entityManager));
    }

    @Override
    public void update() {    
        for(SystemJob sj : systemsMap){
            if(sj.isActive())
                sj.update();
        }
    }

    @Override
    public void fixedUpdate() {
        for(SystemJob sj : systemsMap){
            if(sj.isActive())
                sj.fixedUpdate();
        }
    }

    @Override
    public void init() {
        for(SystemJob sj : systemsMap){
            if(sj.isActive())
                sj.init();
        }
    }

    @Override
    public void render(Graphics g) {
        for(SystemJob sj : systemsMap){
            if(sj.isActive())
                sj.render(g);
        }
    }

    @Override
    public void dispose() {
        for(SystemJob sj : systemsMap){
            if(sj.isActive())
                sj.dispose();
        }
    }
}
