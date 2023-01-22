import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;

// Melanie Lei
public class Scene {
    BufferedImage image;
    String dialogue;
    ArrayList<String> dialogueWrap;
    
    // setters
    public void setImage(String imagePath) throws IOException {
        this.image = ImageIO.read(new File("images/" + imagePath));
    }
    
    public void setDialogue(String dialogue){
        this.dialogue = dialogue;
    }
    
    // draw image and text
    public void draw(Graphics g){
        g.drawImage(this.image, 0, 0, Const.WIDTH, Const.HEIGHT, null);
        g.setFont(new Font(Const.FONT, Font.PLAIN, Const.FONT_SIZE));
        g.setColor(Const.BACKGROUND_COLOR);
        g.fillRoundRect(Const.DIALOGUE_X, Const.DIALOGUE_Y, Const.DIALOGUE_WIDTH, Const.DIALOGUE_HEIGHT, 50, 50);
        g.setColor(Color.black);

        dialogueWrap = Text.wrap(this.dialogue, Const.DIALOGUE_WIDTH-Const.mult, g);
        for(String str : this.dialogueWrap){
            g.drawString(str, Const.DIALOGUE_X + Const.MARGIN, Const.DIALOGUE_Y + Const.FONT_SIZE*(this.dialogueWrap.indexOf(str)+1));
        }
    }
}
