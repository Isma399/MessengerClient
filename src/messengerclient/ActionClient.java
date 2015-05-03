package messengerclient;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ActionClient implements Runnable {
    private final Socket socket;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private Scanner scanner;
    private Thread thread3, thread4;
    
    public ActionClient (Socket socket){
        this.socket=socket;
    }
    @Override
    public void run() {
        try {
//            out = new PrintWriter(socket.getOutputStream());
//            
//            Thread thread4 = new Thread(new Emission(out));
//            thread4.start();
            
            //Message.setText(new PrintWriter(socket.getOutputStream()));
            ObjectOutputStream out2 = new ObjectOutputStream(socket.getOutputStream());
            
            Thread thread5 = new Thread(new EmissionObjet(out2));
            thread5.start();
            
//            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            scanner =new Scanner(System.in);
//            Thread thread3 = new Thread(new Reception(in));
//            thread3.start();
            
            ObjectInputStream in2 = new ObjectInputStream(socket.getInputStream());
            Thread thread6 = new Thread(new ReceptionObjet(in2));
            thread6.start();
        } catch (IOException e){
            System.err.println("Le serveur distant n'est plus accessible.");
        }
    }

}
