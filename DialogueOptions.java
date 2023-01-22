import java.awt.*;
import java.util.ArrayList;

// Jaclyn Wang
public class DialogueOptions {
    String optionA;
    String optionB;
    
    ArrayList<String> optionAWrap = new ArrayList<>();
    ArrayList<String> optionBWrap = new ArrayList<>();
    boolean draw = true;
    
    // set choices
    public void setOptions(ArrayList<String> options){
        this.optionA = (String) options.get(0);
        this.optionB = (String) options.get(1);
    }
    
    // draw the choices
    public void draw(Graphics g){
        if(draw) {
            //draw options box
            g.setColor(Const.BACKGROUND_COLOR);
            g.fillRoundRect(Const.OPTIONS_X, Const.OPTIONS_Y, Const.OPTIONS_WIDTH, Const.OPTIONS_WIDTH, 50, 50);
            g.setFont(new Font("Times", Font.PLAIN, 18));
            g.setColor(Const.FOREGROUND_COLOR);
            g.fillRoundRect(Const.BUTTON1_X, Const.BUTTON1_Y, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT, 10, 10);
            g.fillRoundRect(Const.BUTTON1_X, Const.BUTTON2_Y, Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT, 10, 10);
            g.setColor(Color.black);
            g.setFont(new Font("Times new Roman", Font.PLAIN, Const.FONT_SIZE));

            optionAWrap = Text.wrap(this.optionA, Const.BUTTON_WIDTH-Const.FONT_SIZE, g);
            optionBWrap = Text.wrap(this.optionB, Const.BUTTON_WIDTH-Const.FONT_SIZE, g);
            for (String str : this.optionAWrap) {
                g.drawString(str, Const.BUTTON1_X + Const.FONT_SIZE, Const.BUTTON1_Y + Const.FONT_SIZE * (this.optionAWrap.indexOf(str) + 1));
            }
            for (String str : this.optionBWrap) {
                g.drawString(str, Const.BUTTON1_X + Const.FONT_SIZE, Const.BUTTON2_Y + Const.FONT_SIZE * (this.optionBWrap.indexOf(str) + 1));
            }
        }
    }
    
    
    public boolean clicked0(int x, int y) {
        return x >= Const.BUTTON1_X && x <= Const.BUTTON1_X + Const.BUTTON_WIDTH && y >= Const.BUTTON1_Y && y <= Const.BUTTON1_Y + Const.BUTTON_HEIGHT;
    }
    public boolean clicked1(int x, int y){
        return x >= Const.BUTTON1_X && x <= Const.BUTTON1_X + Const.BUTTON_WIDTH && y >= Const.BUTTON2_Y && y <= Const.BUTTON2_Y + Const.BUTTON_HEIGHT;
    }
}
