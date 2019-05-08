package ECS.Components;

import ECS.Component;
import ECS.interfaces.UIChild;
import Scene.Scene;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Component that shows the userInterface Inventory
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class UIInventory extends Component implements UIChild {

    public String name;

    public Sprite _uiSprite; //the sprite reference is updated in the sistem
    public Transform _uiTransform; //the transform reference is also updated in the sistem

    public Integer firstInventory; //the inventory linkedList

    public ArrayList<ArrayList<Rectangle>> UISlots; //each hitbox for each slot in the inventory

    public Inventory inventory; //temp inventory to hold the Inventory component of the inventories

    public Sprite itemSprite;

    public UIInventory() {
    }

    /**
     * Constructor
     *
     * @param name
     * @param firstInventory
     */
    public UIInventory(String name, Integer firstInventory) {
        this.name = name;
        this.firstInventory = firstInventory;
        UISlots = new ArrayList<>(4);
        inventory = new Inventory();
        itemSprite = new Sprite();
    }

    @Override
    public void UIRender(Graphics2D g, Scene s) {

        //renders itself
        if (_uiSprite.visible) {
            for (int i = 0; i < UISlots.size(); ++i) {
                g.drawImage(_uiSprite.currentFrame, (int) _uiTransform.position.x, (int) _uiTransform.position.y + (17 * i), _uiSprite.width, _uiSprite.height, null);
            }
        }

        //renders the items
        int temp = firstInventory;
        inventory = s.entityManager.getEntityComponentInstance(temp, inventory.getClass());

        for (int row = 0; temp != 0; ++row) {
            for (int sl = 0; sl < inventory.slots.size(); ++sl) {
                if (inventory.slots.get(sl) != 0) {
                    itemSprite = s.entityManager.getEntityComponentInstance(inventory.slots.get(sl), itemSprite.getClass());

                    if (inventory.slots.get(sl) != 0) {
                        g.drawImage(itemSprite.currentFrame, UISlots.get(row).get(sl).x /*+ (int)position.x*/, UISlots.get(row).get(sl).y /*+ (int)position.y*/, 16, 16, null);
                    }
                }
            }
            temp = inventory.nextInventory;
            if (temp != 0) {
                inventory = s.entityManager.getEntityComponentInstance(temp, inventory.getClass());
            }
        }
    }
}
