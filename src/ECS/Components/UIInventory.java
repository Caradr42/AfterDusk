package ECS.Components;

import ECS.Component;
import ECS.interfaces.UIChild;
import Scene.Scene;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author carlo
 */
public class UIInventory extends Component implements UIChild{
    
    public String name;
    
    public Sprite _uiSprite; //the sprite reference is updated in the sistem
    public Transform _uiTransform; //the transform reference is also updated in the sistem
    
    public Integer firstInventory; //the inventory linkedList
    
    //public HashMap<Integer, Vector2> UIItemsPositions;
    public ArrayList<ArrayList<Rectangle>> UISlots; //each hitbox for each slot in the inventory
        
    public Inventory inventory; //temp inventory to hold the Inventory component of the inventories
    
    public Sprite itemSprite;

    public UIInventory() {
    }
    
    /**
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
    public void UIRender(Graphics2D g, Scene s){
        //super.UIRender(g, s);
        
        //renders itself
        if(_uiSprite.visible){
            for(int i = 0; i < UISlots.size(); ++i){
                    g.drawImage(_uiSprite.currentFrame, (int)_uiTransform.position.x, (int)_uiTransform.position.y + (17 * i), _uiSprite.width, _uiSprite.height, null);
            }
        }
        
        //renders the items
        int temp = firstInventory;
        inventory = s.entityManager.getEntityComponentInstance(temp , inventory.getClass());
        
        for(int row = 0; temp != 0; ++row){            
            for(int sl = 0; sl < inventory.slots.size(); ++sl){
                if(inventory.slots.get(sl) != 0){
                    itemSprite = s.entityManager.getEntityComponentInstance(inventory.slots.get(sl), itemSprite.getClass());

                    if(inventory.slots.get(sl) != 0){
                        g.drawImage(itemSprite.currentFrame, UISlots.get(row).get(sl).x /*+ (int)position.x*/ , UISlots.get(row).get(sl).y /*+ (int)position.y*/ ,16,16,null);
                        //g.drawRect(UISlots.get(row).get(sl).x , UISlots.get(row).get(sl).y, UISlots.get(row).get(sl).width, UISlots.get(row).get(sl).height);
                        //sg.drawRect(x, y, 16, 16);
                    }
                }
                //g.drawRect(UISlots.get(row).get(sl).x , UISlots.get(row).get(sl).y, UISlots.get(row).get(sl).width, UISlots.get(row).get(sl).height);
            }
            temp = inventory.nextInventory;
            if(temp != 0)
                inventory = s.entityManager.getEntityComponentInstance(temp, inventory.getClass());
        }
    }
}
