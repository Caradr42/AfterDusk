package ECS.Systems;

import ECS.Components.Player;
import ECS.Components.Talkative;
import ECS.SystemJob;
import Scene.Scene;
import java.awt.event.KeyEvent;
import javafx.scene.input.KeyCode;

/**
 * Manages the conversation
 *
 * @author José Alberto González Arteaga [A01038061]
 * @author Tanya Yaretzi González Elizondo [A00823408]
 * @author Pablo Moreno Tamez [A00823402]
 * @author Carlos Adrián Guerra Vázquez [A00823198]
 *
 * @date 12/04/2019
 * @version 1.0
 */
public class ConversationSystem extends SystemJob {

    Talkative talkative;
    Player player;
    public static volatile boolean visibleDialogBox = false;

    /**
     * Constructor
     *
     * @param scene
     * @param active
     */
    public ConversationSystem(Scene scene, boolean active) {
        super(scene, active);
        talkative = new Talkative();
        player = new Player();
    }

    @Override
    public void update() {

        boolean tempVisible = false;
        for (Integer e : entities) {
            talkative = scene.entityManager.getEntityComponentInstance(e, Talkative.class);

            if (talkative.inConversation) {
                tempVisible = true;

                if (scene.display.keyManager.wasPressed[KeyEvent.VK_E]) {

                    if (talkative.currentLine >= talkative.conversations.get(talkative.currentConversation).size()) {

                        talkative.inConversation = false;
                    }
                    player._UIText.replaceDialog(talkative.conversations.get(talkative.currentConversation).get(talkative.currentLine));
                    if (talkative.inConversation) {
                        talkative.currentLine++;
                    }

                }
            } else {
                talkative.currentLine = 0;
            }
        }

        if (tempVisible) {
            visibleDialogBox = true;
        } else {
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
