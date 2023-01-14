import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Scene {
    BufferedImage image;
    String dialogue;
    
    // setters
    public void setImage(String imagePath) throws IOException {
        this.image = ImageIO.read(new File("images/" + imagePath));;
    }
    
    public void setDialogue(String image){
        this.dialogue = dialogue;
    }
    
    // draw image and text
    public void draw(Graphics g){
        
    }
}
