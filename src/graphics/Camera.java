package graphics;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import maths.Vector2;

/**
 * 
 * 
 * @author Carlos Adrián Guerra Vázquez
 * @date 07/04/2019
 * @version 1.0
 */
public class Camera {
    private Vector2 ortogonalPosition;
    private int scale;
    
    private Display display;
    
    public Camera(Vector2 ortogonalPosition, int scale, Display display) {
        this.ortogonalPosition = ortogonalPosition;
        this.scale = scale;
        this.display = display;
        //init();
    }
    
    public Camera(int x, int y, int scale, Display display) {
        this.ortogonalPosition = new Vector2(x, y);
        this.scale = scale;
        this.display = display;
        //init();
    }
    
    /*private void init(){
        at = new AffineTransform();
        at.translate(-ortogonalPosition.getX(), -ortogonalPosition.getY());
        at.scale(scale, scale);
    }*/
    
    public void tick(Graphics2D g){  
        //at.translate(-ortogonalPosition.getX(), -ortogonalPosition.getY());
        //at.scale(scale, scale);
        AffineTransform at = new AffineTransform();
        
        if(display.getKeyManager().keys[KeyEvent.VK_I]) {
            ortogonalPosition.set(ortogonalPosition.getX(),ortogonalPosition.getY() - 1);
        }
        if(display.getKeyManager().keys[KeyEvent.VK_K]) {
            ortogonalPosition.set(ortogonalPosition.getX(),ortogonalPosition.getY() + 1);
        }
        if(display.getKeyManager().keys[KeyEvent.VK_J]) {
            ortogonalPosition.set(ortogonalPosition.getX() - 1,ortogonalPosition.getY());
        }
        if(display.getKeyManager().keys[KeyEvent.VK_L]) {
            ortogonalPosition.set(ortogonalPosition.getX() + 1,ortogonalPosition.getY());
        }
        
        at.translate(-ortogonalPosition.getX(), -ortogonalPosition.getY());
        at.scale(scale, scale);
        
        g.transform(at);
    }
    
    public void setPosition(Vector2 position){
        ortogonalPosition = position;
    }
    
    public void setPosition(double x, double y){
        ortogonalPosition.set(x, y);
    }

    public Vector2 getPosition() {
        return ortogonalPosition;
    }
    
    
}
