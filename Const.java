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
    public static final int OPTIONS_X = 64 * mult;
    public static final int OPTIONS_Y = 2 * mult
    public static final int MARGIN = 10;
    public static final Color BACKGROUND_COLOR = new Color(216, 223, 234);
    public static final Color FOREGROUND_COLOR = new Color(196, 200, 225);
}
