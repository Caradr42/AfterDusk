package ECS.Components;

import ECS.Component;
import Maths.Vector2;
import Utility.Pair;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import proyecto_videojuegos.MainThread;

/**
 *
 * @author carlo
 */
public class UIEntity extends Component{
    //Transform Data
    public Vector2 position; //position on screen (pixels)
    
    //Sprite data
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
    
    public UIEntity(String name, boolean active, int width, int height, int x, int y, double speed, ArrayList<String> animationsNames) {
        this.position = new Vector2(x,y);
        this.name = name;
        this.visible = active;
        this.frozen = false;
        
        this.width = width;
        this.height = height;
        
        this.speed = speed / MainThread.fps;
        this.animationsNames = animationsNames;
        this.animations = new ArrayList<>();
    }

    public UIEntity() {
    }
}
