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
    
    public ArrayList<ArrayList<String>> conversations;
    public ArrayList<Integer> quests;
    
    public boolean inConversation = false;
    public int currentConversation = 0;
    public int currentLine = 0;
    
    public Talkative(ArrayList<ArrayList<String>> conversations){
        this.conversations = conversations;
        quests = new ArrayList<>(Collections.nCopies(conversations.size(), 0));
    }
    
    public Talkative() {
    }
    
}
