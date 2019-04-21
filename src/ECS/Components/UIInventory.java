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
     * @param visible
     * @param width
     * @param height
     * @param x
     * @param y
     * @param animationsNames
     * @param firstInventory 
     */
    public UIInventory(String name, boolean visible, int width, int height, int x, int y, ArrayList<String> animationsNames, Integer firstInventory) {
        super(name, false, false ,width, height, x, y, 0, animationsNames, null);
        this.firstInventory = firstInventory;
        //UIItemsPositions = new HashMap<>();
        UISlots = new ArrayList<>(4);
        
        inventory = new Inventory();
        itemSprite = new Sprite();     
    }
    
    @Override
    public void UIRender(Graphics2D g, Scene s){
        //renders itself
        if(visible){
            for(int i = 0; i < UISlots.size(); ++i){
                /*if(UISlots.get(i).size() > 3){
                    g.drawImage(currentFrame, (int)position.x, (int)position.y + (17 * i), width, height, null);
                    g.drawImage(currentFrame, (int)position.x + 51, (int)position.y + (17 * i), width, height, null);
                }else{*/
                    g.drawImage(currentFrame, (int)position.x, (int)position.y + (17 * i), width, height, null);
                //}
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
            }
            temp = inventory.nextInventory;
            if(temp != 0)
                inventory = s.entityManager.getEntityComponentInstance(temp, inventory.getClass());
        }
    }
}
