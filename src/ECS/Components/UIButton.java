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
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import Assets.Assets;

/**
 *
 * @author carlo
 */
public class UIButton extends Component implements UIChild{
    
    public Sprite _buttonSprite;
    public Transform _buttonTransform;
    public boolean buttonVisible;
    public boolean buttonPressed;
    public String name;
    public int parentState;
    public String text;
    
    /**
     * the button constructor
     * 
     * @param name name of the button
     * @param tetx
     * @param state
     */
    public UIButton(String name, String tetx, int state) {
        this.name = name;
        buttonVisible = false;
        buttonPressed = false;
        this.parentState = state;
        this.text = tetx;
    }

    public UIButton() {
    }
    
    @Override
    public void UIRender(Graphics2D g, Scene s){
        //System.out.println("button: " + _buttonSprite);
        //System.out.println(_buttonSprite.visible);
        if(_buttonSprite != null && _buttonTransform != null && _buttonSprite.visible){
            g.drawImage(_buttonSprite.currentFrame, (int)(_buttonTransform.position.x) , (int)(_buttonTransform.position.y), _buttonSprite.width, _buttonSprite.height, null);
            
            g.drawString(text,(int)(_buttonTransform.position.x) + 2 , (int)(_buttonTransform.position.y + g.getFont().getSize()) );
            if(buttonVisible){
                g.drawImage(Assets.animations.get("selected_transparency").first[0], (int)(_buttonTransform.position.x) + 1 , (int)(_buttonTransform.position.y) + 1, _buttonSprite.width - 2, _buttonSprite.height - 2, null);
            }
        }
    }
}
