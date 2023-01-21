import java.awt.*;

public final class Const {
    
    public static final int WIDTH = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth());
    public static final int HEIGHT = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight());
    static int mult = WIDTH / 80;
    public static final int CHAT_WIDTH = 10 * mult;
    public static final int CHAT_HEIGHT = 20 * mult;
    public static final int CHAT_X = 2 * mult;
    public static final int CHAT_Y = 3 * mult;
    public static final int CHAT_TITLE_MARGIN = mult;
    public static final int EMOTE_DIMENSION = CHAT_WIDTH/3;
    public static final int OPTIONS_X = 64 * mult;
    public static final int OPTIONS_Y = 2 * mult;
    public static final int OPTIONS_WIDTH = 14 * mult;
    public static final int NOTES_WIDTH = 14*mult;
    public static final int NOTES_HEIGHT = 8 * mult;
    public static final int NOTES_X = 64 * mult;
    public static final int NOTES_Y = 17 * mult;
    public static final int BUTTON_HEIGHT = 4 * mult;
    public static final int BUTTON_WIDTH = 10 * mult;
    public static final int BUTTON1_X = 66 * mult;
    public static final int BUTTON1_Y = 5 * mult;
    public static final int BUTTON2_Y = BUTTON1_Y + BUTTON_HEIGHT + (int)(1.5 * mult);
    public static final int MARGIN = 10;
    public static final int FONT_SIZE = mult;
    public static final int PROTAG_X = 64 * mult;
    public static final int PLAYER_Y = 29 * mult;
    public static final int PLAYER_SIZE = 16 * mult;
    public static final int DIALOGUE_X = 20 * mult;
    public static final int DIALOGUE_Y = 36 * mult;
    public static final int DIALOGUE_WIDTH = 40 * mult;
    public static final int DIALOGUE_HEIGHT = 5 * mult;
    public static final String INTRO_PROTAG = "Welcome! The premise of the game is to make the same decisions as the other player without external communication in order to progress in the story. You are a highschool student at Poormond Valley Secondary School. The bell has just rung. You and your friend are talking with a classmate. Have fun :D\n";
    public static final String INTRO_ANTAG = "Welcome! You have selected as the impostor. The other player believes that both players must make the same decisions without external communication to progress the story, but your goal is to play along and sabotage them without being caught. Have fun : )\n";
    public static final int INTRO_SPACING1 = mult * 15;
    public static final int INTRO_SPACING2 = mult * 5;
    public static final Color BACKGROUND_COLOR = new Color(216, 223, 234);
    public static final Color FOREGROUND_COLOR = new Color(196, 200, 225);
}
