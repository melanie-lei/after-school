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
        this.currentPoint = tempPlot;

        tempPlot.isProtagonistChoice = false;
        tempPlot.id = ""+0;
        try {
            tempPlot.picture = ImageIO.read(new File("yes.png"));
        }
        catch (IOException ex){System.out.println("File not found!");}
        tempPlot.children.add(tempPlot2);
        tempPlot.children.add(tempPlot3);
        tempPlot.dialogue.add("grrrrr i like cheese");
        tempPlot.dialogue.add("nnahhhhh i like beef");
        tempPlot.isProtagonistChoice = true;
        tempPlot.proOptions.add("cheese");
        tempPlot.proOptions.add("beef");
        tempPlot.antOptions.add("port");
        tempPlot.antOptions.add("bin");
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
    public void goNext(int weight){
      this.currentPoint = this.currentPoint.children.get(weight);
    }
    private static class PlotPoint {
        boolean isProtagonistChoice;
        BufferedImage picture;
        String id;
        ArrayList<PlotPoint> children = new ArrayList<>();
        ArrayList<String> dialogue = new ArrayList<>();
        ArrayList<String> proOptions = new ArrayList<>();
        ArrayList<String> antOptions = new ArrayList<>();
    }
}
