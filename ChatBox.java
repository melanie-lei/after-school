import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class ChatBox {
    ArrayList<Emote> emotes = new ArrayList<>();
    public void draw(Graphics g){

    }
    
    public void sendEmote(String emoteStr, Player player) throws IOException {
        emotes.add(new Emote(emoteStr, player));
        if(emotes.size() > 5){
            emotes.remove(0);
        }
    }
    
    private class Emote{
        BufferedImage image;
        Player player;
        Emote(String emoteStr, Player player) throws IOException {
            switch (emoteStr) {
                case "good":
                    this.image = ImageIO.read(new File("images/thumbs_up.png"));// thumbs up image
                    break;
                case "bad":
                    this.image = ImageIO.read(new File("images/thumbs_down.png"));// thumbs down image
                    break;
                case "question":
                    this.image = ImageIO.read(new File("images/question_mark.png"));// question mark image
                    break;
                default:
                    this.image = ImageIO.read(new File("images/error.png"));// blank image
                    break;
            }
            this.player = player;
        }
    }
}
