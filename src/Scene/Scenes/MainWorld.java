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
public class MainWorld extends Scene {

    /**
     * This scene constructor. assigns a display were this scene's entities will
     * render.
     *
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

        //The colliders are for the tools, but we are testing, lets put it in the player
        ArrayList<AttackCollider> playerColliders = new ArrayList<>();

        playerColliders.add(new AttackCollider(new Vector3(32,32, 1), new Vector3(), 84, 36));
        
        ArrayList<AttackCollider> enemyColliders = new ArrayList<>();
        
        enemyColliders.add(new AttackCollider(new Vector3(64, 80, 1), new Vector3(), 36,46));

//GAME START SCREEEN ENTITIES
    //UI BUTTONS
        Entity continueButton = entityManager.createEntityWithComponents("continueButton", 
                new Transform(0,0,0),
                new Sprite("continueButton", true, 72, 15, 0 , new ArrayList<>(Arrays.asList("Button_48"))),
                new UIEntity("continueButton", false, null),
                new UIButton("continueButton"," CONTINUE", 1)
        );
        
        Entity newGameButton = entityManager.createEntityWithComponents("newGameButton", 
                new Transform(0,17,0),
                new Sprite("newGameButton", true, 72, 15, 0 , new ArrayList<>(Arrays.asList("Button_48"))),
                new UIEntity("newGameButton", false, null),
                new UIButton("newGameButton"," NEW GAME", 2)
        );
        
        Entity exitButton = entityManager.createEntityWithComponents("exitButton", 
                new Transform(0,34,0),
                new Sprite("exitButton", true, 72, 15, 0 , new ArrayList<>(Arrays.asList("Button_48"))),
                new UIEntity("exitButton", false, null),
                new UIButton("exitButton"," EXIT GAME", 3)
        );
    //UI ENTITIES
        Entity buttonsWrap = entityManager.createEntityWithComponents("buttonsWrap", 
                new Transform(display.width / c.scale / 2 - 72/2 , display.height / c.scale / 2 - 15/2 ,0),
                new Sprite("buttonsWrap", true, 72, 15, 0 , new ArrayList<>(Arrays.asList("null"))),
                new UIEntity("buttonsWrap", true, new ArrayList<>(Arrays.asList(continueButton.getID(), newGameButton.getID(), exitButton.getID())))
        );
        
        Entity afterDusk = entityManager.createEntityWithComponents("AfterDusk", 
                new Transform(display.width / c.scale / 2 - 352/2 , 20 ,0),
                new Sprite("AfterDusk", true, 352, 72, 0 , new ArrayList<>(Arrays.asList("AfterDusk"))),
                new UIEntity("AfterDusk", true, new ArrayList<>(Arrays.asList()))
        );
        //AfterDusk
        
///ENTITIES      
    //MOUSE POINTER
        //mouse pointer
        Entity pointer = entityManager.createEntityWithComponents("pointer",
                new MousePointer()
        );


    //ITEMS        
        //TOOLS
            //The sword Tool
            Entity swordOne = entityManager.createEntityWithComponents("sword1",
                    new Item("sword1", true),
                    new Sprite("E_sword", true, 16, 16, 8, new ArrayList<>(Arrays.asList("E_sword"))),
                    new Collidable(new Vector3(16, 16, 1)),
                    new Tool(-1),
                    //the x and y of enemy are 90 and 90
                    new Transform(new Vector3(0,0,16)),
                    new AttackComponent(playerColliders),
                    new WorldEntity(),
                    new Electricity()
        );

        Entity swordTwo = entityManager.createEntityWithComponents("sword2",
                new Item("sword2", true),
                new Sprite("E_sword", true, 16, 16, 8, new ArrayList<>(Arrays.asList("E_sword"))),
                new Collidable(new Vector3(16, 16, 1)),
                new Tool(-1),
                //the x and y of enemy are 90 and 90
                new Transform(new Vector3(0, 0, 16)),
                new AttackComponent(enemyColliders),
                new WorldEntity()
        );

        //------------
        
        //Regular Items
            
        //A weird item in the players inventory
        Entity weirdItm = entityManager.createEntityWithComponents("weird",
                new Item("weird", true),
                new Collidable(new Vector3(16, 16, 1)),
                new Transform(new Vector3(50, 50, 16)),
                new Sprite("weird", true, 16, 16, 10, new ArrayList<>(Arrays.asList("weird"))),
                new WorldEntity()
        );

        //a shield in the players inventory
        Entity shieldItm = entityManager.createEntityWithComponents("shield",
                new Tool(new Entity(0), new ArrayList<>(Arrays.asList(0)), new ArrayList<>(Arrays.asList(0))),
                new Item("shield", true),
                new Collidable(new Vector3(16, 16, 1)),
                new Transform(new Vector3(70, 70, 16)),
                new Sprite("shield", true, 16, 16, 10, new ArrayList<>(Arrays.asList("shield"))),
                new WorldEntity()
        );
        //==============

    //INVENTORIES
        //enemy inventory     
        Entity enemyInv = entityManager.createEntityWithComponents("Enemy_Inventory",
                new Inventory(0, 5, new ArrayList<>(Arrays.asList(swordTwo.getID())))
        );
        
        //father inventory
        Entity fatherInv = entityManager.createEntityWithComponents("Father_Inv", 
                new Inventory(0, 5, new ArrayList<>(Arrays.asList())));

        //The player's internal inventories
        Entity playerInv4 = entityManager.createEntityWithComponents("Player_Inventory4",
                new Inventory(0, 6, new ArrayList<>(Arrays.asList()))
        );

        Entity playerInv3 = entityManager.createEntityWithComponents("Player_Inventory3",
                new Inventory(playerInv4.getID(), 6, new ArrayList<>(Arrays.asList()))
        );

        Entity playerInv2 = entityManager.createEntityWithComponents("Player_Inventory2",
                new Inventory(playerInv3.getID(), 6, new ArrayList<>(Arrays.asList()))
        );

        Entity playerInv = entityManager.createEntityWithComponents("Player_Inventory",
                new Inventory(playerInv2.getID(), 6, new ArrayList<>(Arrays.asList(weirdItm.getID(), shieldItm.getID())))
        );
        //------
        Entity playerLR = entityManager.createEntityWithComponents("Player_LR_Inventory",
                new Inventory(0, 2, new ArrayList<>(Arrays.asList( swordOne.getID())))
        );
        //----
        Entity playerPassives3 = entityManager.createEntityWithComponents("Player_Passives_Inventory3",
                new Inventory(0, 3, new ArrayList<>(Arrays.asList()))
        );

        Entity playerPassives2 = entityManager.createEntityWithComponents("Player_Passives_Inventory2",
                new Inventory(playerPassives3.getID(), 3, new ArrayList<>(Arrays.asList()))
        );

        Entity playerPassives = entityManager.createEntityWithComponents("Player_Player_Passives_Inventory",
                new Inventory(playerPassives2.getID(), 3, new ArrayList<>(Arrays.asList()))
        );
        //------

        Entity playerActives = entityManager.createEntityWithComponents("Player_Player_Actives_Inventory",
                new Inventory(0, 9, new ArrayList<>(Arrays.asList()))
        );
    //UI INVENTORIES

        //player ui inventories
        Entity mainInventory = entityManager.createEntityWithComponents("Player_UIInventory_grid",
                new Transform(12, 43),
                new Sprite("grid", true, 103, 18, 0, new ArrayList<>(Arrays.asList("1x6Slots_light"))),
                new UIEntity("grid", false, null),
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
                new Sprite("actives", true, 154, 18, 0, new ArrayList<>(Arrays.asList("1x9Slots_dark"))),
                new UIEntity("actives", false, null),
                new UIInventory("Actives", playerActives.getID())
        );
    //UI BUTTONS

        Entity mapButton = entityManager.createEntityWithComponents("map_button",
                new Transform(16, 15),
                new Sprite("map_button", true, 48, 13, 0, new ArrayList<>(Arrays.asList("Tab_light", "Tab_dark"))),
                new UIEntity("map_button", false, null),
                new UIButton("map_button" ,"MAP" ,0)
        );
        
        Entity questsButton = entityManager.createEntityWithComponents("quests_button",
                new Transform(65, 15),
                new Sprite("quests_button", true, 48, 13, 0, new ArrayList<>(Arrays.asList("Tab_light", "Tab_dark"))),
                new UIEntity("quests_button", false, null),
                new UIButton("quests_button" ,"QUESTS" ,1)
        );
        
        Entity optionsButton = entityManager.createEntityWithComponents("options_button",
                new Transform(114, 15),
                new Sprite("options_button", true, 48, 13, 0, new ArrayList<>(Arrays.asList("Tab_light", "Tab_dark"))),
                new UIEntity("options_button", false, null),
                new UIButton("options_button" ,"OPTIONS" ,2)
        );
        
        Entity gameButton = entityManager.createEntityWithComponents("game_button",
                new Transform(163, 15),
                new Sprite("game_button", true, 48, 13, 0, new ArrayList<>(Arrays.asList("Tab_light", "Tab_dark"))),
                new UIEntity("game_button", false, null),
                new UIButton("game_button" ,"GAME" ,3)
        );
        
    //UI TEXTS
        
        Entity text = entityManager.createEntityWithComponents("text",
                new Transform(16, 48),
                new Sprite("text", true, 0, 0, 0, new ArrayList<>(Arrays.asList("null"))),
                new UIEntity("text", false, 0, null),
                new UIText("This is just some sample test please ignore me. This is just some sample test please ignore me." , 100, 100)
        );
        
        Entity dialogText = entityManager.createEntityWithComponents("dialog_text",
                new Transform(10, 14),
                new Sprite("dialog_text", true, 0, 0, 0, new ArrayList<>(Arrays.asList("null"))),
                new UIEntity("dialog_text", false, 0, null),
                new UIText(" You cheated not only the game, but yourself. You didn't grow. You didn't improve. You took a shortcut and gained nothing. You experienced a hollow victory. Nothing was risked and nothing was gained. It's sad that you don't know the difference." , 250, 62)
        );
    //CHILD UIENTITIES
        
        Entity playerPosition = entityManager.createEntityWithComponents("player_position",
                new Transform(182,85),
                new Sprite("player_position", true, 2, 2, 20, new ArrayList<>(Arrays.asList("effect2"))),
                new UIEntity("player_position", true, 0, null, true)
                //new UIEntity("hp", true, null, true)
        );
        
        Entity itemSelector = entityManager.createEntityWithComponents("item_selector",
                new Transform(0,10),
                new Sprite("item_selector", true, 18, 18, 17, new ArrayList<>(Arrays.asList("item_selector"))),
                new UIEntity("item_selector", true, null, true)
        );
        
        Entity hpBarBar = entityManager.createEntityWithComponents("hp",
                new Transform(2,12),
                new Sprite("hp", true, 1, 3, 0, new ArrayList<>(Arrays.asList("HP"))),
                new UIEntity("hp", true, null, true)
        );
        
        Entity energyBarBar = entityManager.createEntityWithComponents("energy",
                new Transform(2,12),
                new Sprite("energy", true, 1, 3, 0, new ArrayList<>(Arrays.asList("energy"))),
                new UIEntity("energy", true, null, true)
        );

    //USER INTERFACES
        Entity HPbar = entityManager.createEntityWithComponents("HP_bar",
                new Transform(0,0),
                new Sprite("HP_bar", false, 64, 16, 0, new ArrayList<>(Arrays.asList("HP_bar"))),
                new UIEntity("HP_bar", true, new ArrayList<>(Arrays.asList(hpBarBar.getID())))
        );
        
        Entity energyBar = entityManager.createEntityWithComponents("energy_bar",
                new Transform(0,18),
                new Sprite("energy_bar", false, 64, 16, 0, new ArrayList<>(Arrays.asList("energy_bar"))),
                new UIEntity("energy_bar", true, new ArrayList<>(Arrays.asList(energyBarBar.getID())))
        );
        
        //The players Inventory user interface, has a reference to the player internal inventory
        Entity InventoryUI = entityManager.createEntityWithComponents("Player_Inventory",
                new Transform(display.width / c.scale / 2 - (195 / 2), display.height / c.scale / 2 - (135 / 2) - 2),
                new Sprite("Player_Inventory", false, 195, 135, 0, new ArrayList<>(Arrays.asList("inventory"))),
                new UIEntity("Player_Inventory", true,
                        new ArrayList<>(Arrays.asList(
                                mainInventory.getID(),
                                LRInventory.getID(),
                                passivesInventory.getID()
                                )))
        );
        
        //the player actives hotbar
        Entity activesUI = entityManager.createEntityWithComponents("Player_actives",
                new Transform(display.width / c.scale / 2 - (160 / 2) + 3, display.height / c.scale - 28),
                new Sprite("actives_bar", false, 160, 32, 0, new ArrayList<>(Arrays.asList("actives_bar"))),
                new UIEntity("actives_bar", true,
                        new ArrayList<>(Arrays.asList(activesInventory.getID())))
        );

        Entity LRUI = entityManager.createEntityWithComponents("Player_RL",
                new Transform(16, display.height / c.scale - 28),
                new Sprite("RL_bar", false, 48, 32, 0, new ArrayList<>(Arrays.asList("RL_bar"))),
                new UIEntity("RL_bar", true,
                        new ArrayList<>(Arrays.asList( LRUIInventory.getID(),  itemSelector.getID())))
        );   
        
        //dialog box
        Entity dialogBox = entityManager.createEntityWithComponents("dialogBox",
                new Transform(display.width / c.scale / 2 - (256/2), display.height / c.scale - 82),
                new Sprite("dialogBox", false, 256, 72, 0, new ArrayList<>(Arrays.asList("dialog_box"))),
                new UIEntity("dialogBox", true, new ArrayList<>(Arrays.asList(dialogText.getID() )))
        );
        
        //the Game menu
        Entity menu = entityManager.createEntityWithComponents("menu",
                new Transform(display.width / c.scale / 2 - (278/2), display.height / c.scale / 2 - (150/2)),
                new Sprite("menu", false, 278, 150, 0, new ArrayList<>(Arrays.asList("menu_map","menu_quests", "menu_options" ,"menu_game"))),
                new UIEntity("menu", true, new ArrayList<>(Arrays.asList(mapButton.getID(), questsButton.getID(), optionsButton.getID(), gameButton.getID(), text.getID(), playerPosition.getID())))
        );
        
         
        
    //PLAYABLE ENTITIES



        Entity father = entityManager.createEntityWithComponents("father",
                new Transform(new Vector3(200, 100, 36)),
                new Sprite("father", true, 36, 36, 8, new ArrayList<>(Arrays.asList("father_down", "father_up", "father_left", "father_right"))),
                new WorldEntity(),
                //new Enemy(),
                new Talkative(new ArrayList<>(Arrays.asList(
                    new ArrayList<>(Arrays.asList(
                            "HI this is the firt Parragraph; first Line", 
                            "HI this is the firt Parragraph; second Line"
                    )),
                    new ArrayList<>(Arrays.asList(
                            "HI this is the second Parragraph; first Line", 
                            "HI this is the second Parragraph; second Line"
                    ))
                ))),
                new Playable(100, fatherInv.getID(), 1.5),
                new Collidable(new Vector3(32, 32, 36))


        //This item goes here because its transform is a child of the player's transform
      /*  Entity swordOne = entityManager.createEntityWithComponents("sword1",
                new Item("sword1", true),
                new Collidable(new Vector3(16, 16, 1)),
                new Tool(-1),
                //the x and y of enemy are 90 and 90
                new Transform(new Vector3(), player.getID()),
                new AttackComponent(playerColliders),
                new Electricity()
        );*/



        );



         Entity enemy = entityManager.createEntityWithComponents("Enemy1",

                new Transform(new Vector3(200, 90, 80)),
                new Enemy(),
                new Sprite("enemy", true, 64, 64, 5, new ArrayList<>(Arrays.asList("ball", "ball_down", "ball_up", "ball_left", "ball_right"))),
                new WorldEntity(),
                new Collidable(new Vector3(64, 80, 80)),
                new Playable(300, enemyInv.getID(), 1, true));
        

        Entity player = entityManager.createEntityWithComponents("Player",
                new Transform(new Vector3(100, 100, 32)),
                new Sprite("player", true, 32, 32, 8, new ArrayList<>(Arrays.asList("player_down", "player_up", "player_left", "player_right"))),
                new WorldEntity(),
                new Player("player", playerLR.getID(), playerPassives.getID(), playerActives.getID(), dialogText.getID()),
                new Movement(new Vector3(0,0,0)),
                new Playable(100, playerInv.getID(), 2, true),
                new Collidable(new Vector3(32, 32, 32))

        );


       




    //TILES 
        Sprite grassTopSprite = new Sprite("grass", true, 16, 16, 10, new ArrayList<>(Arrays.asList("grass")));
        Sprite grassSideSprite = new Sprite("grassSide", true, 16, 16, 10, new ArrayList<>(Arrays.asList("grassSide")));
        Sprite log = new Sprite("log", true, 16, 16, 10, new ArrayList<>(Arrays.asList("log")));

        //draw grass grid
        for (int x = 0; x < 960; x += 16) {
            for (int y = 0; y < 960; y += 16) {
                /*Entity side = entityManager.createEntityWithComponents("grassSide",
                        new Transform(new Vector3(x, y, -16)),
                        grassSideSprite,
                        new WorldEntity()
                );*/

                entityManager.createEntityWithComponents("grass",
                        new Tile("grass" + Integer.toString(x / 16) + "_" + Integer.toString(y / 16) , grassTopSprite, grassSideSprite),
                        new Transform(new Vector3(x, y, 0)),
                        grassTopSprite,
                        new WorldEntity()
                //new Sprite("grass", true, 16, 16, 10, new ArrayList<>(Arrays.asList("grass")))
                );
            }
        }

        
        //Example log
        entityManager.createEntityWithComponents("wood",
                        new Tile("log" + Integer.toString(200) + "_" + Integer.toString(200),true,log,log),
                        new Transform(200,200,32),
                        log, new WorldEntity()
                );
        entityManager.createEntityWithComponents("wood",
                        new Tile("log" + Integer.toString(200) + "_" + Integer.toString(200),true,log,log),
                        new Transform(216,216,16),
                        log, new WorldEntity()
                );
        entityManager.createEntityWithComponents("wood",
                        new Tile("log" + Integer.toString(200) + "_" + Integer.toString(200),true,log,log),
                        new Transform(264,264,16),
                        log, new WorldEntity()
                );


        /*Entity side = entityManager.createEntityWithComponents("grassSide",
                new Transform(new Vector3(-80, -80, 16)),
                grassSideSprite,
                new WorldEntity()
        );*/
        //System.err.println("MainWorld Tread: " + Thread.currentThread());
        entityManager.createEntityWithComponents("grass",
                new Tile("grass2", grassTopSprite, grassSideSprite),
                new Transform(new Vector3(-80, -80, 48)),
                grassTopSprite,
                new WorldEntity()
        //new Sprite("grass", true, 16, 16, 10, new ArrayList<>(Arrays.asList("grass")))
        );

        //the sword one will be in the right hand of the player
        //entityManager.getEntityComponentInstance(player, (new Player()).getClass()).rightHand = swordOne.getID();

    }

    /**
     * Here you add systems to the system manager of the scene only add one type
     * of system per system type which extends SystemJob.
     */
    @Override
    protected void addSystems() {
        systemJobManager.addSystems(
      
                new PlayerSystem(this, false),
                new CollisionEntityWeapon(this, true),
                new CollisionSystem(this, true),
                new EnemySystem(this, false),
                new GameManagerSystem(this, true),
                new ItemSystem(this, true),
                new MousePointerSystem(this, true),
                
                new SpriteSystem(this, true),
                new TileSystem(this, true),
                new TransformSystem(this, true),
                new InventorySystem(this, true),
                new UIButtonSystem(this, true),
                new UIEntitiesSystem(this, true),
                new UIInventorySystem(this, true),
                new UITextSystem(this, true),
                new WeaponColliderPositionSystem(this, true),
                new RenderSystem(this, true),
                new ActiveSystem(this, true),
                new ElectricSystem(this, true),
                new InventorySystem(this, true),
                new MovementSystem(this, true),
                new ConversationSystem(this, true)

        );
    }
}