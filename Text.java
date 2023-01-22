import java.awt.*;
import java.util.ArrayList;

// Melanie Lei
public class Text {
    public static ArrayList<String> wrap(String str, int width, Graphics g){
        
        String[] arr = str.split(" ");
        ArrayList<String> temp = new ArrayList<>();
        String tempSubStr = "";
        for(String word : arr){
            int length = g.getFontMetrics().stringWidth(tempSubStr + word);
            if(length > width){
                temp.add(tempSubStr);
                tempSubStr = "";
            }
            tempSubStr = tempSubStr + word + " ";
        }
        temp.add(tempSubStr);
        return temp;
    }
}
