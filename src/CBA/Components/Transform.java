package CBA.Components;

import CBA.Behaviour;
import Maths.Vector2;

/**
 * Example CBA component
 * Contains a Vector2 and some update to be executed
 * 
 * @author Carlos Adrián Guerra Vázquez
 * @date 09/02/2019
 * @version 1.0
 */
public class Transform implements Behaviour{
    
    Vector2 position;

    public Transform(Vector2 position) {
        this.position = position;
    }

    public Transform() {
        position = new Vector2(0,0);
    }
    
    @Override
    public void init() {
        
    }
    
    @Override
    public void update() {
        System.out.println("x = " + position.x + "\ty = " + position.y);
    }

    @Override
    public void render() {
        
    }

    @Override
    public void dispose() {
        position = null;
    }
    
}
