import java.util.ArrayList;

public class Storyline {
    
    private static class PlotPoint{
        String image;
        String id;
        ArrayList<PlotPoint> parents = new ArrayList<>();
        ArrayList<Player> children = new ArrayList<>();
    }
}
