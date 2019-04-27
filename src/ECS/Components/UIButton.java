/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Components;

import ECS.Component;
import ECS.interfaces.UIChild;
import Maths.Vector2;
import Scene.Scene;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author carlo
 */
public class UIButton extends  Component implements UIChild{
    
    public Sprite _buttonSprite;
    public Transform _buttonTransform;
    public boolean buttonVisible;
    public String name;
    
    /**
     * the button constructor
     * 
     * @param name name of the button
     */
    public UIButton(String name) {
        this.name = name;
        buttonVisible = false;
    }

    public UIButton() {
    }
    
    @Override
    public void UIRender(Graphics2D g, Scene s){
        //System.out.println("button: " + _buttonSprite);
        //System.out.println(_buttonSprite.visible);
        if(_buttonSprite != null && _buttonTransform != null && buttonVisible){
            g.drawImage(_buttonSprite.currentFrame, (int)(_buttonTransform.position.x) , (int)(_buttonTransform.position.y), _buttonSprite.width, _buttonSprite.height, null);
        }
    }
}
