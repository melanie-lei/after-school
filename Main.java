import java.io.IOException;

// Melanie Lei
public class Main {
    public static void main(String[] args) throws IOException {
        for(int i = 0; i < 2; i++) {
            try {Thread.sleep(20);} catch (InterruptedException e) {throw new RuntimeException(e);}
            Thread thread = new Thread(new Client());
            thread.start();
        }
    }
}
