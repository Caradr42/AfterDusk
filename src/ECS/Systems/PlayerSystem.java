package ECS.Systems;

import ECS.Components.Playable;
import ECS.Components.Player;
import ECS.Components.Sprite;
import ECS.Components.Transform;
import ECS.SystemJob;
import Maths.Vector2;
import Scene.Scene;
import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import proyecto_videojuegos.MainThread;


/**
 * System that executes player behabiour
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class PlayerSystem extends SystemJob{
    
    Player player;
    Transform transform;  
    Playable playable;
    Sprite sprite;
    boolean firstTime;
    
    /**
     * Constructor
     * @param scene 
     */
    public PlayerSystem(Scene scene) {
        super(scene);
        entities = new ArrayList<>();
        transform = new Transform();
        player = new Player();
        sprite = new Sprite();
        //MainThread.c.position2.set(transform.position.x + 20, 0);
    }

    /**
     * Updates the position and animation of the player
     */
    @Override
    public void update() {
        for(Integer e : entities){
            player = scene.entityManager.getEntityComponentInstance(e, player.getClass());
            transform = scene.entityManager.getEntityComponentInstance(e, transform.getClass());
            sprite = scene.entityManager.getEntityComponentInstance(e, sprite.getClass());
            
            if(firstTime){
                MainThread.c.position1.set(-scene.display.width / (2 * MainThread.c.scale) + 80, -scene.display.height / (2 * MainThread.c.scale) + 60);
                MainThread.c.position2.set((scene.display.width / (2 * MainThread.c.scale) - 80), scene.display.height / (2 * MainThread.c.scale) - 50);
                        System.out.println("oli");
                        firstTime = false;
            }
            
            System.out.println("position 1 " + MainThread.c.position1.x + ","+ MainThread.c.position1.y);
            //System.out.println("ortogonal  " + MainThread.c.ortogonalPosition.x + ","+ MainThread.c.ortogonalPosition.y);
            System.out.println("position 2 " + MainThread.c.position2.x + ","+ MainThread.c.position2.y);
            System.out.println("transform " + transform.position.x + ","+ transform.position.y);
            
            //if the player goes to the right change the position and the animation to the right
            if(scene.display.getKeyManager().right){
                transform.position.x = transform.position.x + 2;//+ 100 * MainThread.deltaTime);
                sprite.animation = sprite.animations.get(3).first;
                sprite.animationLenght = sprite.animations.get(3).second;
            }
            //if the player goes to the left change the position and the animation to the left
            if(scene.display.getKeyManager().left){
                transform.position.x = transform.position.x -2;//- 100 * MainThread.deltaTime);
                sprite.animation = sprite.animations.get(2).first;
                sprite.animationLenght = sprite.animations.get(2).second;
            }
            //if the player goes to up change the position and the animation to up
            if(scene.display.getKeyManager().up){
                transform.position.y = transform.position.y -2;//- 100 * MainThread.deltaTime);
                sprite.animation = sprite.animations.get(1).first;
                sprite.animationLenght = sprite.animations.get(1).second;
            }
            //if the player goes down change the position and the animation to down
            if(scene.display.getKeyManager().down){
                transform.position.y = transform.position.y +2;//+ 100 * MainThread.deltaTime);
                sprite.animation = sprite.animations.get(0).first;
                sprite.animationLenght = sprite.animations.get(0).second;
            }
            
            //if its not moving, stop the animation
            if(!(scene.display.getKeyManager().right || scene.display.getKeyManager().left || scene.display.getKeyManager().up || scene.display.getKeyManager().down)){
                sprite.frameCounter = 0;
            }
            
            //if colides with the left border
            if (transform.position.x == MainThread.c.position1.x){
                MainThread.c.ortogonalPosition.set(
                        transform.position.x * MainThread.c.scale - 80 * MainThread.c.scale /*transform.position.x*/ ,
                        MainThread.c.ortogonalPosition.y);
                 MainThread.c.position1.x = MainThread.c.position1.x - 2;
                 MainThread.c.position2.x = MainThread.c.position2.x -2;
            }
            
            //if collides with the rigth border
            if (transform.position.x == MainThread.c.position2.x/*scene.display.width/*MainThread.c.position2.x*/){
                MainThread.c.ortogonalPosition.set(
                       transform.position.x * MainThread.c.scale - scene.display.width + 80 * MainThread.c.scale /*MainThread.c.ortogonalPosition.x + transform.position.x*/ ,
                        MainThread.c.ortogonalPosition.y);
                MainThread.c.position2.x = MainThread.c.position2.x + 2;
                MainThread.c.position1.x = MainThread.c.position1.x + 2;
            }
            
            //if colides with the upper border
            if (transform.position.y == MainThread.c.position1.y){
                MainThread.c.ortogonalPosition.set(
                         MainThread.c.ortogonalPosition.x/*transform.position.x*/ ,
                        transform.position.y * MainThread.c.scale - 60 * MainThread.c.scale);
                MainThread.c.position1.y = MainThread.c.position1.y - 2;
                 MainThread.c.position2.y = MainThread.c.position2.y - 2;
            }
            
            //System.out.println("width " + scene.display.width);
            //if colides with the down border
            if (transform.position.y == MainThread.c.position2.y){
                MainThread.c.ortogonalPosition.set(
                         MainThread.c.ortogonalPosition.x/*transform.position.x*/ ,
                        transform.position.y * MainThread.c.scale - scene.display.height + 50 * MainThread.c.scale );
                 MainThread.c.position1.y = MainThread.c.position1.y + 2;
                 MainThread.c.position2.y = MainThread.c.position2.y + 2;
            }
            
            //System.out.println( "ortogonal" + MainThread.c.ortogonalPosition.x + "," + MainThread.c.ortogonalPosition.y );
            
            /* MainThread.c.ortogonalPosition.set(
                    transform.position.x * MainThread.c.scale + 32 - (scene.display.width / 2), 
                    transform.position.y * MainThread.c.scale + 32 - (scene.display.height / 2));*/
           
            //System.out.println(MainThread.c.position1.y);
            
            //System.out.println(MainThread.c.position2.x);
            //System.out.println(MainThread.c.position2.y);
            //System.out.println(transform.position.y);
            /*if(MainThread.c.position1.y > transform.position.y){
               
                MainThread.c.ortogonalPosition.set(
                    transform.position.x  /** MainThread.c.scale - scene.display.width / 2*///, 
                   // transform.position.y - 20 );
                //MainThread.c.position1.x = MainThread.c.position1.x - 120;
               
             // MainThread.c.setRectC((int)(transform.position.x + scene.display.width) / 2, (int)(transform.position.y + scene.display.height) / 2);
            //}
            
           // System.out.println("width " + scene.display.width);
            //System.out.println("height " + scene.display.height);
            //System.out.println("screen " + MainThread.c.screenSize.height);
            
            //if(MainThread.c.position1.y > transform.position.y){
            /*  // System.out.println("estoy afuera del rec");
                MainThread.c.ortogonalPosition.set(
                    transform.position.x + 32 * MainThread.c.scale, 
                    transform.position.y  + 32 * MainThread.c.scale/** MainThread.c.scale + 32*///);
               /* MainThread.c.position1.set(transform.position.x - 180 *MainThread.c.scale , transform.position.y - 70* MainThread.c.scale);
                MainThread.c.position2.set(transform.position.x + 100*MainThread.c.scale , transform.position.y + 70*MainThread.c.scale);
           */
               
           /* }
            
            if(MainThread.c.position2.y == transform.position.y){
               //System.out.println("jajks");
                MainThread.c.ortogonalPosition.set(
                    transform.position.x /** MainThread.c.scale - 50 - scene.display.width / 2*///, 
                   // transform.position.y /** MainThread.c.scale + 50 - scene.display.height / 2*/);
               /* MainThread.c.position1.set(transform.position.x - 180 , transform.position.y - 70);
                MainThread.c.position2.set(transform.position.x + 100 , transform.position.y + 70);
            }
            
            if(MainThread.c.position1.x == transform.position.x){
              // System.out.println("o sea equis");
                MainThread.c.ortogonalPosition.set(
                    transform.position.x /** MainThread.c.scale - 100 - scene.display.width / 2*///, 
                    //transform.position.y /** MainThread.c.scale + 50 - scene.display.height / 2*/);
                /*MainThread.c.position1.set(transform.position.x - 180 , transform.position.y - 70);
                MainThread.c.position2.set(transform.position.x + 100 , transform.position.y + 70);
            }
            if(MainThread.c.position2.x <= transform.position.x){
               //System.out.println("o sea la otra equis");
                MainThread.c.ortogonalPosition.set(
                    transform.position.x /** MainThread.c.scale - 100 - scene.display.width / 2*///, 
                   // transform.position.y /** MainThread.c.scale + 50 - scene.display.height / 2*/);
               /* MainThread.c.position1.set(transform.position.x - 180 , transform.position.y - 70);
                MainThread.c.position2.set(transform.position.x + 100 , transform.position.y + 70);
            }
        */}
    }

    public void initialPosition(double x, double y){
        MainThread.c.position1.set(x , y + 50);
    }
    
    /**
     * initializes the entities
     */
    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(transform.getClass(), player.getClass(), sprite.getClass());
        firstTime = true;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
    
    @Override
    public void render(Graphics2D g) {
     
    }
    
}

