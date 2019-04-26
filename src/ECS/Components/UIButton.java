/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Components;

import Scene.Scene;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author carlo
 */
public class UIButton extends  UIEntity{
    
    
    /**
     * the button constructor
     * 
     * @param name name of the button
     * @param visible if the button selector is visible
     * @param width width
     * @param height height
     * @param x x pos
     * @param y y pos
     * @param animationsNames the button selector sprite
     */
    public UIButton(String name, boolean visible, int width, int height, int x, int y,  ArrayList<String> animationsNames) {
        super(name, visible, false, width, height, x, y, 0, animationsNames, null);
        currentFrame = Assets.Assets.animations.get("Button_48_selected").first[0];
    }

    public UIButton() {
    }
    
    @Override
    public void UIRender(Graphics2D g, Scene s){
        
        g.drawImage(currentFrame, (int)position.x, (int)position.y, width, height, null);
    }
}
