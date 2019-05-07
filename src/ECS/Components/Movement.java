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
 * @author pepe_
 */
public class Movement extends Component {
    
    public Vector3 velocity;
    public Vector3 resistance;
    
    public Movement(Vector3 velocity) {
        this.velocity = velocity;
        resistance= new Vector3(velocity.x*0.8,velocity.y*0.8,0);
    }

    
    public Movement() {
        velocity=new Vector3(2, 2, 0);
        resistance= new Vector3(1.6,1.6,0);
    }
    
    
    
}