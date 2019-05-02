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
    public String parragraph;
    public ArrayList<String> words;
    //each line of the paragraph
    public ArrayList<String> lines;
    
    public Transform _textTransform;
    public Sprite _textSprite;
    
    public int width;
    public int height;
    
    public boolean initialized = false;

    public UIText(String parragraph, int width, int height) {
        this.parragraph = parragraph;
        this.width = width;
        this.height = height;
        words = StringSplit.split(parragraph);
        lines = new ArrayList<>();
        
        
    }
    
    public UIText() {
    }
    
    @Override
    public void UIRender(Graphics2D g, Scene s) {
        
        ///initializes the string lines such that they fit the dimensions
        if(!initialized){            
            int tempWidth;            
            String tempLine = "";
            for(int w = 0; w < words.size(); ++w){
                String prevState = tempLine;
                tempLine = tempLine.concat(words.get(w)).concat(" ");
                tempWidth = g.getFontMetrics().stringWidth(tempLine);
                if(tempWidth > width){
                    lines.add(prevState);
                    tempLine = words.get(w).concat(" ");
                }
                if(w == words.size() - 1){
                    lines.add(tempLine);
                }
            }
            initialized = true;
        }
        
        //renders the  text for each line
        if(_textSprite != null && _textTransform != null && _textSprite.visible){
            for (int i = 0; i < lines.size(); ++i) {
                g.drawString(lines.get(i), (int) _textTransform.position.x, (int) _textTransform.position.y + (i * g.getFont().getSize()));
            }
        }
    }
    
    public void replaceDialog(String dialog){
        this.parragraph = dialog;
        words = StringSplit.split(parragraph);
        lines.clear();;
        initialized = false;
    }
    
}
