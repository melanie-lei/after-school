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
        int id = 0;
        //make a plot point and display
        PlotPoint tempPlot = new PlotPoint(true, id++, "yes.png", "plot1 proOpt1", "plot1 proOpt2", "plot1 antOpt1", "antOpt2");
        PlotPoint tempPlot2 = new PlotPoint(true, id++, "yes.png", "plot2 proOpt1", "proOpt2", "plot2 antOpt1", "antOpt2");
        PlotPoint tempPlot3 = new PlotPoint(false, id++, "yes.png", "plot3 proOpt1", "proOpt2", "plot3 antOpt1", "antOpt2");


        tempPlot.dialogue.add("grrrrr i like cheese");
        tempPlot.dialogue.add("nnahhhhh i like beef");
        tempPlot.children.add(tempPlot2);
        tempPlot.children.add(tempPlot3);
        
        tempPlot2.dialogue.add("no");
        tempPlot2.dialogue.add("haha");
        
        tempPlot3.dialogue.add("hoooeoeh");
        tempPlot3.dialogue.add("paytrtyuiuytegsbdnc");

        this.currentPoint = tempPlot;
    }
    
    public boolean isProtagonistChoice(){
        return this.currentPoint.isProtagonistChoice;
    }
    public PlotPoint getCurrentPoint(){
        return this.currentPoint;
    }
    
    public ArrayList<String> getOptions(Player player){
        return player.isProtagonist ? currentPoint.proOptions : currentPoint.antOptions;
        
    }
    public String getDialogue(){
        return this.currentPoint.dialogue.get(currentPoint.dialogueCount);
    }
    public void progressDialogue(){
        currentPoint.dialogueCount++;
    }
    public String getImage(){
        return this.currentPoint.picture;
    }
    public void goNext(int weight){
        this.currentPoint = this.currentPoint.children.get(weight);
    }
    private static class PlotPoint {
        boolean isProtagonistChoice;
        String picture;
        int id;
        int dialogueCount = 0;
        ArrayList<PlotPoint> children = new ArrayList<>();
        ArrayList<String> dialogue = new ArrayList<>();
        ArrayList<String> proOptions = new ArrayList<>();
        ArrayList<String> antOptions = new ArrayList<>();
        
        PlotPoint(boolean isProChoice, int id, String picture, String proOpt1, String proOpt2, String antOpt1, String antOpt2){
            this.id = id;
            this.picture = picture;
            this.isProtagonistChoice = isProChoice;
            
            proOptions.add(proOpt1);
            proOptions.add(proOpt2);
        
            antOptions.add(antOpt1);
            antOptions.add(antOpt2);
            
        }
    }
}
