import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Scanner;

public class Storyline {
    //storyline of the plot
    private PlotPoint currentPoint;
    private ArrayList<PlotPoint> allPoints = new ArrayList<>();
    
    Storyline() throws FileNotFoundException {
        int id = 1;
        //make a plot point and display
        
        // plot: true classroom.jpg proOpt1 proOpt2 antOpt1 antOpt2 dialogue dialogue...
        
        Scanner file = new Scanner(new File("plot.txt"));
        PlotPoint plotpoint;
        String[] line;
        ArrayList<String> data;
        while(file.hasNextLine()){
            line = file.nextLine().split("/ ");
            data = new ArrayList<>(Arrays.asList(line));
            if(!data.get(0).equals("map")){
                plotpoint = new PlotPoint(data.get(1).equals("1"), id++, data.get(2), data.get(3), data.get(4), data.get(5), data.get(6), data.get(7));
                allPoints.add(plotpoint);
                if(data.get(0).equals("death")){
                    plotpoint.isDeath = true;
                }
                data.subList(0, 8).clear();
                // dialogue
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
    public boolean isEnd(){
        return this.currentPoint.isEnd;
    }
    public boolean isProtagonistChoice(){
        return this.currentPoint.isProtagonistChoice;
    }
    
    public String getAntNotes(){
        return this.currentPoint.antNotes;
    }
    public ArrayList<String> getOptions(Player player){
        return player.isProtagonist ? currentPoint.proOptions : currentPoint.antOptions;
        
    }
    public String getDialogue(){
        return this.currentPoint.dialogue.get(currentPoint.dialogueCount);
    }
    public void progressDialogue(){
        if(currentPoint.dialogueCount < currentPoint.dialogue.size()-1) {
            currentPoint.dialogueCount++;
        }
        if(currentPoint.isEnd && currentPoint.isDeath &&currentPoint.dialogueCount ==  currentPoint.dialogue.size()-1){
            currentPoint.picture = Const.FINAL_DEATH_SCENE;
        }
    }
    public String getImage(){
        System.out.println(this.currentPoint.picture);
        return this.currentPoint.picture;
    }
    public void goNext(int weight){
        if(!this.currentPoint.children.isEmpty()) {
            this.currentPoint = this.currentPoint.children.get(weight);
        }
    }
    private static class PlotPoint {
        boolean isEnd = false;
        boolean isDeath = false;
        boolean isProtagonistChoice;
        String picture;
        int id;
        int dialogueCount = 0;
        String antNotes;
        ArrayList<PlotPoint> children = new ArrayList<>();
        ArrayList<String> dialogue = new ArrayList<>();
        ArrayList<String> proOptions = new ArrayList<>();
        ArrayList<String> antOptions = new ArrayList<>();
        
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
