/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Components;

import ECS.Component;
import Maths.Vector3;

/**
 *
 * @author pablo
 */
public class Collidable extends Component{
    
     public Vector3 hitbox;

    public Collidable() {
    }

    public Collidable(Vector3 hitbox) {
        this.hitbox = hitbox;
    }
     
     
}
