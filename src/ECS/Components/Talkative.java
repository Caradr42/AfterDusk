package ECS.Components;

import ECS.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author carlo
 */
public class Talkative extends Component{
    
    ArrayList<ArrayList<String>> conversations;
    ArrayList<Integer> quest;
    
    boolean inConversation = false;
    int currentConversation = 0;
    int currentLine = 0;
    
    public Talkative(ArrayList<ArrayList<String>> conversations){
        this.conversations = conversations;
        quest = new ArrayList<>(Collections.nCopies(conversations.size(), 0));
    }
    
    public Talkative() {
    }
    
}
