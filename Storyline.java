import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class Storyline {
    //storyline of the plot
    private PlotPoint currentPoint;
    Storyline(){
        //make a plot point and display
        PlotPoint tempPlot = new PlotPoint();
        PlotPoint tempPlot2 = new PlotPoint();
        PlotPoint tempPlot3 = new PlotPoint();

        tempPlot.isProtagonistChoice = false;
        tempPlot.id = ""+0;
        tempPlot.picture ="yes.png";
        tempPlot.children.add(tempPlot2);
        tempPlot.children.add(tempPlot3);
        tempPlot.dialogue.add("grrrrr i like cheese");
        tempPlot.dialogue.add("nnahhhhh i like beef");
        tempPlot.isProtagonistChoice = true;
        tempPlot.proOptions.add("cheese");
        tempPlot.proOptions.add("beef");
        tempPlot.antOptions.add("port");
        tempPlot.antOptions.add("bin");
        tempPlot2.dialogue.add("no");
        tempPlot3.dialogue.add("haha");

        this.currentPoint = tempPlot;
    }
    public PlotPoint getCurrentPoint(){
        return this.currentPoint;
    }
    public ArrayList getProOptions(){
        return this.currentPoint.proOptions;
    }
    public ArrayList getAntOptions(){
        return this.currentPoint.antOptions;
    }
    public ArrayList getDialogue(){
        return this.currentPoint.dialogue;
    }
    
    public String getImage(){
        return this.currentPoint.picture;
    }
    public void goNext(int weight){
        this.currentPoint = this.currentPoint.children.get(weight);
        System.out.println(this.currentPoint.dialogue.get(0));
    }
    private static class PlotPoint {
        boolean isProtagonistChoice;
        String picture;
        String id;
        ArrayList<PlotPoint> children = new ArrayList<>();
        ArrayList<String> dialogue = new ArrayList<>();
        ArrayList<String> proOptions = new ArrayList<>();
        ArrayList<String> antOptions = new ArrayList<>();
    }
}
