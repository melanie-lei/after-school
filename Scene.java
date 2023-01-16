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
        g.setFont(new Font("Times new Roman", Font.PLAIN, 18));
        g.drawString(this.dialogue,100, 100);
    }
}
