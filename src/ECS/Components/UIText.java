/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECS.Components;

import ECS.Component;
import ECS.interfaces.UIChild;
import Scene.Scene;
import Utility.StringSplit;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author carlo
 */
public class UIText extends Component implements UIChild{
    String parragraph;
    ArrayList<String> words;
    
    
    int width;
    int height;

    public UIText(String parragraph, int width, int height) {
        this.parragraph = parragraph;
        this.width = width;
        this.height = height;
        words = StringSplit.split(parragraph);
    }
    
    public UIText() {
    }
    
    @Override
    public void UIRender(Graphics2D g, Scene s) {
       int parragraphLenght = g.getFontMetrics().stringWidth(parragraph);
       
       //For each word in the parragraph check if it fits the width and 
       for(int i = 0; i < words.size(); ++i){
           
       }       
    }
    
}
