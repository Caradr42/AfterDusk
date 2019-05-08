package ECS.Components;

import ECS.Component;
import IO.MouseManager;
import Maths.Vector2;
import Scene.Scene;
import com.sun.javafx.geom.Vec2d;
import java.awt.Graphics2D;

/**
 * Component that maages the mouse pointer
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class MousePointer extends Component{
    public Integer heldItem;
    public Vector2 position;
    public Vector2 relativeItemPosition;
    private Vector2 itemPosition;
    public MouseManager mouseManager;
    
    Sprite itemSprite;
/**
 * Constructor
 */
    public MousePointer() {
        this.heldItem = 0;
        this.position = new Vector2();
        this.relativeItemPosition = new Vector2(-8,-8);
        this.itemPosition = new Vector2();

        itemSprite = new Sprite();
    }
    
    /**
     * Render the User Interface
     * @param g
     * @param s 
     */
    public void UIRender(Graphics2D g, Scene s){
        itemPosition.set(position.add(relativeItemPosition));
        if(heldItem != 0){
            itemSprite = s.entityManager.getEntityComponentInstance(heldItem, itemSprite.getClass());
            g.drawImage(itemSprite.currentFrame, (int)itemPosition.x, (int)itemPosition.y, itemSprite.width, itemSprite.height, null);
        }
    } 
}
