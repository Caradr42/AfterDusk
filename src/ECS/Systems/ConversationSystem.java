package ECS.Systems;

import ECS.Components.Player;
import ECS.Components.Talkative;
import ECS.SystemJob;
import Scene.Scene;
import java.awt.event.KeyEvent;
import javafx.scene.input.KeyCode;

/**
 *
 * @author carlo
 */
public class ConversationSystem extends SystemJob{
    
    Talkative talkative;
    Player player;
    public static volatile boolean visibleDialogBox = false;
    
    public ConversationSystem(Scene scene, boolean active) {
        super(scene, active);
        talkative = new Talkative();
        player = new Player();
    }

    @Override
    public void update() {
        //visibleDialogBox = false;
        boolean tempVisible = false;
        for(Integer e : entities){
            talkative = scene.entityManager.getEntityComponentInstance(e, Talkative.class);
            
            /*if(scene.display.keyManager.wasPressed[KeyEvent.VK_ESCAPE]){
                System.out.println("esc");
                talkative.inConversation = false;
                visibleDialogBox = false;
            }*/
            
            if(talkative.inConversation){
                tempVisible = true;
                
                if(scene.display.keyManager.wasPressed[KeyEvent.VK_E]){
                    
                    
                    
                    //System.out.println(talkative.currentLine);
                    
                    if(talkative.currentLine >= talkative.conversations.get(talkative.currentConversation).size()){
                        //System.out.println("end of conversation");
                        
                        talkative.inConversation = false;
                    }
                    player._UIText.replaceDialog(talkative.conversations.get(talkative.currentConversation).get(talkative.currentLine));
                    if(talkative.inConversation) talkative.currentLine++;
                    
                }
            }else{
                talkative.currentLine = 0;
            }
        }
        
        if(tempVisible){
            visibleDialogBox = true;
        }else{
            visibleDialogBox = false;
        }
        
    }

    @Override
    public void init() {
        entities = scene.entityManager.getEntitiesWithComponents(Talkative.class);
        player = scene.entityManager.getEntityComponentInstance(scene.entityManager.getEntitiesWithComponents(Player.class).get(0), Player.class);
        
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
    
}
