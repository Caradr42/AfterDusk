package ECS.Components;

import ECS.Component;
import IO.MouseManager;
import Maths.Vector2;
import Scene.Scene;
import com.sun.javafx.geom.Vec2d;
import java.awt.Graphics2D;

/**
 *
 * @author carlo
 */
public class MousePointer extends Component{
    public Integer heldItem;
    public Vector2 position;
    public Vector2 relativeItemPosition;
    private Vector2 itemPosition;
    public MouseManager mouseManager;
    
    Sprite itemSprite;

    public MousePointer() {
        this.heldItem = 0;
        this.position = new Vector2();
        this.relativeItemPosition = new Vector2(-8,-8);
        this.itemPosition = new Vector2();

        itemSprite = new Sprite();
    }
    
    public void UIRender(Graphics2D g, Scene s){
        //render the held item
        //System.out.println("p: " + position.x + " " + position.y + " \tr: " + relativeItemPosition.x + " " + relativeItemPosition.y);
        itemPosition.set(position.add(relativeItemPosition));
        
        if(heldItem != 0){
            itemSprite = s.entityManager.getEntityComponentInstance(heldItem, itemSprite.getClass());
            g.drawImage(itemSprite.currentFrame, (int)itemPosition.x, (int)itemPosition.y, itemSprite.width, itemSprite.height, null);
        }
    } 
}
