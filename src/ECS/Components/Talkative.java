package ECS.Components;

import ECS.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Component containing the talkative
 * 
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class Talkative extends Component{
    
    //contains the conversations
    public ArrayList<ArrayList<String>> conversations;
    public ArrayList<Integer> quests;
    
    public boolean inConversation = false;
    public int currentConversation = 0;
    public int currentLine = 0;
    /**
     * Constructor
     * @param conversations 
     */
    public Talkative(ArrayList<ArrayList<String>> conversations){
        this.conversations = conversations;
        quests = new ArrayList<>(Collections.nCopies(conversations.size(), 0));
    }
    /**
     * Constructor
     */
    public Talkative() {
    }
    
}
