package graphics;


import ECS.Components.Player;
import ECS.Components.Transform;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import Maths.Vector2;
import java.awt.Dimension;
import java.awt.Toolkit;


/**
 * Graphics class for handling the camera
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/02/2019
 * @version 1.1
 */
public class Camera {
    public Vector2 ortogonalPosition;

    public Vector2 worldPosition;

    Player player;
    Transform transform;

    Graphics2D tempG;
    public AffineTransform UItransform = new AffineTransform();
    public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public int scale;
    private Display display;
    
    /**
     * Constructor
     * @param ortogonalPosition position of the camera
     * @param scale of the world
     * @param display 
     */
    public Camera(Vector2 ortogonalPosition, int scale, Display display) {
        this.ortogonalPosition = ortogonalPosition;
        this.scale = scale;
        this.display = display;
        init();
    }
    /**
     * Constructor
     * @param x
     * @param y
     * @param scale
     * @param display 
     */
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
  
    
    public void tick(Graphics2D g){  
        
        AffineTransform at = new AffineTransform();

        //translate the position to the world position
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
        worldPosition.set(ortogonalPosition.div(scale));
    }
    
    /**
     * setPosition
     * @param position 
     */
    public void setPosition(Vector2 position){
        ortogonalPosition = position;
    }
    
    /**
     * setPosition
     * @param x
     * @param y 
     */
    public void setPosition(double x, double y){
        ortogonalPosition.set(x, y);
    }

    /**
     * getPosition
     * @return 
     */
    public Vector2 getPosition() {
        return ortogonalPosition;
    }
    
    /**
     * UIToWorldCoodinates
     * @param UIcoordinates
     * @return 
     */
    public Vector2 UIToWorldCoodinates(Vector2 UIcoordinates){
        return worldPosition.add(UIcoordinates);
    }
    
    /**
     * WorldToUICoodinates
     * @param WorldCoordinates
     * @return 
     */
    public Vector2 WorldToUICoodinates(Vector2 WorldCoordinates){

        return WorldCoordinates.sub(worldPosition);
    }
}
