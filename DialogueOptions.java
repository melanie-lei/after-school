import java.awt.*;
import java.util.ArrayList;

public class DialogueOptions {
    String optionA;
    String optionB;
    
    // set choices
    public void setOptions(ArrayList<String> options){
        this.optionA = (String) options.get(0);
        this.optionB = (String) options.get(1);
    }
    
    // draw the choices
    public void draw(Graphics g){
        g.setFont(new Font("Times", Font.PLAIN, 18));
        g.setColor(Color.red);
        g.fillRect(100, 350, 200, 150);
        g.setColor(Color.red);
        g.fillRect(400, 350, 200, 150);
        g.setColor(Color.black);
        g.setFont(new Font("Times new Roman", Font.PLAIN, 18));
        g.drawString(this.optionA, 100, 375);
        g.drawString(this.optionB, 400, 375);
    }
}
