package graphics;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import Maths.Vector2;

/**
 * 
 * 
 * @author Carlos Adrián Guerra Vázquez
 * @date 07/04/2019
 * @version 1.0
 */
public class Camera {
    public Vector2 ortogonalPosition;
    public Vector2 worldPosition;
    
    Graphics2D tempG;
    public AffineTransform UItransform = new AffineTransform();

    public int scale;
    
    private Display display;
    
    public Camera(Vector2 ortogonalPosition, int scale, Display display) {
        this.ortogonalPosition = ortogonalPosition;
        this.scale = scale;
        this.display = display;
        init();
    }
    
    public Camera(int x, int y, int scale, Display display) {
        this.ortogonalPosition = new Vector2(x, y);
        this.worldPosition = new Vector2( x / scale, y / scale);
        this.scale = scale;
        this.display = display;
        init();
    }
    
    private void init(){
        UItransform.setTransform(scale, 0, 0, scale, 1, 0);
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
        //previousTransform = g.getTransform();
        
        if(display.getKeyManager().keys[KeyEvent.VK_I]) {
            ortogonalPosition.set(ortogonalPosition.x,ortogonalPosition.y - 6);
        }
        if(display.getKeyManager().keys[KeyEvent.VK_K]) {
            ortogonalPosition.set(ortogonalPosition.x,ortogonalPosition.y + 6);
        }
        if(display.getKeyManager().keys[KeyEvent.VK_J]) {
            ortogonalPosition.set(ortogonalPosition.x - 6,ortogonalPosition.y);
        }
        if(display.getKeyManager().keys[KeyEvent.VK_L]) {
            ortogonalPosition.set(ortogonalPosition.x + 6,ortogonalPosition.y);
        }
        worldPosition.set(ortogonalPosition.div(scale));
        
        at.translate(-ortogonalPosition.x, -ortogonalPosition.y);
        at.scale(scale, scale);
        
        g.transform(at);
    }
    
    
    /**
     * 
     * @param g 
     */
    public void tickUI(Graphics2D g){
        g.setTransform(UItransform);  
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
    
    public Vector2 UIToWorldCoodinates(Vector2 UIcoordinates){
        //System.out.println("camera: " + worldPosition.x + " " + worldPosition.y );
        return worldPosition.add(UIcoordinates);
    }
    
    public Vector2 WorldToUICoodinates(Vector2 WorldCoordinates){
        return worldPosition.sub(WorldCoordinates);
    }
}
