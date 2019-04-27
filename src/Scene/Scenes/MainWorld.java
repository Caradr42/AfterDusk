package Scene.Scenes;

import Assets.Assets;
import ECS.Components.*;
import ECS.Entity;
import ECS.SystemJob;
import ECS.Systems.*;
import Maths.Vector3;
import Scene.Scene;
import Signals.Listener;
import graphics.Camera;
import graphics.Display;
import java.util.ArrayList;
import Maths.Vector2;
import java.util.Arrays;

/**
 * Test scene. extends Scene 
 * 
 * @author Carlos Adrián Guerra Vázquez
 * @date 27/02/2019
 * @version 1.0
 */
public class MainWorld extends Scene{
    
    /**
     * This scene constructor. assigns a display were this scene's entities will
     * render.
     * @param display
     * @param c
     */
    public MainWorld(Display display, Camera c) {
        super(display, c);
    }
    
    /**
     * Here you add entities to the game manager of this scene with their
     * respective components instances.
     */
    @Override
    protected void addEntities() {
        
        //mouse pointer
        Entity pointer = entityManager.createEntityWithComponents("pointer", 
                new MousePointer()
        );
        
        //ITEMS
        
        //A weird item in the players inventory
        Entity weirdItm = entityManager.createEntityWithComponents("weird", 
                new Item ("weird", true),
                new Collidable(new Vector3(16, 16, 1)),
                new Transform(new Vector3(50, 50, 16)),
                new Sprite("weird", true, 16, 16, 10, new ArrayList<>(Arrays.asList("weird"))),
                new WorldEntity()
        );

        //a shield in the players inventory
        Entity shieldItm = entityManager.createEntityWithComponents("shield", 
                new Tool(new Entity(0), new ArrayList<>(Arrays.asList(0)), new ArrayList<>(Arrays.asList(0))),
                new Item ("shield", true),
                new Collidable(new Vector3(16, 16, 1)),
                new Transform(new Vector3(70, 70, 16)),
                new Sprite("shield", true, 16, 16, 10, new ArrayList<>(Arrays.asList("shield"))),
                new WorldEntity()
        );
        //==============
        
        //INVENTORIES
        
        //enemy inventory     
        Entity enemyInv = entityManager.createEntityWithComponents("Enemy_Inventory", 
                new Inventory(0, 5, new ArrayList<>(Arrays.asList(weirdItm.getID())))
        );
        
        //The player's internal inventories
        Entity playerInv4 = entityManager.createEntityWithComponents("Player_Inventory4", 
                new Inventory(0, 6, new ArrayList<>(Arrays.asList()))
        );
        
        Entity playerInv3 = entityManager.createEntityWithComponents("Player_Inventory3", 
                new Inventory(playerInv4.getID(), 6, new ArrayList<>(Arrays.asList(weirdItm.getID())))
        );
        
        Entity playerInv2 = entityManager.createEntityWithComponents("Player_Inventory2", 
                new Inventory(playerInv3.getID(), 6, new ArrayList<>(Arrays.asList(weirdItm.getID())))
        );
        
        Entity playerInv = entityManager.createEntityWithComponents("Player_Inventory", 
                new Inventory(playerInv2.getID(), 6, new ArrayList<>(Arrays.asList(weirdItm.getID() ,shieldItm.getID())))
        );
        //------
        Entity playerLR = entityManager.createEntityWithComponents("Player_LR_Inventory", 
                new Inventory(0 , 2, new ArrayList<>(Arrays.asList(shieldItm.getID())))
        );
        //----
        Entity playerPassives3 = entityManager.createEntityWithComponents("Player_Passives_Inventory3", 
                new Inventory(0 , 3, new ArrayList<>(Arrays.asList()))
        );
        
        Entity playerPassives2 = entityManager.createEntityWithComponents("Player_Passives_Inventory2", 
                new Inventory(playerPassives3.getID() , 3, new ArrayList<>(Arrays.asList()))
        );
        
        Entity playerPassives = entityManager.createEntityWithComponents("Player_Player_Passives_Inventory", 
                new Inventory(playerPassives2.getID() , 3, new ArrayList<>(Arrays.asList()))
        );
        //------
        
        Entity playerActives = entityManager.createEntityWithComponents("Player_Player_Actives_Inventory", 
                new Inventory(0 , 9, new ArrayList<>(Arrays.asList()))
        );
        //UI INVENTORIES
        
        //player ui inventories
        Entity mainInventory = entityManager.createEntityWithComponents("Player_UIInventory_grid", 
                new Transform(12, 43),
                new Sprite("grid", true, 103, 18, 0, new ArrayList<>(Arrays.asList("1x6Slots_light"))),
                new UIEntity("grid", false, null/*childs ID's go here*/),
                new UIInventory("grid", playerInv.getID())
            );
        
        Entity LRInventory = entityManager.createEntityWithComponents("Player_UIInventory_LR", 
                new Transform(12, 23),
                new Sprite("LR", true, 35, 18, 0, new ArrayList<>(Arrays.asList("1x2Slots_dark"))),
                new UIEntity("LR", false, null),
                new UIInventory("LR", playerLR.getID())
            );
        
        Entity LRUIInventory = entityManager.createEntityWithComponents("Player_UI_LR",
                new Transform(0, 10),
                new Sprite("LRUI", true, 35, 18, 0, new ArrayList<>(Arrays.asList("1x2Slots_dark"))),
                new UIEntity("LRUI", false, null),
                new UIInventory("LRUI", playerLR.getID())
            );
        
        Entity passivesInventory = entityManager.createEntityWithComponents("Player_UI_Passives_Inventory", 
                new Transform(127, 23),
                new Sprite("passives", true, 52, 18, 0, new ArrayList<>(Arrays.asList("1x3Slots_dark"))),
                new UIEntity("passives", false, null),
                new UIInventory("Passives", playerPassives.getID())
            );
        
        Entity activesInventory = entityManager.createEntityWithComponents("Player_UI_Actives_Inventory", 
                new Transform(0, 10),
                new Sprite("actives", true, 154, 18, 0,  new ArrayList<>(Arrays.asList("1x9Slots_dark"))),
                new UIEntity("actives", false, null),
                new UIInventory("Actives", playerActives.getID())
            );
        //UI Buttons
        
        Entity button = entityManager.createEntityWithComponents("button", 
                new Transform(50, 50),
                new Sprite("Button", false, 48, 10, 0, new ArrayList<>(Arrays.asList("Button_48_selected"))),
                new UIEntity("Button", false, null),
                new UIButton("Button")
        );
        
        //USER INTERFACES
        
        //The players Inventory user interface, has a reference to the player internal inventory
        Entity InventoryUI = entityManager.createEntityWithComponents("Player_Inventory", 
                new Transform( display.width / c.scale / 2 - (195/2) , display.height / c.scale / 2 - (135 / 2) -2),
                new Sprite("Player_Inventory", false, 195, 135, 0, new ArrayList<>(Arrays.asList("inventory"))),
                new UIEntity("Player_Inventory", true, 
                    new ArrayList<>(Arrays.asList( 
                        mainInventory.getID(), 
                        LRInventory.getID(), 
                        passivesInventory.getID(), 
                        button.getID()))) 
        );
        
        //the player actives hotbar
        Entity activesUI = entityManager.createEntityWithComponents("Player_actives", 
                new Transform( display.width / c.scale / 2 - (160/2) + 3, display.height / c.scale - 28 ),
                new Sprite("actives_bar", true, 160, 32, 0,new ArrayList<>(Arrays.asList("actives_bar"))),
                new UIEntity("actives_bar", true, 
                    new ArrayList<>(Arrays.asList(activesInventory.getID()))) 
        );
        
        Entity LRUI = entityManager.createEntityWithComponents("Player_RL", 
                new Transform( 16 , display.height / c.scale - 28),
                new Sprite("RL_bar", true, 48, 32, 0, new ArrayList<>(Arrays.asList("RL_bar"))),
                new UIEntity("RL_bar", true,
                    new ArrayList<>(Arrays.asList(LRUIInventory.getID()))) 
        );
        
        
        
        
        //PLAYABLE ENTITIES
        
        entityManager.createEntityWithComponents("Player",            
            new Transform(new Vector3(50,50, 32)),
            new Sprite("sprite", true, 32, 32, 8, new ArrayList<>(Arrays.asList("player_down","player_up","player_left","player_right"))),
            new WorldEntity(),
            new Player("player", playerLR.getID(), playerPassives.getID(), playerActives.getID() ),
            new Playable(100, playerInv.getID(), new Vector3()),
            new Collidable(new Vector3(32, 32, 1))
        );

        
        entityManager.createEntityWithComponents("Enemy1", 
                new Transform(new Vector3(90, 90, 48)),
                new Sprite("enemy", true, 64, 80, 10, new ArrayList<>(Arrays.asList("enemy"))),
                new WorldEntity(),
                new Collidable(new Vector3(64, 80, 1)),
                new Playable(300, enemyInv.getID(), new Vector3()));
        
        //TILES 
        
        Sprite grassTopSprite = new Sprite("grass", true, 16, 16, 10, new ArrayList<>(Arrays.asList("grass")));
        Sprite grassSideSprite = new Sprite("grassSide", true, 16, 16, 10, new ArrayList<>(Arrays.asList("grassSide")));
        
        //draw grass grid
        for(int x = 0; x < 960; x += 16){
            for(int y = 0; y < 960; y += 16){
                Entity side = entityManager.createEntityWithComponents("grassSide", 
                        new Transform(new Vector3(x,y,-16)),
                        grassSideSprite,
                        new WorldEntity()
                        );
                
                entityManager.createEntityWithComponents("grass",
                        new Tile("grass" + Integer.toString(x) + "_" + Integer.toString(y), grassTopSprite, side.getID()),
                        new Transform(new Vector3(x,y,0)), 
                        grassTopSprite,
                        new WorldEntity()
                                                //new Sprite("grass", true, 16, 16, 10, new ArrayList<>(Arrays.asList("grass")))
                );
            }
        }
        
        Entity side = entityManager.createEntityWithComponents("grassSide", 
                        new Transform(new Vector3(-80,-80,-16)),
                        grassSideSprite,
                        new WorldEntity()
                        );
        
        entityManager.createEntityWithComponents("grass",
                        new Tile("grass2", grassTopSprite, side.getID()),
                        new Transform(new Vector3(-80,-80,0)), 
                        grassTopSprite,
                        new WorldEntity()
                        //new Sprite("grass", true, 16, 16, 10, new ArrayList<>(Arrays.asList("grass")))
            );
    }
    
    /**
     * Here you add systems to the system manager of the scene
     * only add one type of system per system type which extends SystemJob.
     */
    @Override
    protected void addSystems(){
        systemJobManager.addSystems(
            new CollisionSystem(this),
                
            new GameManagerSystem(this),
            new ItemSystem(this),
            new MousePointerSystem(this),
            new PlayerSystem(this),
            new RenderSystem(this),
            new SpriteSystem(this),
            new TransformSystem(this),
            new UIButtonSystem(this),
            new UIEntitiesSystem(this),
            new UIInventorySystem(this)
        );
    }
}