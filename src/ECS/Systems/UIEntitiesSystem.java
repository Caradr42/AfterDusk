package ECS.Systems;

import Assets.Assets;
import ECS.Component;
import ECS.Components.Item;
import ECS.Components.MousePointer;
import ECS.Components.Playable;
import ECS.Components.Player;
import ECS.Components.Sprite;
import ECS.Components.Transform;
import ECS.Components.UIButton;
import ECS.Components.UIEntity;
import ECS.Components.UIInventory;
import ECS.Components.UIText;
import ECS.SystemJob;
import ECS.interfaces.UIChild;
import Maths.Vector2;
import Maths.Vector3;
import Scene.Scene;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * System that executes User interface behabiour
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class UIEntitiesSystem extends SystemJob {

    UIEntity uiEntity;
    Transform uiTransform;
    Sprite uiSprite;

    Sprite childSprite;
    Transform childTransform;
    UIEntity childUIEntity;

    UIEntity parentUIEntity;

    //player with an inventory to show
    Player player;

    //UIEntity subclasses //add as needed
    UIInventory uiInventory;
    UIButton uiButton;
    UIText uIText;

    //Pointer info
    ArrayList<Integer> mousePointers;
    MousePointer mousePointer;
    boolean pointerOutsideUI = true;

    //held item data
    Transform itemTransform; //to update the item position
    Item item; //for the is in inventory boolean
    //the _visible boolean in sprite is automatically updated 

    //CONSTS
    final Vector3 leftLR = new Vector3(0, 10, 0);
    final Vector3 rightLR = new Vector3(17, 10, 0);

    /**
     * Constructor
     *
     * @param scene
     * @param active
     */
    public UIEntitiesSystem(Scene scene, boolean active) {
        super(scene, active);

        uiEntity = new UIEntity();
        uiSprite = new Sprite();
        uiTransform = new Transform();

        childSprite = new Sprite();
        childTransform = new Transform();
        childUIEntity = new UIEntity();

        parentUIEntity = new UIEntity();

        uiInventory = new UIInventory();
        uiButton = new UIButton();
        uIText = new UIText();

        mousePointers = new ArrayList<>();
        mousePointer = new MousePointer();

        player = new Player();

        itemTransform = new Transform();
        item = new Item();
    }

    @Override
    public void update() {
        pointerOutsideUI = true;

        for (Integer e : entities) {

            uiEntity = scene.entityManager.getEntityComponentInstance(e, uiEntity.getClass());
            uiSprite = scene.entityManager.getEntityComponentInstance(e, uiSprite.getClass());
            uiTransform = scene.entityManager.getEntityComponentInstance(e, uiTransform.getClass());

            //update UI collider Position
            uiEntity.UIcollider.setLocation((int) uiTransform.position.x, (int) uiTransform.position.y);

            //updte if the pointer is outside of any UIEntity
            if (uiEntity.UIcollider.contains((int) mousePointer.position.x, (int) mousePointer.position.y) && uiSprite.visible) {
                pointerOutsideUI = false;
            }

            //Update the each of the childs.
            for (Integer sub : uiEntity.childs) {
                childSprite = scene.entityManager.getEntityComponentInstance(sub, childSprite.getClass());
                childUIEntity = scene.entityManager.getEntityComponentInstance(sub, childUIEntity.getClass());

                //if the child uses the parent window variable to deterine its visibility
                if (childUIEntity.usesParentWindow) {
                    if (childUIEntity.parent != null) {
                        if (uiEntity.window != childUIEntity.expectedParentWindow) {

                            childSprite.visible = false;
                        } else {
                            childSprite.visible = true;
                        }
                    }
                } else {
                    childSprite.visible = uiSprite.visible;
                }

            }

            //INPUT 
            //Cannot open other UI while in init game screen
            if (GameManagerSystem.gameRunning) {
                //INPUT             
                if (uiEntity.name.equals("menu")) {
                    if (scene.display.keyManager.wasPressed[KeyEvent.VK_ESCAPE]) {
                        Assets.menu.play();
                        if (uiSprite.visible) {
                            uiSprite.visible = false;
                        } else {
                            uiSprite.visible = true;
                        }
                    }
                }
                if (uiEntity.name.equals("actives_bar") || uiEntity.name.equals("RL_bar") || uiEntity.name.equals("HP_bar") || uiEntity.name.equals("energy_bar")) {
                    if (scene.display.keyManager.wasPressed[KeyEvent.VK_TAB]) {
                        Assets.menu.play();
                        if (uiSprite.visible) {
                            uiSprite.visible = false;
                        } else {
                            uiSprite.visible = true;
                        }
                    }
                }

                if (uiEntity.name.equals("map_button") || uiEntity.name.equals("quests_button") || uiEntity.name.equals("options_button") || uiEntity.name.equals("game_button")) {
                    if (uiEntity._parentUI.window == uiEntity.expectedParentWindow) {

                    } else {

                    }
                }

                //update the player hp bar according to the  players hp
                if (uiEntity.name.equals("hp")) {
                    Playable playerPlayable = scene.entityManager.getEntityComponentInstance(scene.entityManager.getEntitiesWithComponents(Player.class).get(0), Playable.class);
                    uiSprite.width = (int) (playerPlayable.hp * (60.0 / playerPlayable.maxHp));

                }

                if (uiEntity.name.equals("energy")) {
                    Playable playerPlayable = scene.entityManager.getEntityComponentInstance(scene.entityManager.getEntitiesWithComponents(Player.class).get(0), Playable.class);
                    uiSprite.width = (int) (playerPlayable.energy * (60.0 / playerPlayable.maxEnergy));

                }

                if (uiEntity.name.equals("player_position")) {

                }

                if (uiEntity.name.equals("Player_Inventory")) {

                    if (scene.display.keyManager.wasPressed[KeyEvent.VK_X] || scene.display.keyManager.wasPressed[KeyEvent.VK_I] || scene.display.keyManager.wasPressed[KeyEvent.VK_Q]) {
                        Assets.menu.play();
                        if (uiSprite.visible) {
                            uiSprite.visible = false;
                        } else {
                            uiSprite.visible = true;
                        }
                    }
                }

                if (uiEntity.name.equals("item_selector")) {
                    if (player.rightOrLeft) {
                        uiTransform.relativePosition = rightLR;
                    } else {
                        uiTransform.relativePosition = leftLR;
                    }
                }

                if (uiEntity.name.equals("dialogBox")) {
                    if (ConversationSystem.visibleDialogBox) {
                        uiEntity._uiSprite.visible = true;
                    } else {
                        uiEntity._uiSprite.visible = false;
                    }
                }

                if (uiEntity.name.equals("AfterDusk") && GameManagerSystem.gameRunning) {
                    uiEntity._uiSprite.visible = false;
                }

            }

            uiSprite.animation = uiSprite.animations.get(uiEntity.window).first;
        }

        //drop item if outside o UI
        if (pointerOutsideUI) {
            if (mousePointer.mouseManager.wasLeftClicked() && mousePointer.heldItem != 0) {
                int tempItem = mousePointer.heldItem;
                mousePointer.heldItem = 0;
                itemTransform = scene.entityManager.getEntityComponentInstance(tempItem, itemTransform.getClass());
                item = scene.entityManager.getEntityComponentInstance(tempItem, item.getClass());
                double originalItemZ = itemTransform.position.z;
                itemTransform.position.set(scene.c.UIToWorldCoodinates(mousePointer.position.add(new Vector2(-8, -8))));
                itemTransform.position.y += originalItemZ;

                //Play drop sound
                Assets.pickUp.play();

                item.isInInventory = false;

            }
        }
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(uiEntity.getClass());

        mousePointers = scene.entityManager.getEntitiesWithComponents(mousePointer.getClass());
        mousePointer = scene.entityManager.getEntityComponentInstance(mousePointers.get(0), mousePointer.getClass());

        player = scene.entityManager.getEntityComponentInstance(scene.entityManager.getEntitiesWithComponents(Player.class).get(0), Player.class);
        //for each UIentity, it initializes it
        for (Integer e : entities) {
            uiEntity = scene.entityManager.getEntityComponentInstance(e, uiEntity.getClass());
            uiSprite = scene.entityManager.getEntityComponentInstance(e, uiSprite.getClass());
            uiTransform = scene.entityManager.getEntityComponentInstance(e, uiTransform.getClass());

            //update the sprite and transform references in the inventoryUI
            uiEntity._uiSprite = uiSprite;
            uiEntity._uiTransform = uiTransform;

            //Adds the UI entities childs as childs of the Transform
            uiTransform.childs.addAll(uiEntity.childs);

            //adds all components instances for all types of uiEntities to the childs list
            ArrayList<UIChild> instances = new ArrayList<>(); //temp set

            for (Integer sub : uiEntity.childs) {
                //Adds this entity as the parent for each of its childs (both in the transform component and the UIEntity component)
                childTransform = scene.entityManager.getEntityComponentInstance(sub, Transform.class);
                childTransform.parent = e;
                childUIEntity = scene.entityManager.getEntityComponentInstance(sub, UIEntity.class);
                childUIEntity.parent = e;

                ////////expand here for all UIEntity subclass... -->
                UIChild tempUIChild;

                UIInventory tempInv = scene.entityManager.getEntityComponentInstance(sub, UIInventory.class);
                UIButton tempButton = scene.entityManager.getEntityComponentInstance(sub, UIButton.class);
                UIText tempText = scene.entityManager.getEntityComponentInstance(sub, UIText.class);
                UIEntity tempUI = scene.entityManager.getEntityComponentInstance(sub, UIEntity.class);

                tempUIChild = (tempInv != null ? tempInv : (tempButton != null ? tempButton : (tempText != null ? tempText : (tempUI))));

                instances.add(tempUIChild);
                ///////expand here for all UIEntity subclass... <--
            }
            instances.remove(null);

            uiEntity.UIChildsInterfaces.addAll(instances);
            instances.clear();

            uiEntity.UIcollider = new Rectangle((int) uiTransform.position.x, (int) uiTransform.position.y, uiSprite.width, uiSprite.height);
        }

        for (Integer e : entities) {
            uiEntity = scene.entityManager.getEntityComponentInstance(e, uiEntity.getClass());
            uiSprite = scene.entityManager.getEntityComponentInstance(e, uiSprite.getClass());
            uiTransform = scene.entityManager.getEntityComponentInstance(e, uiTransform.getClass());

            if (uiEntity.parent != 0) {
                parentUIEntity = scene.entityManager.getEntityComponentInstance(uiEntity.parent, UIEntity.class);
                uiEntity._parentUI = parentUIEntity;
            } else {
                uiEntity._parentUI = null;
            }
        }
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }

}
