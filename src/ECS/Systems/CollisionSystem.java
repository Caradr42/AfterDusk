package ECS.Systems;

import ECS.Components.Collidable;
import ECS.Components.Inventory;
import ECS.Components.Item;
import ECS.Components.MousePointer;
import ECS.Components.Playable;
import ECS.Components.Player;
import ECS.Components.Sprite;
import ECS.Components.Tile;
import ECS.Components.Transform;
import ECS.Entity;
import ECS.SystemJob;
import Scene.Scene;
import Utility.Point;
import java.awt.Rectangle;
import static java.lang.Integer.min;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author pablo
 */
public class CollisionSystem extends SystemJob{
    
    //An item must have a collidable assigned by reference
    private Item item;
    //A playable must have a collidable assigned by reference
    private Playable playable;

    private Tile tile;
    private Tile tileCollidable;
    private ArrayList<Integer> arrPlayables;
    private static ArrayList<Integer> arrItems;
    private ArrayList<Integer> arrTiles;
    private ArrayList<Integer> entitiesCollidable;
    private ArrayList<Integer> tilesCollidable;
    private Collidable collision;

    //private ArrayList<Integer> arr;
    
    //Mouse Pointer 
    ArrayList<Integer> mousePointers;
    MousePointer mousePointer;
    //duplication bug prevention

    public CollisionSystem(Scene scene, boolean active) {
        super(scene, active);
        mousePointer = new MousePointer();
        mousePointers = new ArrayList<>();
    }
    
    @Override
    public void update() {
        initializeEntities();
        
        //For each entity
        for(int i = 0; i < entities.size(); i++) {
            
            //check all the entities
            for(int j = i; j < entities.size(); j++) {
                
                //Except itself
                if( i != j) {
                    checkCollision(entities.get(i), entities.get(j));
                }
            }
        }
        for(int i=0;i<entitiesCollidable.size();i++){
            scene.entityManager.getEntityComponentInstance(entitiesCollidable.get(i), collision.getClass()).setCollidable.clear();
            for(int j=0;j<arrTiles.size();j++){
                collisionTileEntity(entities.get(i), arrTiles.get(j));
            } 
        }
    }
    
    @Override
    public void init() { 
        mousePointers = scene.entityManager.getEntitiesWithComponents(mousePointer.getClass());
        mousePointer = scene.entityManager.getEntityComponentInstance(mousePointers.get(0), mousePointer.getClass());
        initializeEntities();
    }

    @Override
    public void onCreate() {        
    }

    @Override
    public void onDestroy() {    
    }
    
