import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class Client implements Runnable{
    final String LOCAL_HOST = "192.168.12.11";
    final int PORT = 5050;
    JFrame frame;
    JPanel panel;
    Socket clientSocket;
    PrintWriter output;
    BufferedReader input;
    String serverData;
    Storyline storyline = new Storyline();
    MyMouseListener mouseListener = new MyMouseListener();
    int weight;
    boolean mouseClicked;
    int currentDialogue = 0;
    Player player = new Player();
    ChatBox chatBox = new ChatBox();
    Scene scene = new Scene();
    @Override
    public void run() {
        Client client = new Client();
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
        System.out.println(input.readLine());
        if (mouseClicked = true) {
            output.println(weight);
            mouseClicked = false;
        }
        DialogueOptions dialogueOptions = new DialogueOptions();
        scene.setDialogue(storyline.getDialogue().get(currentDialogue).toString());
        if(player.isProtagonist){
            dialogueOptions.setOptionA(storyline.getProOptions().get(0).toString());
            dialogueOptions.setOptionB(storyline.getProOptions().get(1).toString());
        }
        else{
            dialogueOptions.setOptionA(storyline.getAntOptions().get(0).toString());
            dialogueOptions.setOptionB(storyline.getAntOptions().get(1).toString());
        }
        scene.setImage(storyline.getImage());
        //establish graphics panel
        frame = new JFrame("GraphicsDemo");
        panel = new GraphicsPanel(chatBox, dialogueOptions, scene);
        frame.getContentPane().setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setFocusable(true);
        panel.requestFocus();
        frame.add(panel);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.addMouseListener(mouseListener);
    }

    public class MyMouseListener implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            mouseClicked = true;
            if (e.getX() >= 100 && e.getX() <= 300 && e.getY() >= 300 && e.getY() <= 500){
                weight = 0;
                scene.setDialogue(storyline.getDialogue().get(currentDialogue).toString());
                storyline.goNext(weight);
                System.out.println(e.getX()+", "+e.getY());
            }
            else if(e.getX() >= 400 && e.getX() <= 600 && e.getY() >= 300 && e.getY() <= 500){
                weight = 1;
                scene.setDialogue(storyline.getDialogue().get(currentDialogue).toString());
                storyline.goNext(weight);
            }
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
