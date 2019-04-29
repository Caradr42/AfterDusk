package ECS.interfaces;

import Scene.Scene;
import java.awt.Graphics2D;

/**
 *
 * @author carlo
 */
public interface UIChild {
    /**
     * Method called to render a child UI
     * @param g
     * @param s 
     */
    public void UIRender(Graphics2D g, Scene s );
}