    /**
     * Check if entity i is to the left/right/up/down of entity j
     * @param i
     * @param j 
     */
    public static boolean checkCollision(int i, int j) {

        Boolean bRight = false;
        Boolean bLeft = false;
        Boolean bUp = false;
        Boolean bDown = false;

        Transform transformi = new Transform();
        Collidable collidablei = new Collidable();
        
        Transform transformj = new Transform();
        Collidable collidablej = new Collidable();
        
        //Getting th collidable and the tranform of the first entity
        Entity e = scene.entityManager.getEntityByID(i);
        collidablei = scene.entityManager.getEntityComponentInstance(i, collidablei.getClass());
        transformi = scene.entityManager.getEntityComponentInstance(i, transformi.getClass());
        
        //Getting the collidable and the transform of the second entity
        Entity e2 = scene.entityManager.getEntityByID(j);
        collidablej = scene.entityManager.getEntityComponentInstance(j, collidablej.getClass());
        transformj = scene.entityManager.getEntityComponentInstance(j, transformj.getClass());

        //Rectangle of the first entity
        Rectangle firstRect = new Rectangle((int)transformi.position.x, (int)transformi._renderedY, (int)collidablei.hitbox.x, (int)collidablei.hitbox.y);
        
        //Rectangle of the second entity
        Rectangle secondRect = new Rectangle((int)transformj.position.x, (int)transformj._renderedY, (int)collidablej.hitbox.x, (int)collidablej.hitbox.y);

        //The center of the first object(x, y, z)
        double firstCenterX = transformi.position.x + collidablei.hitbox.x / 2;
;
        double firstCenterY = transformi._renderedY + collidablei.hitbox.y / 2;
        double firstCenterZ;
        
        
        //The length of the first object in the x axis from the center to the border
        double firstLengthX = collidablei.hitbox.x / 2;
        
        //The length of the first object in the y axis from the center to the border
        double firstLengthY = collidablei.hitbox.y / 2;

        //The length of the first object in the z axis from the center to the border
        double firstLengthZ;

        //The center of the second object(x, y, z)
        double secondCenterX = transformj.position.x + collidablej.hitbox.x / 2;
        double secondCenterY = transformj._renderedY + collidablej.hitbox.y / 2;
        double secondCenterZ;

        //The length of the second object in the x axis from the center to the border
        double secondLengthX = collidablej.hitbox.x / 2;

        //The length of the second object in the y axis from the center to the border
        double secondLengthY = collidablej.hitbox.y / 2;

        //The length of the second object in the z axis from the center to the border
        double secondLengthZ;

        //
        if (firstRect.intersects(secondRect) && collidablei.active && collidablej.active) {
            //Point of the upper left corner of the first object
            Point firstPoint1 = new Point(firstCenterX - firstLengthX, firstCenterY - firstLengthY);

            //Point of the upper right corner of the first object
            Point firstPoint2 = new Point(firstCenterX + firstLengthX, firstCenterY - firstLengthY);

            //Point of the bottom left corner of the first object
            Point firstPoint3 = new Point(firstCenterX - firstLengthX, firstCenterY + firstLengthY);

            //Point of the bottom right corner of the first object
            Point firstPoint4 = new Point(firstCenterX + firstLengthX, firstCenterY + firstLengthY);

            //Point of the upper left corner of the first object
            Point secondPoint1 = new Point(secondCenterX - secondLengthX, secondCenterY - secondLengthY);

            //Point of the upper right corner of the first object
            Point secondPoint2 = new Point(secondCenterX + secondLengthX, secondCenterY - secondLengthY);

            //Point of the bottom left corner of the first object
            Point secondPoint3 = new Point(secondCenterX - secondLengthX, secondCenterY + secondLengthY);

            //Point of the bottom right corner of the first object
            Point secondPoint4 = new Point(secondCenterX + secondLengthX, secondCenterY + secondLengthY);
            
            /*
                Distance in the x axis between the ideally closest pair of points of both entities.
                Assuming that the first entity is to the left of the second*/
            int A = abs((int) (secondPoint1.x - firstPoint2.x));
            
            
            /*
                Distance in the x axis between the ideally closest pair of points of both entities.
                Assuming that the first entity is to the right of the second*/
            int B = abs((int) (firstPoint1.x - secondPoint2.x));
            
            /*
                Distance in the y axis between the ideally closest pair of points of both entities.
                Assuming that the first entity is above the second*/              
            int C = abs((int) (secondPoint1.y - firstPoint3.y));
            
            /*
                Distance in the y axis between the ideally closest pair of points of both entities.
                Assuming that the first entity is below the second*/
            int D = abs((int) (firstPoint1.y - secondPoint3.y));
            

            //Just one boolean can be true, that is why there are several ifs
            bLeft = A < B && A <= min(C, D);
            
            if(!bLeft)
                bRight = B < A && B <= min(C, D);
            
            if(!bRight)
                bUp = C < D;
            
            if(!bUp)
                bDown = D < C;
            
            
            //if the first entity is the player
            /*if("Player".equals(e.getName())) {
                //And the second an item
                if(arrItems.contains(e2.getID())) {
                    //If the user press the E in the collision
                    if(scene.display.getKeyManager().isE) {
                        System.out.println("E pressed A");
                        scene.entityManager.getEntityComponentInstance(j, (new Item()).getClass()).isInInventory = true;
                        
                        //sprite = scene.entityManager.getEntityComponentInstance(i, sprite.getClass());
                        //sprite.visible = false;
                    }
                }
            }
            
            //Or the second entity is the player
            else */if("Player".equals(e2.getName())) {
                //And the first an item
                if(arrItems.contains(e.getID())) {
                    //If the user press the E in the collision
                    
                    if(scene.display.getKeyManager().isE) {                      
                        //Play pick up sound
                        Assets.Assets.pickUp.play();

                        //fetch the player's inventory ID and instance
                        Integer inventoryID = scene.entityManager.getEntityComponentInstance(j, (new Playable()).getClass()).inventory;
                        Integer handsInventoryID = scene.entityManager.getEntityComponentInstance(j, (new Player()).getClass()).LRInventory;
                        
                        //if space available in the players inventory it will add the item to it
                        boolean added = addToInventory(handsInventoryID, i) || addToInventory(inventoryID, i);
                        
                        
                        if(added){
                            //adds the player as parent of the item when it is collected
                                if(i != 0){
                                    Transform itemTransform = scene.entityManager.getEntityComponentInstance(i, Transform.class);
                                    if(itemTransform != null) itemTransform.parent = j;
                                }
                            
                            scene.entityManager.getEntityComponentInstance(i, (new Item()).getClass()).isInInventory = true; 
                            collidablei.active = false;
                        }
                        
                        //sprite = scene.entityManager.getEntityComponentInstance(j, sprite.getClass());
                        //sprite.visible = false;
                    }
                }
            }
        }
        
        return bRight && bLeft && bUp && bDown;
    }
    
    
    public void collisionTileEntity(int i, int j){
        
        Transform transformi = new Transform();
        Collidable collidablei = new Collidable();
        
        Transform transformj = new Transform();
        tileCollidable = new Tile();
        
        
        //Getting th collidable and the tranform of the first entity
        Entity e = scene.entityManager.getEntityByID(i);
        collidablei = scene.entityManager.getEntityComponentInstance(i, collidablei.getClass());
        transformi = scene.entityManager.getEntityComponentInstance(i, transformi.getClass());
        //Getting the collidable and the transform of the second entity
        
        transformj = scene.entityManager.getEntityComponentInstance(j, transformj.getClass());
        tileCollidable = scene.entityManager.getEntityComponentInstance(j, tile.getClass());
        
        //Rectangle of the first entity
        Rectangle firstRect = new Rectangle((int)transformi.position.x, (int)transformi._renderedY, (int)collidablei.hitbox.x, (int)collidablei.hitbox.y);
        
        //Rectangle of the second entity
        Rectangle secondRect = new Rectangle((int)transformj.position.x, (int)transformj._renderedY, (int)16,(int)16);
                
        //floor of player and Tile
        double floorPlayer = (transformi.position.z-32); 
        double floorTile = (transformj.position.z-16);
        
        //Check intersection in x & y & z.

        if(firstRect.intersects(secondRect) && collidablei.active && tileCollidable.isCollidable()&&((transformi.position.z >= floorTile)&&(floorPlayer<=transformj.position.z))){
            //System.out.println("Collision");

            collidablei.setCollidable.add(j);
        }
    }
    
