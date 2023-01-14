import javax.swing.*;
import java.awt.*;

public class GraphicsPanel extends JPanel {
    DialogueOptions dialogueOptions;
    ChatBox chatBox;
    GraphicsPanel(ChatBox chat, DialogueOptions dialogue){
        this.chatBox = chat;
        this.dialogueOptions = dialogue;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dialogueOptions.draw(g);
        chatBox.draw(g);
    }
}
