import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class Server {
    final int PORT = 5050;

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
        while(true) {
            clientSocket = serverSocket.accept();             //wait for connection request
            clientCounter = clientCounter +1;
            System.out.println("Client "+clientCounter+" connected");
            Thread connectionThread = new Thread(new ConnectionHandler(clientSocket));
            connectionThread.start();                         //start a new thread to handle the connection


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
                    output.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
