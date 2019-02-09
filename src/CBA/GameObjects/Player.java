/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CBA.GameObjects;

import CBA.Components.Transform;
import CBA.GameObject;

/**
 *
 * @author carlo
 */
public class Player extends GameObject{
   
   
    Transform tr;
    
    public Player() {
        tr = new Transform();
        addComponent(tr);
    }
        
}
