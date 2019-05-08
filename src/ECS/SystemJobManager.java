package ECS;

import Scene.Scene;
import Signals.Listener;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Singleton static Class
 * 
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 * @date 17/02/2019
 * @version 1.2
 */
public class SystemJobManager extends SystemJob{
    
    public class SystemJobWraper implements Callable{
        
        ArrayList<SystemJob> systems;

        /**
         * Constructor
         * @param systems 
         */
        public SystemJobWraper(ArrayList<SystemJob> systems) {
            this.systems = systems;
        }
        
        /**
         * Constructor
         * @param systems 
         */
        public SystemJobWraper(SystemJob ... systems) {
            this.systems = new ArrayList<>();
            for(SystemJob sj : systems){
                this.systems.add(sj);
            }
        }
        /**
         * Constructor
         * @param system 
         */
        public SystemJobWraper(SystemJob system) {
            this.systems = new ArrayList<>();
            this.systems.add(system);
        }

        @Override
        public Object call() throws Exception {
            for(SystemJob sj : systems){
                if(sj.active)
                    sj.update();
            }
            return null;
        }
    }
    
    public ArrayList<SystemJob> systemsList;
    public ArrayList<SystemJobWraper> wrapersList;
    public int systemsListSize;
    public  ExecutorService executorService;
    public ExecutorCompletionService completionService;
/**
 * Constructor
 * @param scene
 * @param active 
 */
    public SystemJobManager(Scene scene, boolean active) {
        super(scene, active);
        executorService = Executors.newFixedThreadPool(4);
        completionService = new ExecutorCompletionService(executorService);
        
        systemsList = new ArrayList<>();
        wrapersList = new ArrayList<>();
        
        systemsListSize = 0;
    }
    /**
     * addSystem
     * Allows to add a new system
     * @param sj 
     */
    public void addSystem(SystemJob sj){
        systemsList.add(sj);
        wrapersList.add(new SystemJobWraper(sj));
        systemsListSize++;
    }
    /**
     * addSystems
     * Allows to add new systems
     * @param systemsToAdd 
     */
    public void addSystems(SystemJob ... systemsToAdd){
        for(SystemJob sj : systemsToAdd){
            systemsList.add(sj);
            wrapersList.add(new SystemJobWraper(sj));
            systemsListSize++;
        }
    }

    @Override
    public void update() {    
        for(SystemJobWraper sjw : wrapersList){
            completionService.submit(sjw);
        }   
    }
    
    /**
     * initialize each system in the systemsList at the start of the game. 
     * Also adds all systems onRemoveEntitiesListener and onAddEntitesListener 
     * to the entity manager's removeEntitiesSignal and addEntitiesSignal on 
     * this scene.
     */
    @Override
    public void init() {   
        for(SystemJob sj : systemsList){
            sj.init();
            //scene.entityManager.removeEntitiesSignal.add(sj.onRemoveEntitiesListener);
           // scene.entityManager.addEntitiesSignal.add(sj.onAddEntitesListener);
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
