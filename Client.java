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

public class Client implements Runnable{
    final String LOCAL_HOST = "192.168.2.21";
    final int PORT = 5050;
    JFrame frame;
    JPanel panel;
    JPanel startingScreen;
    JButton startingButton;
    JTextField playerInput;
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
    ActionListener startButtonListener;
    BufferedImage title;

    public Client() throws IOException {
    }

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
       
        // drawing everything of first scene
        scene.setDialogue(storyline.getDialogue());
        dialogueOptions.setOptions(storyline.getOptions(player));
        scene.setImage(storyline.getImage());
        
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
        
        JLabel title = new JLabel(new ImageIcon("images/yes.png"));
        //grah
        startingButton = new JButton("Start");
        startingScreen.setLayout(new FlowLayout());
        startButtonListener = new startButtonListener();
        startingButton.addActionListener(startButtonListener);
        startingScreen.add(startingButton);
        startingScreen.add(title);
        startingScreen.setBackground(Color.black);

        frame.add(startingScreen);
        
        frame.setVisible(true);
        frame.setResizable(false);
        panel.addMouseListener(mouseListener);
        
        player.name = playerCount;
        
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
                }
            }
            // draw antagonist notes
            if(!player.isProtagonist){
                antNotes.setText(storyline.getAntNotes());
            }
            
            if(storyline.isEnd()){
                dialogueOptions.draw = false;
                chatBox.draw = false;
                antNotes.draw = false;
            }
            frame.repaint();
        }
    }
    public class startButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            frame.remove(startingScreen);
            frame.add(panel);
            panel.setVisible(true);
            frame.validate();
            frame.repaint();
        }
    }
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
