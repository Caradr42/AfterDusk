package ECS.Systems;

import ECS.Components.Inventory;
import ECS.Components.Sprite;
import ECS.Components.UIInventory;
import ECS.SystemJob;
import Scene.Scene;
import java.awt.Rectangle;
import java.util.ArrayList;
import Assets.Assets;
import ECS.Components.Active;
import ECS.Components.MousePointer;
import ECS.Components.Pasive;
import ECS.Components.Player;
import ECS.Components.Transform;
import Maths.Vector3;
import java.awt.event.KeyEvent;

/**
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class UIInventorySystem extends SystemJob {

    ArrayList<Integer> mousePointers;

    Sprite inventorySprite;
    Transform inventoryTransform;
    Integer playerID;

    Transform parentTransform;

    UIInventory uiInventory; //temp inventory to hold the Inventory component of the inventories
    Inventory inventory;
    Sprite itemSprite; //the sprite of the items to render in the inventory

    MousePointer mousePointer;

    /**
     * Constructor
     *
     * @param scene
     * @param active
     */
    public UIInventorySystem(Scene scene, boolean active) {
        super(scene, active);

        this.inventorySprite = new Sprite();
        this.inventoryTransform = new Transform();
        this.parentTransform = new Transform();

        uiInventory = new UIInventory();
        inventory = new Inventory();
        itemSprite = new Sprite();
        mousePointer = new MousePointer();
    }

    @Override
    public void update() {
        for (Integer e : entities) {
            uiInventory = scene.entityManager.getEntityComponentInstance(e, uiInventory.getClass());

            inventorySprite = scene.entityManager.getEntityComponentInstance(e, inventorySprite.getClass());
            inventoryTransform = scene.entityManager.getEntityComponentInstance(e, inventoryTransform.getClass());
            parentTransform = scene.entityManager.getEntityComponentInstance(inventoryTransform.parent, parentTransform.getClass());

            int temp = uiInventory.firstInventory;
            inventory = scene.entityManager.getEntityComponentInstance(temp, inventory.getClass());
            for (int i = 0; temp != 0; ++i) {

                for (int j = 0; j < inventory.size; ++j) {
                    uiInventory.UISlots.get(i).get(j).setLocation((int) (/*parentTransform.position.x +*/inventoryTransform.position.x + (j * 17) + 1), (int) (/*parentTransform.position.y +*/inventoryTransform.position.y + (i * 17) + 1));
                }
                temp = inventory.nextInventory;
                if (temp != 0) {
                    inventory = scene.entityManager.getEntityComponentInstance(temp, inventory.getClass());
                }
            }

            if (uiInventory.name.equals("Actives")) {
                int Atemp = uiInventory.firstInventory;
                inventory = scene.entityManager.getEntityComponentInstance(Atemp, inventory.getClass());
                int number = 0;
                if (scene.display.keyManager.wasPressed[KeyEvent.VK_1]) {
                    number = 1;
                }
                if (scene.display.keyManager.wasPressed[KeyEvent.VK_2]) {
                    number = 2;
                }
                if (scene.display.keyManager.wasPressed[KeyEvent.VK_3]) {
                    number = 3;
                }
                if (scene.display.keyManager.wasPressed[KeyEvent.VK_4]) {
                    number = 4;
                }
                if (scene.display.keyManager.wasPressed[KeyEvent.VK_5]) {
                    number = 5;
                }
                if (scene.display.keyManager.wasPressed[KeyEvent.VK_6]) {
                    number = 6;
                }
                if (scene.display.keyManager.wasPressed[KeyEvent.VK_7]) {
                    number = 7;
                }
                if (scene.display.keyManager.wasPressed[KeyEvent.VK_8]) {
                    number = 8;
                }
                if (scene.display.keyManager.wasPressed[KeyEvent.VK_9]) {
                    number = 9;
                }

                int item = getItemFromInventory(e, 0, number);

                if (item != 0) {
                    // Active active = scene.entityManager.getEntityComponentInstance(item, i);
                    for (Class p : Active.activesSet) {

                        if (scene.entityManager.hasComponent(item, p)) {
                            Active active = (Active) scene.entityManager.getEntityComponentInstance(item, p);
                            active.isActive = true;
                            //pasive.isActive = false;
                        }

                    }
                }
            }

            //to place or remove items from the inventory
            if (inventorySprite.visible) {
                for (int i = 0; i < uiInventory.UISlots.size(); ++i) {
                    for (int j = 0; j < uiInventory.UISlots.get(i).size(); ++j) {
                        //if the mouse is inside the UIslot rectangle
                        if (uiInventory.UISlots.get(i).get(j).contains((int) mousePointer.position.x, (int) mousePointer.position.y)) {
                            if (mousePointer.mouseManager.wasLeftClicked()) {

                                //Play pick up sound
                                Assets.pickUp.play();

                                int mouseItemBuffer = mousePointer.heldItem;

                                int inventoryItemBuffer = getItemFromInventory(uiInventory.firstInventory, i, j);
                                if (inventoryItemBuffer != 0) {
                                    if (uiInventory.name.equals("passives")) {
                                        for (Class p : Pasive.pasivesSet) {

                                            if (scene.entityManager.hasComponent(inventoryItemBuffer, p)) {
                                                Pasive pasive = (Pasive) scene.entityManager.getEntityComponentInstance(inventoryItemBuffer, p);

                                                pasive.isActive = false;
                                            }
                                        }
                                    }
                                }

                                boolean wasAdded = setItemFromInventory(uiInventory.firstInventory, i, j, mouseItemBuffer);
                                if (wasAdded) {
                                    mousePointer.heldItem = inventoryItemBuffer;
                                }

                                if (mouseItemBuffer == 0 | wasAdded) {

                                    if (mouseItemBuffer != 0) {

                                        Transform itemTransform = scene.entityManager.getEntityComponentInstance(mouseItemBuffer, Transform.class);
                                        if (itemTransform != null) {
                                            itemTransform.parent = playerID;
                                        }
                                    }

                                    if (mousePointer.heldItem != 0) {
                                        Transform itemTransform = scene.entityManager.getEntityComponentInstance(mousePointer.heldItem, Transform.class);
                                        if (itemTransform != null) {
                                            itemTransform.parent = 0;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(uiInventory.getClass());
        mousePointers = scene.entityManager.getEntitiesWithComponents(mousePointer.getClass());
        mousePointer = scene.entityManager.getEntityComponentInstance(mousePointers.get(0), mousePointer.getClass());
        playerID = scene.entityManager.getEntitiesWithComponents(Player.class).get(0);

        //sets the slots  acording to the inventory
        for (Integer e : entities) {//iterate on all UIInventories
            uiInventory = scene.entityManager.getEntityComponentInstance(e, uiInventory.getClass());
            inventorySprite = scene.entityManager.getEntityComponentInstance(e, inventorySprite.getClass());
            inventoryTransform = scene.entityManager.getEntityComponentInstance(e, inventoryTransform.getClass());
            parentTransform = scene.entityManager.getEntityComponentInstance(inventoryTransform.parent, parentTransform.getClass());

            //update the sprite and transform references in the inventoryUI
            uiInventory._uiSprite = inventorySprite;
            uiInventory._uiTransform = inventoryTransform;

            int temp = uiInventory.firstInventory;
            inventory = scene.entityManager.getEntityComponentInstance(temp, inventory.getClass());

            for (int i = 0; temp != 0; ++i) {
                uiInventory.UISlots.add(new ArrayList<>());
                for (int j = 0; j < inventory.size; ++j) {
                    uiInventory.UISlots.get(i).add(new Rectangle((int) (parentTransform.position.x + inventoryTransform.position.x + (j * 17) + 1), (int) (parentTransform.position.y + inventoryTransform.position.y + (i * 17) + 1), 16, 16));
                }
                temp = inventory.nextInventory;
                if (temp != 0) {
                    inventory = scene.entityManager.getEntityComponentInstance(temp, inventory.getClass());
                }
            }
        }
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }

    /**
     * Return the item id from an inventory slot by providing a vertical
     * coordinate i and a horizontal j
     *
     * @param inv
     * @param i
     * @param j
     * @return
     */
    private Integer getItemFromInventory(Integer e, int i, int j) {
        Inventory invComp = new Inventory();
        int temp = uiInventory.firstInventory;
        invComp = scene.entityManager.getEntityComponentInstance(temp, invComp.getClass());
        //follow the inventories LinkedList up to the i list
        for (int inv = 0; temp != 0; ++inv) {

            if (inv == i) {
                if (invComp.slots.get(j) == null) {
                    return 0;
                }
                return invComp.slots.get(j); //returns the Item id at position j
            }
            temp = invComp.nextInventory;
            if (temp != 0) {
                invComp = scene.entityManager.getEntityComponentInstance(temp, invComp.getClass());
            }

        }
        return 0;
    }

    private boolean setItemFromInventory(Integer e, int i, int j, Integer item) { //cambiar a bool
        Inventory invComp = new Inventory();
        int temp = uiInventory.firstInventory;

        invComp = scene.entityManager.getEntityComponentInstance(temp, Inventory.class);

        if (item != 0) {
            if (uiInventory.name.equals("passives")) {
                boolean isPassive = false;

                //comprobacion
                for (Class p : Pasive.pasivesSet) {

                    if (scene.entityManager.hasComponent(item, p)) {

                        isPassive = true;
                        Pasive pasive = (Pasive) scene.entityManager.getEntityComponentInstance(item, p);
                        pasive.isActive = true;
                    }
                }

                if (!isPassive) {
                    return false;
                }

            }

            if (uiInventory.name.equals("Actives")) {
                boolean isActive = false;
                //comprobacion
                for (Class p : Active.activesSet) {
                    if (scene.entityManager.hasComponent(item, p)) {
                        isActive = true;
                    }
                }
                if (!isActive) {
                    return false;
                }

            }
        }
        //follow the inventories LinkedList up to the i list
        for (int inv = 0; temp != 0; ++inv) {
            if (inv == i) {
                invComp.slots.set(j, item); //returns the Item id at position j
                return true;
            }
            temp = invComp.nextInventory;
            if (temp != 0) {
                invComp = scene.entityManager.getEntityComponentInstance(temp, invComp.getClass());
            }
        }
        return false;
    }
}
