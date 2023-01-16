import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("hello ");
        for(int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Client());
            thread.start();
        }
    }
}
