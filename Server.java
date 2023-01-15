import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class Server {
    final int PORT = 5050;
    int playerCount = 0;
    ServerSocket serverSocket;
    Socket clientSocket;
    int clientCounter = 0;

    public static void main(String[] args) throws Exception{
        Server server = new Server();
        server.go();
    }

    public void go() throws Exception{
        //create a socket with the local IP address and wait for connection request       
        System.out.println("Waiting for a connection request from a client ...");
        serverSocket = new ServerSocket(PORT);                //create and bind a socket
        while(playerCount < 2) {
            clientSocket = serverSocket.accept();             //wait for connection request
            clientCounter = clientCounter +1;
            System.out.println("Client "+clientCounter+" connected");
            Thread connectionThread = new Thread(new ConnectionHandler(clientSocket));
            connectionThread.start();                         //start a new thread to handle the connection
            playerCount++;
        }
    }

    //------------------------------------------------------------------------------
    class ConnectionHandler extends Thread {
        Socket socket;
        PrintWriter output;
        BufferedReader input;
        String data;
        Queue<String> dataQ = new LinkedList<>();

        public ConnectionHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            
            while (true) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    //receive a message from the client
                    if (input.ready()) {
                        System.out.println("receiving data");
                        data = input.readLine();
                        dataQ.add(data);
                    }
                    output.println("hello!!!");
                    output.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
