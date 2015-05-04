package messengerclient;
import shared.Client;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ActionClient implements Runnable {
    private final Socket socket;
    private Scanner scanner;
    private Thread thread3, thread4;
    private Client client;
    
    public ActionClient (Socket socket,Client client){
        this.socket=socket;this.client=client;
    }
    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            Thread thread3 = new Thread(new EmissionObjet(out,client));
            thread3.start();
            
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Thread thread4 = new Thread(new ReceptionObjet(in));
            thread4.start();
        } catch (IOException e){
            System.err.println("Le serveur distant n'est plus accessible.");
        }
    }

}