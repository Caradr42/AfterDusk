package ECS.Systems;


import DataBaseConnection.Insert;
import DataBaseConnection.Select;
import ECS.Components.Collidable;
import ECS.Components.Inventory;
import ECS.Components.Item;
import ECS.Components.Movement;
import ECS.Components.Playable;
import ECS.Components.Player;
import ECS.Components.Sprite;
import ECS.Components.Tool;
import ECS.Components.Transform;
import ECS.Components.UIText;
import ECS.SystemJob;
import Maths.Vector2;
import Maths.Vector3;
import Scene.Scene;
import Utility.Pair;
import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import com.sun.xml.internal.bind.v2.util.CollisionCheckStack;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import proyecto_videojuegos.MainThread;


/**
 * System that executes player behaviour
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


    Tool tool;


    Movement movement;


    boolean firstTime;
    
    int leftBorder = 80 - 32;
    int rightBorder = 80;
    int upperBorder = 50;
    int downBorder = 50 - 32;
    Vector3 v3V;
    Vector3 v3R;
    Collidable collision;
    /**
     * Constructor
     * @param scene 
     */
    public PlayerSystem(Scene scene, boolean active) {
        super(scene, active);
        entities = new ArrayList<>();
        transform = new Transform();
        player = new Player();
        sprite = new Sprite();
        movement = new Movement();
        //rightHand = new Tool();
        playable = new Playable();
        collision=new Collidable();
    }

    /**
     * Updates the position and animation of the player
     */
    @Override
    public void update() {
        
        for (Integer e : entities) {
            player = scene.entityManager.getEntityComponentInstance(e, player.getClass());
            transform = scene.entityManager.getEntityComponentInstance(e, transform.getClass());
            sprite = scene.entityManager.getEntityComponentInstance(e, sprite.getClass());
            playable = scene.entityManager.getEntityComponentInstance(e, playable.getClass());
            movement = scene.entityManager.getEntityComponentInstance(e, Movement.class);
            
            
            collision= scene.entityManager.getEntityComponentInstance(e, Collidable.class);
            boolean CollisionCheck=false;
            //Check if is empty the HashSet with the ids of collision bw entity-tiles
            
            if(!collision.setCollidable.isEmpty()){
                //System.out.println(collision.setCollidable);
                CollisionCheck=true;
            }
            
            //
            
            /*//Save the position of the initial position of the player
            if(firstTime){
                MainThread.c.position1.set(-scene.display.width / (2 * MainThread.c.scale) + leftBorder, -scene.display.height / (2 * MainThread.c.scale) +  upperBorder);
                MainThread.c.position2.set(scene.display.width / (2 * MainThread.c.scale) - rightBorder, scene.display.height / (2 * MainThread.c.scale) - downBorder);
                        firstTime = false;
            }*/
            
            //

            //if the player goes to the right change the position and the animation to the right
            if(scene.display.getKeyManager().right){
                //check loop and play the grass walking sound.
                if(!Assets.Assets.grassWalk.getLooping())
                {
                    Assets.Assets.grassWalk.setLooping(active);
                    Assets.Assets.grassWalk.play();
                }
                
                //Movement with ressistance
                v3V=new Vector3(0.2, 0, 0);
                v3R=new Vector3(-0.1,0,0);
                
                
                if(Math.abs(movement.velocity.x)<=2&&!CollisionCheck){
                    movement.velocity.set(movement.velocity.add(v3V));
                    movement.velocity.set(movement.velocity.add(v3R));
                }
               
                
                if(scene.display.keyManager.wasPressed[KeyEvent.VK_D] || scene.display.keyManager.wasPressed[KeyEvent.VK_RIGHT]){
                    sprite.frameCounter = 1;
                }
                
                sprite.animation = sprite.animations.get(3).first;
                sprite.animationLenght = sprite.animations.get(3).second;
                
                playable.right = true;
                playable.left = false;
                playable.down = false;
                playable.up = false;
                

            }
            
            //if the player goes to the left change the position and the animation to the left

            if(scene.display.getKeyManager().left){
                //check loop and play the grass walking sound.
                if(!Assets.Assets.grassWalk.getLooping())
                {
                    Assets.Assets.grassWalk.setLooping(active);
                    Assets.Assets.grassWalk.play();
                }
                
                //Movement with ressistance
                v3V=new Vector3(-0.2, 0, 0);
                v3R=new Vector3(0.1,0,0);
                
                if(Math.abs(movement.velocity.x)<=2){
                    movement.velocity.set(movement.velocity.add(v3V));
                    movement.velocity.set(movement.velocity.add(v3R));
                }
                
               
                if(scene.display.keyManager.wasPressed[KeyEvent.VK_A] || scene.display.keyManager.wasPressed[KeyEvent.VK_LEFT]){
                    sprite.frameCounter = 1;
                }
                
                sprite.animation = sprite.animations.get(2).first;
                sprite.animationLenght = sprite.animations.get(2).second;

                playable.right = false;
                playable.left = true;
                playable.down = false;
                playable.up = false;
                
            }

           
            //if the player goes to up change the position and the animation to up
            if(scene.display.getKeyManager().up){
               //check loop and play the grass walking sound.
                if(!Assets.Assets.grassWalk.getLooping())
                {
                    Assets.Assets.grassWalk.setLooping(active);
                    Assets.Assets.grassWalk.play();
                }
                
                
                //Movement with ressistance
                v3V=new Vector3(0, -0.2, 0);
                v3R=new Vector3(0,0.1,0);
                
                
                if(Math.abs(movement.velocity.y)<=2&&!CollisionCheck){
                    movement.velocity.set(movement.velocity.add(v3V));
                    movement.velocity.set(movement.velocity.add(v3R));
                    }

                    if(scene.display.keyManager.wasPressed[KeyEvent.VK_W] || scene.display.keyManager.wasPressed[KeyEvent.VK_UP]){
                        sprite.frameCounter = 1;
                    }
                
                
                sprite.animation = sprite.animations.get(1).first;
                sprite.animationLenght = sprite.animations.get(1).second;

                playable.right = false;
                playable.left = false;
                playable.down = false;
                playable.up = true;
                
            }

 
            //if the player goes down change the position and the animation to down
            if(scene.display.getKeyManager().down){
                //check loop and play the grass walking sound.
                if(!Assets.Assets.grassWalk.getLooping())
                {
                    Assets.Assets.grassWalk.setLooping(active);
                    Assets.Assets.grassWalk.play();
                }
                
                //Movement with ressistance
                v3V=new Vector3(0, 0.2, 0);
                v3R=new Vector3(0,-0.1,0);
                
                if(Math.abs(movement.velocity.y)<=2&&!CollisionCheck){
                    movement.velocity.set(movement.velocity.add(v3V));
                    movement.velocity.set(movement.velocity.add(v3R));
                }
                
               
                
                if(scene.display.keyManager.wasPressed[KeyEvent.VK_S] || scene.display.keyManager.wasPressed[KeyEvent.VK_DOWN]){
                    sprite.frameCounter = 1;
                }
                
                sprite.animation = sprite.animations.get(0).first;
                sprite.animationLenght = sprite.animations.get(0).second;

                playable.right = false;
                playable.left = false;
                playable.down = true;
                playable.up = false;
                
            }
            

            //if the player presses the space key, an attack is done with the right hand
            if(scene.display.getKeyManager().wasPressed[KeyEvent.VK_SPACE]) {
         
                Inventory LRinv = scene.entityManager.getEntityComponentInstance(player.LRInventory, Inventory.class);
                
                Item RItem = scene.entityManager.getEntityComponentInstance(LRinv.slots.get(1), Item.class);
                //rightHand = scene.entityManager.getEntityComponentInstance(LRinv.slots.get(1), rightHand.getClass());
                //System.out.println(RItem.name);
                //0 for the base attack
                //rightHand.currentActive = 0;
               if(playable.hasWeapon){
                   int idTool;
                   if(player.rightOrLeft){ //si es verdadero es que tiene arma en la derecha
                       idTool = scene.entityManager.getEntityComponentInstance(player.LRInventory, Inventory.class).slots.get(1);
                       
                   }else {
                       idTool = scene.entityManager.getEntityComponentInstance(player.LRInventory, Inventory.class).slots.get(0);   
                   }
                   tool = scene.entityManager.getEntityComponentInstance(idTool, Tool.class);
                   //System.out.println("currentactv antes" + tool.currentActive);
                   tool.currentActive = 0;
                   
                   Assets.Assets.swordClip.play();
                   //System.out.println("currentactv platersis" + tool.currentActive);
                   System.out.println("Space: " + scene.entityManager.getEntityByID(idTool).getName() + " " + tool.currentActive);
               }
                
                //System.out.println("Space pressed");
            }
            
            if(scene.display.keyManager.wasPressed[KeyEvent.VK_L]){
                //active = new Insert("insertEntity", 101, "Hello");
                scene.insert.makeInsert("insertEntity", 50288, "ffe");
                
            }
            
            if (scene.display.keyManager.wasPressed[KeyEvent.VK_B]) {
               
                
                scene.select.makeSelect("selectEntities", "SELECT * FROM Entity");
                
               /* ArrayList<Pair> ents = Select.selectEntities("SELECT * FROM Entity");
                
                for(Pair p : ents) {
                    System.out.println("Id: " + p.first);
                    System.out.println("name: " + p.second);
                }*/
                try {
                    while (scene.select.resSet.next()) {
                        int id = scene.select.resSet.getInt("Entity_ID");
                        String entityName = scene.select.resSet.getString("Entity_Name");
                        
                        System.out.println("Id: " + id);
                        System.out.println("name: " + entityName);
                        
                        // print the results
                        //System.out.format("ID: " + id);
                        //System.out.format("EntityName: " + entityName);
                        //entities.add(new Pair(id, entityName));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(PlayerSystem.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            if(scene.display.keyManager.wasPressed[KeyEvent.VK_SHIFT]){
                Assets.Assets.menu.play();
                if(player.rightOrLeft) {
                    player.rightOrLeft = false;
                    
                    //If there is no weapon in the left part
                    if(scene.entityManager.getEntityComponentInstance(player.LRInventory, Inventory.class).slots.get(0) == 0) {
                        playable.hasWeapon = false;
                        System.out.println("has no weapon");
                    }
                    else {
                        playable.hasWeapon = true;
                        System.out.println("has weapon");
                    }   
                } 
                else {
                    player.rightOrLeft = true;

                    //If there is no weapon in the left part
                    if (scene.entityManager.getEntityComponentInstance(player.LRInventory, Inventory.class).slots.get(1) == 0) {
                        playable.hasWeapon = false;
                        System.out.println("has no weapon");
                    } 
                    
                    else {
                        playable.hasWeapon = true;
                        System.out.println("has weapon");
                    }
                }
            }

            //if its not moving, stop sound
            if(!(scene.display.getKeyManager().down||scene.display.getKeyManager().up||scene.display.getKeyManager().left||scene.display.getKeyManager().right)){
                Assets.Assets.grassWalk.setLooping(false);
                Assets.Assets.grassWalk.stop();
            }

            //if its not moving, stop the animation
            if(!(scene.display.getKeyManager().right || scene.display.getKeyManager().left || scene.display.getKeyManager().up || scene.display.getKeyManager().down)){
                sprite.frameCounter = 0;
            }
            
            
            
            //if colides with the left border, move the camera to the left
            if(scene.c.WorldToUICoodinates(transform.position.toVector2()).x < leftBorder){
               scene.c.ortogonalPosition.x = (transform.position.x - leftBorder) * scene.c.scale;
            }
            //if collides with the rigth border, move the camera to the right
            if(scene.c.WorldToUICoodinates(transform.position.toVector2()).x > + scene.display.width / scene.c.scale - rightBorder){
               scene.c.ortogonalPosition.x = (transform.position.x - (scene.display.width / scene.c.scale - rightBorder)) * scene.c.scale;
            }
            //if colides with the upper border lift camera
            if(scene.c.WorldToUICoodinates(transform.position.toVector2()).y < upperBorder){
               scene.c.ortogonalPosition.y = (transform.position.y - upperBorder) * scene.c.scale;
            }
            //if colides with the down border move camera down

            if(scene.c.WorldToUICoodinates(transform.position.toVector2()).y > + scene.display.height / scene.c.scale - downBorder){
               scene.c.ortogonalPosition.y = (transform.position.y - (scene.display.height / scene.c.scale - downBorder)) * scene.c.scale;
            }
            
            //No key activity, no movement.
            if(!(scene.display.getKeyManager().up||scene.display.getKeyManager().down)){
                movement.velocity.set(new Vector3(movement.velocity.x,0,0));
            }
            if(!(scene.display.getKeyManager().left||scene.display.getKeyManager().right)){
                movement.velocity.set(new Vector3(0,movement.velocity.y,0));
            }
            if(scene.display.getKeyManager().left&&scene.display.getKeyManager().right){
                movement.velocity.set(new Vector3(0,movement.velocity.y,0));
            }
            if(scene.display.getKeyManager().up&&scene.display.getKeyManager().down){
                movement.velocity.set(new Vector3(movement.velocity.x,0,0));
            }
            
        }
    }

    /**
     * initializes the entities
     */
    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(transform.getClass(), player.getClass(), sprite.getClass(), Movement.class,Collidable.class);
        firstTime = true;
        
        for(Integer e : entities){
            player = scene.entityManager.getEntityComponentInstance(e, player.getClass());
            player._UIText = scene.entityManager.getEntityComponentInstance(player.uiText, UIText.class);
        }

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

