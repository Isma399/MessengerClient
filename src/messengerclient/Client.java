package messengerclient;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client implements Runnable {
    private final Socket socket;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private Scanner scanner;
    private Thread thread3, thread4;
    
    public Client (Socket socket){
        this.socket=socket;
    }
    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            scanner =new Scanner(System.in);
            Thread thread4 = new Thread(new Emission(out));
            thread4.start();
            Thread thread3 = new Thread(new Reception(in));
            thread3.start();
        } catch (IOException e){
            System.err.println("Le serveur distant n'est plus accessible.");
        }
    }

}
