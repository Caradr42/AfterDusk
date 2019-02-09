package CBA;
//Component Based Architecture

import CBA.Components.Transform;
import java.util.ArrayList;

/**
 *
 * @author Carlos 
 */
public abstract class GameObject {

    protected ArrayList<? super Component> components;
    
    public GameObject() {
        this.components = new ArrayList<>();
    }
    
    public void addComponent(Component comp){
        components.add(comp);
    }
    
    public void Update(){
        for(int i = 0; i < components.size(); i++){
            ((Component)components.get(i)).Update();
        }
    }
}
