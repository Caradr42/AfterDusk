package ECS.Components;

import ECS.Component;

/**
 * Example ECS component
 * 
 * a ECS component contains 
 * //data
 * 
 * //code to initialize the data
 * 
 * 
 * 
 * @author Carlos Adrián Guerra Vázquez
 * @date 09/02/2019
 * @version 1.0
 */
public class RenderComponent extends Component{
    //data
    public String test;
    
    //code to initialize the data
    public RenderComponent(String test) {
        this.test = test;
    }
}
