package ECS.Components;

import ECS.Component;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import videoGame.ImageLoader;

/**
 * Example ECS component
 * 
 * a ECS component contains 
 * //data
 * 
 * //code to initialize the data
 * @author Carlos Adrián Guerra Vázquez
 * @date 09/02/2019
 * @version 1.0
 */
public class Sprite extends Component{
    //data
    public String name;
    public boolean active;
    
    public int mode; // 0 is no animation, 1 is animation one, n is animation n
    public BufferedImage currentFrame;
    public BufferedImage[] animation;
    public ArrayList<BufferedImage[]> animations;
    
    public ArrayList<String> animationsNames;
    
    public double fps;
    public double flameAccum = 0;
    
    public int width;
    public int height;
    
    
    /**
     * Sprite constructor
     * 
     * @param name
     * @param active
     * @param width
     * @param height
     * @param animationsPaths 
     */
    public Sprite(String name, boolean active, int width, int height, double fps, ArrayList<String> animationsNames) {
        this.name = name;
        this.active = active;
        this.fps = fps;
        //this.currentFrame ;
        this.animations = new ArrayList<>();
        this.animationsNames = animationsNames;
        
        this.width = width;
        this.height = height;
        active = true;
    }

    public Sprite() {
    }
    
}
