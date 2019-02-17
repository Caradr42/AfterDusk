package ECS;

import ECS.Systems.SpriteRender;
import Scene.Scene;
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
    
    private  ArrayList<SystemJob> systemsMap;

    public SystemJobManager(Scene scene) {
        super(scene);
        systemsMap = new ArrayList<>();
    }
    
    public void addSystem(SystemJob sj){
        systemsMap.add(sj);
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
    public void onCreate() {
        for(SystemJob sj : systemsMap){
            if(sj.isActive())
                sj.onCreate();
        }
    }
    
    @Override
    public void onDestroy() {
        for(SystemJob sj : systemsMap){
            if(sj.isActive())
                sj.onDestroy();
        }
    }
    
    
}