    public void initializeEntities() {
        item = new Item();
        tile = new Tile();
        playable = new Playable();
        arrItems = new ArrayList<>();
        arrPlayables = new ArrayList<>();
        arrTiles = new ArrayList<>();
        entities = new ArrayList<>();
        entitiesCollidable = new ArrayList<>();
        tilesCollidable= new ArrayList<>();
        collision = new Collidable();
        
        //fetching the entities with the playable component
        arrItems = scene.entityManager.getEntitiesWithComponents(item.getClass());
        
        //Fetching the entities with the item component
        arrPlayables = scene.entityManager.getEntitiesWithComponents(playable.getClass());
        
        //Fetching the tiles
        arrTiles = scene.entityManager.getEntitiesWithComponents(tile.getClass());
        
        entitiesCollidable = scene.entityManager.getEntitiesWithComponents(collision.getClass());
        
        for(int i = 0; i < arrItems.size(); i++) {
            entities.add(arrItems.get(i));
        }
        
        for(int i = 0; i < arrPlayables.size(); i++) {
            entities.add(arrPlayables.get(i));
        }
    }
    
    /**
     * adds an item to the inventory is space is available
     * @param inventoryID
     * @return 
     */
    private static boolean addToInventory(Integer inventoryID, Integer item){
        int tempID = inventoryID;
        Inventory inventory = scene.entityManager.getEntityComponentInstance(tempID, new Inventory().getClass());
        
        for(int inv = 0; tempID != 0; ++inv){ 
            
            for(int j = 0; j < inventory.size; ++j){
                if(inventory.slots.get(j) == 0){
                    inventory.slots.set(j, item);
                    return true;
                }
            }
            
            tempID = inventory.nextInventory;
            if(tempID != 0){
                inventory = scene.entityManager.getEntityComponentInstance(tempID, inventory.getClass());
            }
        }
        return false;
    }    
}