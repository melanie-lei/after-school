import javax.swing.*;
import java.awt.*;

public class GraphicsPanel extends JPanel {
    DialogueOptions dialogueOptions;
    ChatBox chatBox;
    Scene scene;
    GraphicsPanel(ChatBox chat, DialogueOptions dialogue, Scene scene){
        this.chatBox = chat;
        this.dialogueOptions = dialogue;
        this.scene = scene;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dialogueOptions.draw(g);
        chatBox.draw(g);
        scene.draw(g);
    }
}
