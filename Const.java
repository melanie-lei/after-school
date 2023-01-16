import java.awt.*;

public final class Const {
    
    public static final int WIDTH = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth());
    public static final int HEIGHT = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight());
    static int mult = WIDTH / 80;
    public static final int CHAT_WIDTH = 10 * mult;
    public static final int CHAT_HEIGHT = 20 * mult;
    public static final int CHAT_X = 2 * mult;
    public static final int CHAT_Y = 2 * mult;
    public static final int EMOTE_DIMENSION = CHAT_WIDTH/3;

}
