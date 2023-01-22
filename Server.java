import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// Melanie Lei
public class Server {
    final int PORT = 5050;
    String[] choices = {"-1", "-1"};
    ServerSocket serverSocket;
    Socket clientSocket;
    int clientCounter = 0;
    ArrayList<ConnectionHandler> clients = new ArrayList<>();
    Storyline storyline;
    
    {try {storyline = new Storyline();} catch (FileNotFoundException e) {throw new RuntimeException(e);}}


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
            // create input and output readers for sending data
            try {
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // tell client which number they are
            output.println(clientCount);
            
            while (true) {
                try {Thread.sleep(10);} catch (InterruptedException e) {throw new RuntimeException(e);}
                try {
                    //receive a message from the client
                    if (input.ready()) {
                        System.out.println("receiving data");
                        String[] data = input.readLine().split(" ");
                        
                        if(data[0].equals("chat")){ // chat updates
                            sendData(String.join(" ", data)); // send updated data to both clients
                        } else {
                            if(data[0].equals("true")){ // data from protagonist
                                choices[0] = data[1];
                            } else {
                                choices[1] = data[1];
                            }
                        }
                    }
                    
                    // once two decisions are received, send data back
                    if(!(choices[0].equals("-1") || choices[1].equals("-1"))){
                        // check if the decision should be influenced by protag or antag then sends decision back
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
