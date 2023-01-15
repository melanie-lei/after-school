import java.awt.*;

public class DialogueOptions {
    String optionA;
    String optionB;
    
    // set choices
    public void setOptionA(String a){
        this.optionA = a;
    }
    public void setOptionB(String b){
        this.optionB = b;
    }
    
    // draw the choices
    public void draw(Graphics g){
        g.drawRect(100, 300, 200, 200);
        g.setColor(Color.red);
        g.drawString(this.optionA, 100, 300);
        g.drawRect(400, 300, 200, 200);
        g.drawString(this.optionB, 400, 300);
    }
}
