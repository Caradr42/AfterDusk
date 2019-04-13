package ECS.Components;

import ECS.Component;
import Utility.Pair;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import proyecto_videojuegos.MainThread;
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
    public boolean visible;
    public boolean frozen;
     
    public int width;
    public int height;
    
    public int animationLenght;
    public double speed;
    public double frameCounter = 0;
    
    public ArrayList<String> animationsNames;
    
    public ArrayList<Pair<BufferedImage[], Integer>> animations;
    public BufferedImage[] animation;
    public BufferedImage currentFrame;
    
    public int animationNo; // 0 is no animation, 1 is animation one, n is animation n
    
    /**
     * Sprite constructor
     * 
     * @param name
     * @param active
     * @param width
     * @param height
     * @param speed
     * @param animationsNames 
     */
    public Sprite(String name, boolean active, int width, int height, double speed, ArrayList<String> animationsNames) {
        this.name = name;
        this.visible = active;
        this.frozen = false;
        
        this.width = width;
        this.height = height;
        
        this.speed = speed / MainThread.fps;
        this.animationsNames = animationsNames;
        this.animations = new ArrayList<>();
        
    }

    public Sprite() {
    }
    
}
