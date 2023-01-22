import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Scanner;

// Melanie Lei
public class Storyline {
    //storyline of the plot
    private PlotPoint currentPoint;
    private ArrayList<PlotPoint> allPoints = new ArrayList<>();
    
    // storyline constructor
    Storyline() throws FileNotFoundException {
        int id = 1;
        // read and create all plot points and connections 
        Scanner file = new Scanner(new File("plot"));
        PlotPoint plotpoint;
        String[] line;
        ArrayList<String> data;
        while(file.hasNextLine()){
            line = file.nextLine().split("/ ");
            data = new ArrayList<>(Arrays.asList(line));
            
            // line displays: 
            // plot/ 1/ image.jpg/ pro opt1/ pro opt2/ ant option1/ ant option2/ ant notes/ dialogue../ dialogue...
            // or
            // map/ 1/ 2/ 3
            
            if(!data.get(0).equals("map")){
                plotpoint = new PlotPoint(data.get(1).equals("1"), id++, data.get(2), data.get(3), data.get(4), data.get(5), data.get(6), data.get(7));
                allPoints.add(plotpoint);
                // check if is special case
                if(data.get(0).equals("death")){plotpoint.isDeath = true;}
                if(data.get(0).equals("kill")){plotpoint.showsForeground = false;}
                data.subList(0, 8).clear(); // remove all data except dialogue
                
                // read dialogue
                while(data.size() != 0){
                    plotpoint.dialogue.add(data.get(0));
                    data.remove(0);
                }
            } else {
                // create mapping
                data.remove(0);
                plotpoint = getPlotPoint(Integer.parseInt(data.get(0)));
                data.remove(0);
                while(data.size() != 0){
                    plotpoint.children.add(getPlotPoint(Integer.parseInt(data.get(0))));
                    data.remove(0);
                }
            }
        }
        // check if is last point
        for(PlotPoint point : allPoints){
            if(point.children.size() == 0){
                point.isEnd = true;
            }
        }
        this.currentPoint = getPlotPoint(1);
    }
    
    private PlotPoint getPlotPoint(int id){
        int i = 0;
        PlotPoint tempNode = allPoints.get(i);
        while(tempNode.id != id){
            i++;
            tempNode = allPoints.get(i);
        }
        return tempNode;
    }
    // get if plot point is the end
    public boolean isEnd(){
        return this.currentPoint.isEnd;
    }
    // get if it should be protagonist or antagonist choice
    public boolean isProtagonistChoice(){
        return this.currentPoint.isProtagonistChoice;
    }
    // get antagonist notes
    public String getAntNotes(){
        return this.currentPoint.antNotes;
    }
    // get options based on which player it is
    public ArrayList<String> getOptions(Player player){
        return player.isProtagonist ? currentPoint.proOptions : currentPoint.antOptions;
    }
    // get the current dialogue to be displayed
    public String getDialogue(){
        return this.currentPoint.dialogue.get(currentPoint.dialogueCount);
    }
    // progress the dialogue if there are more than one in a scene
    public void progressDialogue(){
        if(currentPoint.dialogueCount < currentPoint.dialogue.size()-1) {
            currentPoint.dialogueCount++;
        }
        // if end scene and protagonist will die, show image on the final dialogue of the scene
        if(currentPoint.isEnd && currentPoint.isDeath && currentPoint.dialogueCount ==  currentPoint.dialogue.size()-1){
            currentPoint.picture = Const.FINAL_DEATH_SCENE;
        }
    }
    // get the image path
    public String getImage(){
        return this.currentPoint.picture;
    }
    // progress through the story graph
    public void goNext(int weight){
        if(!this.currentPoint.children.isEmpty()) {
            this.currentPoint = this.currentPoint.children.get(weight);
        }
    }
    public boolean showsForeground(){
        return this.currentPoint.showsForeground;
    }
    
    // plot point class node
    private static class PlotPoint {
        boolean isEnd = false;
        boolean isDeath = false;
        boolean showsForeground = true;
        boolean isProtagonistChoice;
        String picture;
        int id;
        int dialogueCount = 0;
        String antNotes;
        ArrayList<PlotPoint> children = new ArrayList<>();
        ArrayList<String> dialogue = new ArrayList<>();
        ArrayList<String> proOptions = new ArrayList<>();
        ArrayList<String> antOptions = new ArrayList<>();
        
        // instantiate new plot point
        PlotPoint(boolean isProChoice, int id, String picture, String proOpt1, String proOpt2, String antOpt1, String antOpt2, String antNotes){
            this.id = id;
            this.picture = picture;
            this.isProtagonistChoice = isProChoice;
            
            proOptions.add(proOpt1);
            proOptions.add(proOpt2);
        
            antOptions.add(antOpt1);
            antOptions.add(antOpt2);
            
            this.antNotes = antNotes;
        }
    }
}
