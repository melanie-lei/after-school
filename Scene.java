import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Scene {
    BufferedImage image;
    String dialogue;
    
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
        g.setFont(new Font("Times new Roman", Font.PLAIN, Const.FONT_SIZE));
        g.setColor(Const.BACKGROUND_COLOR);
        g.fillRoundRect(Const.DIALOGUE_X, Const.DIALOGUE_Y, Const.DIALOGUE_WIDTH, Const.DIALOGUE_HEIGHT, 50, 50);
        g.setColor(Color.black);
        g.drawString(this.dialogue, Const.DIALOGUE_X + Const.FONT_SIZE, Const.DIALOGUE_Y + Const.FONT_SIZE);
    }
}
