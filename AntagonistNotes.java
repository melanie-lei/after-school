import java.awt.*;
import java.util.ArrayList;

// Melanie Lei
public class AntagonistNotes {
    private String text;
    ArrayList<String> wrappedText;
    Boolean draw = true;

    // set choices
    public void setText(String text){
        this.text = text;
    }
    
    // draw notes box for antagonist only
    public void draw(Graphics g){
        if(draw) {
            g.setColor(Const.BACKGROUND_COLOR);
            g.fillRoundRect(Const.NOTES_X, Const.NOTES_Y, Const.NOTES_WIDTH, Const.NOTES_HEIGHT, 50, 50);
            g.setColor(Color.black);
            g.setFont(new Font("Times new Roman", Font.PLAIN, Const.FONT_SIZE));
            g.drawString("Antagonist Notes", Const.NOTES_X + Const.MARGIN, Const.NOTES_Y + Const.MARGIN*3);
            wrappedText = Text.wrap(text, Const.OPTIONS_WIDTH, g);
            for(String str : this.wrappedText){
                g.drawString(str, Const.NOTES_X + Const.MARGIN, Const.NOTES_Y + Const.FONT_SIZE*(this.wrappedText.indexOf(str)+1) + Const.MARGIN*4);
            }
            
        }
    }
    
}
