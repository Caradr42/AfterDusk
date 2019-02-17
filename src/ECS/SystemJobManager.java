package ECS;

import Scene.Scene;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Singleton static Class
 * 
 * 
 * @author Carlos Adrián Guerra Vázquez
 * @date 17/02/2019
 * @version 1.2
 */
public class SystemJobManager extends SystemJob{
    
    public class Thread1 implements Runnable{
        private Thread thread;
        
        public Thread1() {
        }
                
        @Override
        public void run() {
            for(int i = 0; i <= systemsMapSize / 2; ++i){
                if(systemsList.get(i).isActive())
                    systemsList.get(i).update();
            }
            //System.out.println( systemsList.get(0).toString());
        }    
        
        public void start(){
            thread = new Thread(this);
            thread.start();
        }
    }
    
    public class Thread2 implements Runnable{
        private Thread thread;
        
        public Thread2() {
        }
                
        @Override
        public void run() {  
            for(int i = systemsMapSize / 2; i < systemsMapSize; ++i){
                if(systemsList.get(i).isActive())
                    systemsList.get(i).update();
            }          
        }
        
        public void start(){
            thread = new Thread(this);
            thread.start();
        }
    }
    
    private Thread1 t1;
    private Thread2 t2;
    
    private  ArrayList<SystemJob> systemsList;
    private int systemsMapSize;

    public SystemJobManager(Scene scene) {
        super(scene);
        systemsList = new ArrayList<>();
        systemsMapSize = 0;
        t1  = new Thread1();
        t2  = new Thread2();
    }
    
    public void addSystem(SystemJob sj){
        systemsList.add(sj);
        systemsMapSize++;
    }

    @Override
    public void update() {    
        /*for(SystemJob sj : systemsList){
            if(sj.isActive())
                sj.update();
        }*/
        t1.start();
        t2.start();
    }

    @Override
    public void fixedUpdate() {
        for(SystemJob sj : systemsList){
            if(sj.isActive())
                sj.fixedUpdate();
        }
    }

    @Override
    public void init() {
        
        for(SystemJob sj : systemsList){
            if(sj.isActive())
                sj.init();
        }
    }

    @Override
    public void render(Graphics2D g) {
        for(SystemJob sj : systemsList){
            if(sj.isActive())
                sj.render(g);
        }
    }

    @Override
    public void onCreate() {
        for(SystemJob sj : systemsList){
            if(sj.isActive())
                sj.onCreate();
        }
    }
    
    @Override
    public void onDestroy() {
        for(SystemJob sj : systemsList){
            if(sj.isActive())
                sj.onDestroy();
        }
    }
    
    
}
