package CBA.GameObjects;

import CBA.Components.Transform;
import CBA.GameObject;
import Maths.Vector2;

/**
 * Example CBA GameObject
 * This GO contains a Transform component and as such it adds it at 
 * initialization. When the Player methods are called the corresponding 
 * components methods will be executed.
 * 
 * @author Carlos Adrián Guerra Vázquez
 * @date 09/02/2019
 * @version 1.0
 */
public class Player extends GameObject{
   
   
    Transform tr;
    
    public Player() {
        tr = new Transform(new Vector2(21,42));
        addComponent(tr);
    }
        
}
