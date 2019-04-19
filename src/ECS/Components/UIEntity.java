package ECS.Components;

import ECS.Component;
import Maths.Vector2;
import Scene.Scene;
import Utility.Pair;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import proyecto_videojuegos.MainThread;

/**
 * Component of the entity to be rendered as a  user interface
 * 
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
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
    
    //I'm sorry ECS :(
    public void UIRender(Graphics2D g, Scene s){
    }   
}
