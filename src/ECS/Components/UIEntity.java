package ECS.Components;

import ECS.Component;
import ECS.Entity;
import ECS.interfaces.UIChild;
import Maths.Vector2;
import Maths.Vector3;
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
public class UIEntity extends Component implements UIChild{
    
    public int windowState = 0;
    
    //public Sprite sprite;
    
    public Integer parent;
    public ArrayList<Integer> childs;
    
    public String name;
    
    //Transform Data
    //public Vector2 position; //position on screen (pixels)
    
    //Sprite data
    /*public String name;
    public boolean _visible;
     
    public int width;
    public int height;
    
    
    public int animationLenght;
    public double speed;
    public double frameCounter = 0;
    
    public ArrayList<String> animationsNames;
    
    public ArrayList<Pair<BufferedImage[], Integer>> animations;
    public BufferedImage[] animation;
    public BufferedImage currentFrame;*/
    
    //UI data
    public ArrayList<UIChild> UIChildsInterfaces; //polimorfic list of components
    public boolean mainUI;
    public Rectangle UIcollider;
    //public boolean _visible;
    
    public Sprite _uiSprite; //the sprite reference is updated in the sistem
    public Transform _uiTransform; //the transform reference is also updated in the sistem
    
    /*public BufferedImage _currentSpriteReference;
    public int _width;
    public int _height;
    public Vector3 _currentPositionReference;*/
    
    /**
     * UI entity constructor 
     * @param name the name used for the UI behabiour
     * @param mainUI if the UI is a root UI, (no father)
     * @param childs the list of child UI that this father manages
     */
    public UIEntity(String name,boolean mainUI, ArrayList<Integer> childs) {
        this.name = name;
        this.mainUI = mainUI;
        //sprite = new Sprite();
        //Move to system
        //this.UIcollider = new Rectangle((int)position.x, (int)position.y, width,height);
        if(childs != null){
            this.childs = childs;
        }else{
            this.childs = new ArrayList<>();
        }
        UIChildsInterfaces = new ArrayList<>();
        //_visible = false; //this is updated in the system
        
    }

    public UIEntity() {
    }
    
    //I'm sorry ECS :(
    @Override
    public void UIRender(Graphics2D g, Scene s ){
        //System.out.println("Rendering UI: " + name + " where: " + _uiSprite + " : " + _uiTransform);
        if(_uiSprite != null && _uiTransform != null && mainUI){
            //System.out.println("Rendering UI2w: " + name);
            g.drawImage(_uiSprite.currentFrame, (int)_uiTransform.position.x, (int)_uiTransform.position.y, _uiSprite.width, _uiSprite.height, null);
        }
        for(UIChild sub: UIChildsInterfaces){
            sub.UIRender(g, s);
        }
    }   
}
