/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Components;

import ECS.Entity;
import Maths.Vector2;
import Scene.Scene;
import Utility.Pair;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author carlo
 */
public class UIInventory extends UIEntity{
    ArrayList<Entity> inventories;
    HashMap<Integer, Vector2> UIItesmPositions;
    ArrayList<ArrayList<Rectangle>> UISlotPosition;
    
    Inventory inventory;
    Sprite itemSprite;

    public UIInventory() {
    }

    public UIInventory(String name, boolean active, int width, int height, int x, int y, double speed, ArrayList<String> animationsNames, ArrayList<Entity> inventories) {
        super(name, active, width, height, x, y, speed, animationsNames);
        this.inventories = inventories;
        UIItesmPositions = new HashMap<>();
        UISlotPosition = new ArrayList<>(4);
        inventory = new Inventory();
        itemSprite = new Sprite();
        
        for(int i = 0; i < 4; ++i){
            UISlotPosition.add(new ArrayList<>());
        }
        
        for(int i = 0; i < 6; ++i){
            for(int j = 0; j < 4; ++j){
                 UISlotPosition.get(j).add(new Rectangle(i* 17 + (int)position.x + 18, j *17 + (int)position.y + 42,16,16));
            }
        }
        
        /*for(int i = (int)position.x + 18; i < (int)position.x + 18 + 17 * 6 ;i += 17){
            for(int j = (int)position.y + 42; j < (int)position.y + 42 + 17 * 3 ; j += 17){
                UISlotPosition.get(j).add(new Rectangle(i, j,16,16));
            }
        }*/
    }   
    
    @Override
    public void UIRender(Graphics2D g, Scene s){
        for(Entity e: inventories){
            inventory = s.entityManager.getEntityComponentInstance(e.getID(), inventory.getClass());
            for(int i = 0; i < inventory.slots.size(); ++i){
                itemSprite = s.entityManager.getEntityComponentInstance(inventory.slots.get(i), itemSprite.getClass());
                g.drawImage(itemSprite.currentFrame, UISlotPosition.get(0).get(i).x ,UISlotPosition.get(0).get(i).y ,16,16,null);
            }
        }
    }
}
