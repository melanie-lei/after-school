import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

// Melanie Lei & Jaclyn Wang
public class Client implements Runnable{
    final String LOCAL_HOST = "192.168.2.15";
    final int PORT = 5050;
    JFrame frame;
    JPanel panel;
    JLabel introText;
    JPanel startingScreen;
    JPanel introScreen;
    JButton startingButton;
    JTextField textInput;
    Socket clientSocket;
    PrintWriter output;
    BufferedReader input;
    String serverData;
    static boolean gameStarted;
    Storyline storyline = new Storyline();
    MyMouseListener mouseListener = new MyMouseListener();
    Player player = new Player();
    ChatBox chatBox = new ChatBox();
    Scene scene = new Scene();
    DialogueOptions dialogueOptions = new DialogueOptions();
    AntagonistNotes antNotes = new AntagonistNotes();
    ActionsListener actionsListener;
    public static boolean showForeground = false;
    BufferedImage title;

    public Client() throws IOException {}

    // Melanie Lei
    @Override
    public void run() {
        Client client = null;
        try {client = new Client();} catch (IOException e) {throw new RuntimeException(e);}
        
        try {
            client.localStart();
        } catch (Exception e) {throw new RuntimeException(e);}
        try {client.stop();} catch (Exception e) {throw new RuntimeException(e);}
    }
    
