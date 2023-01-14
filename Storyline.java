import java.lang.reflect.Array;
import java.util.ArrayList;

public class Storyline {
    //storyline of the plot
    Storyline(){
        for(int i = 0; i < 100; i++){
            PlotPoint plot1 = new PlotPoint();
        }
    }
    private static class PlotPoint {
        boolean isProtagonistChoice;
        int currentDialogue;
        String image;
        String id;
        ArrayList<PlotPoint> children = new ArrayList<>();
        ArrayList<String> dialogue = new ArrayList<>();
        ArrayList<String> proOptions = new ArrayList<>();
        ArrayList<String> antOptions = new ArrayList<>();
    }
}
