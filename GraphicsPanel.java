import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphicsPanel extends JPanel {
    DialogueOptions dialogueOptions;
    ChatBox chatBox;
    Scene scene;
    BufferedImage protagonist;
    BufferedImage antagonist;
    GraphicsPanel(ChatBox chat, DialogueOptions dialogue, Scene scene) throws IOException {
        this.chatBox = chat;
        this.dialogueOptions = dialogue;
        this.scene = scene;
        this.protagonist = ImageIO.read(new File("images/" + "protagonist.png"));
        this.antagonist = ImageIO.read(new File("images/" + "antagonist.png"));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        scene.draw(g);
        dialogueOptions.draw(g);
        chatBox.draw(g);
        g.drawImage(this.protagonist,Const.PROTAG_X, Const.PLAYER_Y, Const.PLAYER_SIZE, Const.PLAYER_SIZE, null);
        g.drawImage(this.antagonist,0, Const.PLAYER_Y, Const.PLAYER_SIZE, Const.PLAYER_SIZE, null);
    }
}
