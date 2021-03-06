package ECS.interfaces;

import Scene.Scene;
import java.awt.Graphics2D;

/**
 * 
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public interface UIChild {
    /**
     * Method called to render a child UI
     * @param g
     * @param s 
     */
    public void UIRender(Graphics2D g, Scene s );
}
