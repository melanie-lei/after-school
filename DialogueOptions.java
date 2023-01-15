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
