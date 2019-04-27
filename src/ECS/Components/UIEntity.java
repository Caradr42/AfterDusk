package ECS.Components;

import ECS.Component;
import ECS.Entity;
import Maths.Vector2;
import Scene.Scene;
import Utility.Pair;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
    
    public Integer parent;
    public ArrayList<Integer> childs;
    
    //Transform Data
    public Vector2 position; //position on screen (pixels)
    
    //Sprite data
    public String name;
    public boolean visible;
     
    public int width;
    public int height;
    public Rectangle UIcollider;
    
    public int animationLenght;
    public double speed;
    public double frameCounter = 0;
    
    public ArrayList<String> animationsNames;
    
    public ArrayList<Pair<BufferedImage[], Integer>> animations;
    public BufferedImage[] animation;
    public BufferedImage currentFrame;
    //UI data
    //public ArrayList<Integer> subInterfaces; //the smaller UIEntities or UIInventories inside this UIEntity
    public ArrayList<UIEntity> subInterfacesComponents; //polimorfic list of components instances
    public boolean mainUI;
    
    public UIEntity(String name, boolean visible,boolean mainUI, int width, int height, int x, int y, double speed, ArrayList<String> animationsNames, ArrayList<Integer> subInterfaces) {
        this.position = new Vector2(x,y);
        this.name = name;
        this.visible = visible;
        this.mainUI = mainUI;
        
        this.width = width;
        this.height = height;
        this.UIcollider = new Rectangle((int)position.x, (int)position.y, width,height);
        this.speed = speed / MainThread.fps;
        
        this.animationsNames = animationsNames;
        this.animations = new ArrayList<>();
        
        this.childs = subInterfaces;
        subInterfacesComponents = new ArrayList<>();
    }

    public UIEntity() {
    }
    
    //I'm sorry ECS :(
    public void UIRender(Graphics2D g, Scene s, Vector2 fatherPosition ){
        
        for(UIEntity sub: subInterfacesComponents){
            if(sub.visible){
                sub.UIRender(g, s, fatherPosition);
            }
        }
    }   
}
