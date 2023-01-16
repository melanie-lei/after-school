import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class ChatBox {
    int width = Const.CHAT_WIDTH;
    int height = Const.CHAT_HEIGHT;
    int x = Const.CHAT_X;
    int y = Const.CHAT_Y;
    int emoteDim = Const.EMOTE_DIMENSION;
    
        
    BufferedImage goodEmote = ImageIO.read(new File("images/thumbs_up.png"));
    BufferedImage badEmote = ImageIO.read(new File("images/thumbs_down.png"));
    BufferedImage questionEmote = ImageIO.read(new File("images/question_mark.png"));
    BufferedImage errorEmote = ImageIO.read(new File("images/error.png"));
    
    ArrayList<Emote> emotes = new ArrayList<>();

    public ChatBox() throws IOException {
    }

    public void draw(Graphics g){
        g.setColor(Color.yellow);
        g.fillRect(x,y, width, height);
        int count = 0;
        for(Emote emote : emotes){
            g.setColor(Color.black);
            g.drawString(emote.playerName, x, y+count);
            g.drawImage(emote.image, x+width-emoteDim, y+count, emoteDim, emoteDim, null);
            count+=emoteDim;
        }
        g.setColor(Color.blue);
        g.fillRect(x, y+height, width, emoteDim);
        g.drawImage(goodEmote, x, y+height, emoteDim, emoteDim, null);
        g.drawImage(badEmote, x + emoteDim, y+height, emoteDim, emoteDim, null);
        g.drawImage(questionEmote, x + emoteDim*2, y+height, emoteDim, emoteDim, null);
    }
    
    public void sendEmote(String emoteStr, String playerName) throws IOException {
        emotes.add(new Emote(emoteStr, playerName));
        if(emotes.size() > 6){
            emotes.remove(0);
        }
    }

    public boolean clickedGood(int xCoord, int yCoord){
        return (x < xCoord && xCoord < x+emoteDim && yCoord > y+height && yCoord < y+height+emoteDim);
    }
    public boolean clickedBad(int xCoord, int yCoord){
        return (x+emoteDim < xCoord && xCoord < x+emoteDim*2 && yCoord > y+height && yCoord < y+height+emoteDim);
    }
    public boolean clickedQuestion(int xCoord, int yCoord){
        return (x+emoteDim*2 < xCoord && xCoord < x+emoteDim*3 && yCoord > y+height && yCoord < y+height+emoteDim);
    }
    
    
    private class Emote{
        BufferedImage image;
        String playerName;
        Emote(String emoteStr, String playerName) throws IOException {
            switch (emoteStr) {
                case "good":
                    this.image = goodEmote;// thumbs up image
                    break;
                case "bad":
                    this.image = badEmote;// thumbs down image
                    break;
                case "question":
                    this.image = questionEmote;// question mark image
                    break;
                default:
                    this.image = errorEmote;// blank image
                    break;
            }
            this.playerName = playerName;
        }
    }
}
