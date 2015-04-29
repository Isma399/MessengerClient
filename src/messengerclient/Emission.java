package messengerclient;
import java.io.PrintWriter;
import java.util.Scanner;


public class Emission implements Runnable {
    private final PrintWriter out;
    private final String login = null;
    private String message = null;
    private Scanner scanner = null;
    
    public Emission(PrintWriter out){
        this.out = out;
    }
    @Override
    public void run(){
              
        scanner = new Scanner (System.in);
        while(true){
            System.out.print("Message : ");
            message = scanner.nextLine();
            out.println(message);
            out.flush();
        } 
    }
}