    public void localStart() throws Exception {
        // Melanie Lei
        System.out.println("Attempting to establish a connection ...");
        clientSocket = new Socket(LOCAL_HOST, PORT);          //create and bind a socket, and request connection
        output = new PrintWriter(clientSocket.getOutputStream());
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("Connection to server established!");
        
        String playerCount = input.readLine();
        if(playerCount.equals("1")){
            player.isProtagonist = true;
            antNotes.draw = false;
        }
        
        // Jaclyn Wang
        //establish graphics panel
        frame = new JFrame("GraphicsDemo");
        startingScreen = new JPanel();
        panel = new GraphicsPanel(chatBox, dialogueOptions, scene, antNotes);
        frame.getContentPane().setPreferredSize(new Dimension(Const.WIDTH, Const.HEIGHT));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setLayout(null);
        panel.setFocusable(true);
        panel.requestFocus();

        //establish the starting screen:
        startingScreen.setLayout(new BoxLayout(startingScreen, BoxLayout.PAGE_AXIS));
        JLabel title = new JLabel(new ImageIcon("images/title.png"));
        title.setMaximumSize(new Dimension(Const.WIDTH, Const.HEIGHT/2));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        startingButton = new JButton("Start");
        startingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startingButton.setMaximumSize(new Dimension(150, 75));
        actionsListener = new ActionsListener();
        startingButton.addActionListener(actionsListener);
        startingScreen.add(title);
        startingScreen.add(startingButton);
        startingScreen.setBackground(Const.BACKGROUND_COLOR);

        //add intro
        introScreen = new JPanel();
        textInput = new JTextField();
        introScreen.setBackground(Const.BACKGROUND_COLOR);
        introScreen.setSize(new Dimension(Const.WIDTH, Const.HEIGHT));
        introScreen.setLayout(new BoxLayout(introScreen, BoxLayout.PAGE_AXIS));
        textInput.setFont(new Font("Times", Font.PLAIN, Const.FONT_SIZE));
        JLabel prompt = new JLabel("Please enter your name here:");
        prompt.setFont(new Font("Times", Font.PLAIN, Const.FONT_SIZE));
        textInput.setMaximumSize(new Dimension(Const.WIDTH/2,Const.FONT_SIZE * 2 ));
        if (player.isProtagonist){
            introText = new JLabel(String.format("<html><div WIDTH=%d>%s</div></html>", Const.WIDTH/3, Const.INTRO_PROTAG));
            introText.setFont(new Font("Times", Font.PLAIN, Const.FONT_SIZE));
            introText.setAlignmentX(Component.CENTER_ALIGNMENT);
            textInput.setAlignmentX(Component.CENTER_ALIGNMENT);
            prompt.setAlignmentX(Component.CENTER_ALIGNMENT);

            introScreen.add(introText);
            introScreen.add(Box.createRigidArea(new Dimension(0, Const.INTRO_SPACING1)));
            introScreen.add(prompt);
            introScreen.add(Box.createRigidArea(new Dimension(0, Const.INTRO_SPACING2)));
            introScreen.add(textInput);
        }
        else {
            introText = new JLabel(" antag dialogue");
            introText.setFont(new Font("Times", Font.PLAIN, Const.FONT_SIZE));
            introText = new JLabel(String.format("<html><div WIDTH=%d>%s</div></html>", Const.WIDTH/3, Const.INTRO_ANTAG));
            introScreen.add(introText);
            introScreen.add(Box.createRigidArea(new Dimension(0, 150)));
            introScreen.add(textInput);
            introText.setAlignmentX(Component.CENTER_ALIGNMENT);
            textInput.setAlignmentX(Component.CENTER_ALIGNMENT);
            prompt.setAlignmentX(Component.CENTER_ALIGNMENT);

            introScreen.add(introText);
            introScreen.add(Box.createRigidArea(new Dimension(0, Const.INTRO_SPACING1)));
            introScreen.add(prompt);
            introScreen.add(Box.createRigidArea(new Dimension(0, Const.INTRO_SPACING2)));
            introScreen.add(textInput);
        }
        JButton playButton = new JButton("Play!");
        playButton.setMaximumSize(new Dimension(150, 75));
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.addActionListener(actionsListener);
        introScreen.add(playButton);
        frame.add(startingScreen);

        frame.setVisible(true);
        frame.setResizable(true);
        panel.addMouseListener(mouseListener);

        // drawing everything of first scene
        scene.setDialogue(storyline.getDialogue());
        dialogueOptions.setOptions(storyline.getOptions(player));
        scene.setImage(storyline.getImage());

        // Melanie Lei
        while(true){
            try {Thread.sleep(20);} catch (InterruptedException e) {throw new RuntimeException(e);}
            // receives input from server
            if(input.ready()){
                String[] in = input.readLine().split(" ");
                if(in[0].equals("chat")){
                    //chat update
                    chatBox.sendEmote(in[1], in[2]);
                } else {
                    storyline.goNext(Integer.parseInt(in[0]));
                    scene.setDialogue(storyline.getDialogue());
                    dialogueOptions.setOptions(storyline.getOptions(player));
                    try {
                        scene.setImage(storyline.getImage());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            // draw antagonist notes
            if(!player.isProtagonist){
                antNotes.setText(storyline.getAntNotes());
            }
            // check if the plot point is end
            if(storyline.isEnd()){
                dialogueOptions.draw = false;
                chatBox.draw = false;
                antNotes.draw = false;
            }
            showForeground = storyline.showsForeground();
            frame.repaint();
        }
    }
    
    // Jaclyn Wang
    public class ActionsListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String s = e.getActionCommand();
            if (s.equals("Start")){
                frame.remove(startingScreen);
                frame.add(introScreen);
                frame.validate();
                frame.repaint();
            }
            if (s.equals("Play!")){
                player.name = textInput.getText();
                frame.remove(introScreen);
                frame.add(panel);
                frame.validate();
                frame.repaint();
            }
        }
    }
    
    // Jaclyn Wang
    public class MyMouseListener implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            if (dialogueOptions.clicked0(x, y)){
                output.println(player.isProtagonist + " 0");
            } else if(dialogueOptions.clicked1(x, y)){
                output.println(player.isProtagonist + " 1");
            } else if(chatBox.clickedGood(x, y)){
                output.println("chat good " + player.name);
            } else if(chatBox.clickedBad(x, y)){
                output.println("chat bad " + player.name);
            } else if(chatBox.clickedQuestion(x, y)){
                output.println("chat question " + player.name);
            }
            else{
                storyline.progressDialogue();
                scene.setDialogue(storyline.getDialogue());
                try {
                    scene.setImage(storyline.getImage());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            output.flush();
            // update dialogue and options
        }
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    }
    public void stop() throws Exception {
        input.close();
        output.close();
        clientSocket.close();
    }
}
