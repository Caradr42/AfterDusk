package ECS.Components;

import ECS.Component;
import java.util.ArrayList;
import Maths.Vector3;
import java.awt.image.BufferedImage;

/**
 * Component of an entity considered as a tile of the background
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class Tile extends Component {

    public String name;
    //Integer sideSpriteID;

    public Sprite topSprite;
    public Sprite sideSprite;
    public boolean tileCollidable;
    public boolean topVisible;
    public boolean sideVisible;

    /**
     * Constructor
     *
     * @param name
     * @param tileCollidable
     * @param topSprite
     * @param sideSprite
     */
    public Tile(String name, boolean tileCollidable, Sprite topSprite, Sprite sideSprite) {
        this.name = name;
        this.tileCollidable = tileCollidable;
        this.topSprite = topSprite;
        this.sideSprite = sideSprite;
        topVisible = true;
        sideVisible = true;
    }

    /**
     * Constructor
     *
     * @param name
     * @param topSprite
     * @param sideSprite
     */
    public Tile(String name, Sprite topSprite, Sprite sideSprite) {
        this.name = name;
        this.topSprite = topSprite;
        this.sideSprite = sideSprite;
        topVisible = true;
        sideVisible = true;
    }

    /**
     * Constructor
     */
    public Tile() {
        tileCollidable = false;
    }

    /**
     * isCollidable Return if is collidable
     *
     * @return tile collidable
     */
    public boolean isCollidable() {
        return tileCollidable;
    }

    /**
     * Set collidable
     *
     */
    public void setCollidable() {
        tileCollidable = !tileCollidable;
    }

}
