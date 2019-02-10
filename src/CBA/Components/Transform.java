package CBA.Components;

import CBA.Component;
import maths.Vector2;

/**
 * Example CBA component
 * Contains a Vector2 and some update to be executed
 * 
 * @author Carlos Adrián Guerra Vázquez
 * @date 09/02/2019
 * @version 1.0
 */
public class Transform implements Component{
    
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
        System.out.println("x = " + position.getX() + "\ty = " + position.getY());
    }

    @Override
    public void render() {
        
    }

    @Override
    public void dispose() {
        position = null;
    }
    
}
