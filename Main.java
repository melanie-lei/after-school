public class Main {
    public static void main(String[] args){
        System.out.println("hello ");
        
        Thread thread = new Thread(new Client());
        thread.start();
    }
}
