import javax.swing.*;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class Client implements Runnable{

    final String LOCAL_HOST = "192.168.0.109";
    final int PORT = 5000;
    JFrame frame;
    JPanel panel;
    Socket clientSocket;
    PrintWriter output;
    BufferedReader input;
    String serverData;
    @Override
    public void run() {
        Client client = new Client();
        try {
            client.localStart();
        } catch (Exception e) {throw new RuntimeException(e);}
        try {client.stop();} catch (Exception e) {throw new RuntimeException(e);}
    }

    public void localStart() throws Exception {
        
    }
    
    public void stop() throws Exception {
        input.close();
        output.close();
        clientSocket.close();
    }
}
