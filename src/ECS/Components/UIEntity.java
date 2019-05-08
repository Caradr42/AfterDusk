package ECS.Components;

import ECS.Component;
import ECS.interfaces.UIChild;
import Scene.Scene;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Component of the entity to be rendered as a user interface
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class UIEntity extends Component implements UIChild {

    public int window = 0;
    public int expectedParentWindow = 0;
    public boolean usesParentWindow = false;

    public Integer parent = 0;
    public ArrayList<Integer> childs;

    public String name;

    //UI data
    public ArrayList<UIChild> UIChildsInterfaces; //polimorfic list of components

    public boolean mainUI;
    public boolean worldUI = false;
    public boolean standAlone = false;
    public Rectangle UIcollider;

    public UIEntity _parentUI;
    public Sprite _uiSprite; //the sprite reference is updated in the sistem
    public Transform _uiTransform; //the transform reference is also updated in the sistem

    /*public BufferedImage _currentSpriteReference;
    public int _width;
    public int _height;
    public Vector3 _currentPositionReference;*/
    /**
     * UI entity constructor with expected parent window.
     *
     * @param name the name used for the UI behabiour
     * @param mainUI if the UI is a root UI, (no father)
     * @param expectedParentWindow
     * @param childs the list of child UI that this father manages
     */
    public UIEntity(String name, boolean mainUI, int expectedParentWindow, ArrayList<Integer> childs) {
        this(name, mainUI, childs);

        this.expectedParentWindow = expectedParentWindow;
        this.usesParentWindow = true;
        //System.out.println("USESPARENTWINDOW: " + name);

    }

    /**
     * UI entity constructor
     *
     * @param name name
     * @param mainUI if main UI
     * @param childs the childs of this UI entity
     */
    public UIEntity(String name, boolean mainUI, ArrayList<Integer> childs) {
        this.name = name;
        this.mainUI = mainUI;

        if (childs != null) {
            this.childs = childs;
        } else {
            this.childs = new ArrayList<>();
        }
        UIChildsInterfaces = new ArrayList<>();
        //System.out.println("NOTUSEWINDOW: " + name);
        //_visible = false; //this is updated in the system 
    }

    /**
     * Constructor
     *
     * @param name
     * @param mainUI
     * @param worldUI
     * @param childs
     */
    public UIEntity(String name, boolean mainUI, boolean worldUI, ArrayList<Integer> childs) {
        this(name, mainUI, childs);
        this.worldUI = worldUI;
    }

    /**
     * Constructor
     *
     * @param name
     * @param mainUI
     * @param childs
     * @param standAlone
     */
    public UIEntity(String name, boolean mainUI, ArrayList<Integer> childs, boolean standAlone) {
        this(name, mainUI, childs);
        this.standAlone = standAlone;
    }

    /**
     * Constructor
     *
     * @param name
     * @param mainUI
     * @param expectedParentWindow
     * @param childs
     * @param standAlone
     */
    public UIEntity(String name, boolean mainUI, int expectedParentWindow, ArrayList<Integer> childs, boolean standAlone) {
        this(name, mainUI, expectedParentWindow, childs);
        this.standAlone = standAlone;
    }

    /**
     * Constructor
     */
    public UIEntity() {
    }

    //I'm sorry ECS :(
    @Override
    public void UIRender(Graphics2D g, Scene s) {

        if (_uiSprite != null && _uiTransform != null && mainUI && _uiSprite.visible) {
            if (parent != 0 && standAlone) {
                if (_parentUI != null && _parentUI._uiSprite.visible) {
                    g.drawImage(_uiSprite.currentFrame, (int) _uiTransform.position.x, (int) _uiTransform.position.y, _uiSprite.width, _uiSprite.height, null);
                }
            } else {
                g.drawImage(_uiSprite.currentFrame, (int) _uiTransform.position.x, (int) _uiTransform.position.y, _uiSprite.width, _uiSprite.height, null);
            }

        }
        if (name.equals("RL_bar")) {

        }
        for (UIChild sub : UIChildsInterfaces) {
            if (name.equals("RL_bar")) {

            }
            sub.UIRender(g, s);
        }
        if (name.equals("RL_bar")) {

        }
    }
}
