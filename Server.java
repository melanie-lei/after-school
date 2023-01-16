import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Server {
    final int PORT = 5050;
    String[] choices = {"-1", "-1"};
    ServerSocket serverSocket;
    Socket clientSocket;
    int clientCounter = 0;
    ArrayList<ConnectionHandler> clients = new ArrayList<>();
    
    Storyline storyline = new Storyline();

    public static void main(String[] args) throws Exception{
        Server server = new Server();
        server.go();
    }

    public void go() throws Exception{
        //create a socket with the local IP address and wait for connection request       
        System.out.println("Waiting for a connection request from a client ...");
        serverSocket = new ServerSocket(PORT);                //create and bind a socket
        while(clientCounter < 2) {
            clientSocket = serverSocket.accept();             //wait for connection request
            clientCounter = clientCounter +1;
            System.out.println("Client "+clientCounter+" connected");
            ConnectionHandler connectionHandler = new ConnectionHandler(clientSocket, clientCounter);
            clients.add(connectionHandler);
            Thread connectionThread = new Thread(connectionHandler);
            connectionThread.start();   
        }
    }
    
    // sends data to all threads
    public void sendData(String data){
        for(ConnectionHandler client : clients){
            client.output.println(data);
        }
    }

    //------------------------------------------------------------------------------
    class ConnectionHandler extends Thread {
        Socket socket;
        PrintWriter output;
        BufferedReader input;
        int clientCount;
       
        

        public ConnectionHandler(Socket socket, int clientCount) {
            this.socket = socket;
            this.clientCount = clientCount;
        }

        public void run() {
            try {
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            output.println(clientCount);
            while (true) {
                try {Thread.sleep(20);} catch (InterruptedException e) {throw new RuntimeException(e);}
                try {
                    //receive a message from the client
                    if (input.ready()) {
                        System.out.println("receiving data");
                        String[] data = input.readLine().split(" ");
                        System.out.println(data[0]);
                        if(data[0].equals("chat")){
                            // chat things
                            sendData(String.join(" ", data));
                        } else {
                            if(data[0].equals("true")){ // protagonist
                                choices[0] = data[1];
                            } else {
                                choices[1] = data[1];
                            }
                        }
                    }
                    
                    // once two decisions are received, send data back
                    if(!(choices[0].equals("-1") || choices[1].equals("-1"))){
                        // check if the decision should be influenced by protag or antag
                        if(storyline.isProtagonistChoice()){
                            sendData(choices[0]);
                            storyline.goNext(Integer.parseInt(choices[0]));
                        } else {
                            sendData(choices[1]);
                            storyline.goNext(Integer.parseInt(choices[1]));
                        }
                        choices[0] = "-1";
                        choices [1] = "-1";
                    }
                    
                    output.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
