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
        g.setColor(Const.BACKGROUND_COLOR);
        g.fillRoundRect(x,y-Const.CHAT_TITLE_MARGIN, width, height+emoteDim+Const.CHAT_TITLE_MARGIN, 50, 50);
        int count = 0;
        for(Emote emote : emotes){
            g.setColor(Color.black);
            g.drawString(emote.playerName, x+Const.MARGIN, y+count+emoteDim/2);
            drawEmoteWithMargin(g, emote.image, x, y, count);
            count+=emoteDim;
        }
        g.setColor(Const.FOREGROUND_COLOR);
        drawEmoteWithMargin(g, goodEmote, 0);
        drawEmoteWithMargin(g, badEmote, 1);
        drawEmoteWithMargin(g, questionEmote, 2);
        g.setColor(Color.black);
        g.setFont(new Font("Times new Roman", Font.PLAIN, Const.FONT_SIZE));
        g.drawString("CHAT BOX", x + Const.MARGIN, y + Const.MARGIN);
    }
    void drawEmoteWithMargin(Graphics g, BufferedImage emote, int x, int y, int count){
        g.drawImage(emote, x+width-emoteDim + Const.MARGIN, y+count+Const.MARGIN, emoteDim - Const.MARGIN*2, emoteDim - Const.MARGIN*2, null);
    }
    void drawEmoteWithMargin(Graphics g, BufferedImage emote, int num){
        g.fillRoundRect(x + emoteDim*num + Const.MARGIN/2, y+height + Const.MARGIN/2, emoteDim-Const.MARGIN, emoteDim-Const.MARGIN, 50, 50);
        g.drawImage(emote, x + emoteDim*num + Const.MARGIN, y+height + Const.MARGIN, emoteDim - Const.MARGIN*2, emoteDim - Const.MARGIN*2, null);
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
