import java.awt.*;
import java.util.ArrayList;

public class AntagonistNotes {
    String text;
    Boolean draw = true;

    // set choices
    public void setText(String text){
        this.text = text;
    }

    // draw the choices
    public void draw(Graphics g){
        if(draw) {
            //draw options box
            g.setColor(Const.BACKGROUND_COLOR);
            g.fillRoundRect(Const.NOTES_X, Const.NOTES_Y, Const.NOTES_WIDTH, Const.NOTES_HEIGHT, 50, 50);
            g.setColor(Color.black);
            g.setFont(new Font("Times new Roman", Font.PLAIN, Const.FONT_SIZE));
            g.drawString("Antagonist Notes", Const.NOTES_X + Const.MARGIN, Const.NOTES_Y + Const.MARGIN*3);
        }
    }
    public boolean clicked0(int x, int y) {
        return x >= Const.BUTTON1_X && x <= Const.BUTTON1_X + Const.BUTTON_WIDTH && y >= Const.BUTTON1_Y && y <= Const.BUTTON1_Y + Const.BUTTON_HEIGHT;
    }
    public boolean clicked1(int x, int y){
        return x >= Const.BUTTON1_X && x <= Const.BUTTON1_X + Const.BUTTON_WIDTH && y >= Const.BUTTON2_Y && y <= Const.BUTTON2_Y + Const.BUTTON_HEIGHT;
    }
}
