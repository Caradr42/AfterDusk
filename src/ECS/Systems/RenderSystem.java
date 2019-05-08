package ECS.Systems;


import ECS.Components.AttackCollider;
import ECS.Components.AttackComponent;

import ECS.Components.Collidable;

import ECS.Components.MousePointer;
import ECS.Components.Sprite;
import ECS.Components.Transform;
import ECS.Components.UIEntity;
import ECS.Components.WorldEntity;
import ECS.SystemJob;
import Scene.Scene;
import java.awt.Graphics2D;
import java.util.Comparator;
import Utility.Pair;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;


/**
 * System that renders all entities with Sprite and Transform Components
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class RenderSystem extends SystemJob{
    
    private ArrayList<Integer> UIentities; //this are entities that are UI elements
    private ArrayList<Integer> mousePointers;

    private ArrayList<Integer> attackComponents; // attackComponents of weapons

    private ArrayList<Integer> collidables;
    

    private MousePointer mousePointer;
    
    public static boolean debugRender = false;
    
    //archetype of the enitites lits
    private Transform transform;
    private Sprite sprite;

    private AttackComponent attackComponent;

    
    //Debug Componetns
    Collidable collidable;

    
    //Priority Queue of entities to render
    private List <Pair<Transform, Sprite>> array;
    
    AffineTransform originalAT;

    private WorldEntity worldEntity;
    
    //archetype if the UI Entities list
    private UIEntity uiEntity;
    
    /**
     * Constructor
     * @param scene
     * @param active 
     */
    public RenderSystem(Scene scene, boolean active) {
        super(scene, active);
        transform = new Transform();
        sprite = new Sprite();
        worldEntity = new WorldEntity();
        
        uiEntity = new UIEntity();
        mousePointer = new MousePointer();

        attackComponent = new AttackComponent();
        collidable = new Collidable();

    }

    @Override
    public void update() {
        //adds components Pairs to the queue
        for(Integer e: entities){
            transform = scene.entityManager.getEntityComponentInstance(e, transform.getClass());
            sprite = scene.entityManager.getEntityComponentInstance(e, sprite.getClass());
           
            array.add(new Pair(transform, sprite));
        } 
    }

    @Override
    public void init() {
      //Fetch entities with the Transform and Sprite components
      entities = scene.entityManager.getEntitiesWithComponents(transform.getClass(), sprite.getClass(), worldEntity.getClass());
      //Fetch User interface entities //This entities cannot be deleted or added after init()
      UIentities = scene.entityManager.getEntitiesWithComponents(uiEntity.getClass());
      //Fetch mouse pointers
      mousePointers = scene.entityManager.getEntitiesWithComponents(mousePointer.getClass());

      //fetch weapon attack components
      attackComponents = scene.entityManager.getEntitiesWithComponents(attackComponent.getClass());

      //Fetch collidables
      collidables = scene.entityManager.getEntitiesWithComponents(Collidable.class);

     
      array = new ArrayList <Pair<Transform, Sprite>>(); 
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
    
    @Override
    public void render(Graphics2D g) {

        array.sort(new myComparator());
        //Render all entities with a Transform and Sprite Component
        for(Pair<Transform, Sprite> t : array){
            if(t.second.visible) {
                g.drawImage(t.second.currentFrame, (int) t.first.position.x,t.first._renderedY ,t.second.width,t.second.height,null);
            }
        }
        
        if(debugRender){
            for(Integer col: collidables){
                collidable = scene.entityManager.getEntityComponentInstance(col, Collidable.class);
                transform = scene.entityManager.getEntityComponentInstance(col, Transform.class);
                if(collidable.active)   g.drawRect((int)transform.position.x, (int)transform._renderedY, (int)collidable.hitbox.x, (int)collidable.hitbox.y);
            }
            
            for(Integer attk : attackComponents){
                attackComponent = scene.entityManager.getEntityComponentInstance(attk, AttackComponent.class);
                transform = scene.entityManager.getEntityComponentInstance(attk, Transform.class);
                
                for(AttackCollider attkCol : attackComponent.arrColliders){
                    //transform.position.add(attkCol.relativePosition)
                    if(attkCol.active)  g.drawRect((int)transform.position.add(attkCol.relativePosition).x, (int)(transform.position.add(attkCol.relativePosition).y - transform.position.z), (int)attkCol.hitbox.x, (int)attkCol.hitbox.y);                
                }
            }
        }
        
        //transformthe grphic coordinates to the screen coordinates
        originalAT = g.getTransform();
        AffineTransform at = new AffineTransform();
        at.setTransform(4, 0, 0, 4, 1, 0);
        g.setTransform(at);  
        
        //Renders UIEntities after rendering the game entities
        for(Integer ui: UIentities){
            uiEntity = scene.entityManager.getEntityComponentInstance(ui, uiEntity.getClass());
            
            if(uiEntity._uiSprite.visible && uiEntity.mainUI && !uiEntity.worldUI) {
                //g.drawImage(uiEntity.currentFrame, (int) uiEntity.position.x,(int) uiEntity.position.y, uiEntity.width, uiEntity.height, null);
                uiEntity.UIRender(g, scene);
            }
        }
        
        //render mouse pointer
        for(Integer mp: mousePointers){
            mousePointer = scene.entityManager.getEntityComponentInstance(mp, mousePointer.getClass());
            //draws held item
            mousePointer.UIRender(g, scene);
            //draws tthe poiter sprite if it has one
            if(scene.entityManager.hasComponent(mp, sprite.getClass())){
                sprite = scene.entityManager.getEntityComponentInstance(mp, sprite.getClass());
                g.drawImage(sprite.currentFrame,(int)mousePointer.position.x, (int)mousePointer.position.y, sprite.width, sprite.height, null);
            }
        }        
        /*if (scene.display.getKeyManager().keys[KeyEvent.VK_P]) {
            //Display the inventory
            inventory = new UserInterface(1);
            inventory.render(g);
        }*/
        
        //render the attackComponents
       /* for(Integer p : attackComponents) {
            attackComponent = scene.entityManager.getEntityComponentInstance(p, attackComponent.getClass());
        
            for(AttackCollider ate : attackComponent.arrColliders) {
                //Get the transform of each tool/weapon
                Transform wpnTrans = scene.entityManager.getEntityComponentInstance(p, transform.getClass());
                Rectangle rectangle = new Rectangle((int) (wpnTrans.position.x + ate.relativePosition.x), (int) (wpnTrans.position.y + ate.relativePosition.y), (int) ate.hitbox.x, (int) ate.hitbox.y);
                g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
        }*/
        
        array.clear();
        
        g.setTransform(originalAT);
    }

    
    private class myComparator implements Comparator <Pair<Transform, Sprite> >{

        @Override
        public int compare
        (Pair<Transform, Sprite> p1, Pair<Transform, Sprite > p2) {
            
            if(p1.first.position.z + p1.first._renderedY > p2.first.position.z + p2.first._renderedY){
                return 1;
            }else if (p1.first.position.z + p1.first._renderedY < p2.first.position.z + p2.first._renderedY){
                return -1;
            }else return 0; 
        }
            
    }
}
