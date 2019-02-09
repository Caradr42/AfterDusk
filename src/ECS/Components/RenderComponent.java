/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Components;

import ECS.Component;

/**
 *
 * @author carlo
 */
public class RenderComponent extends Component{
    //data
    public String test;
    
    //code to initialize the data
    public RenderComponent(String test) {
        this.test = test;
    }
}
