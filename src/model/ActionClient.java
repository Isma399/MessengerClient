package model;
import shared.Client;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.io.IOException;
import controller.State;

public class ActionClient implements Runnable {
    private final Socket socket;
    private Thread thread3, thread4;
    private final Client client;
    public State state;
            
    public ActionClient (Socket socket,Client client,State state){
        this.socket=socket;this.client=client;this.state=state;
    }
    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
           thread3 = new Thread(new EmissionObjet(out, client, state));
            thread3.start();
            
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            thread4 = new Thread(new ReceptionObjet(in));
            thread4.start();
        } catch (IOException e){
           // e.printStackTrace();
            System.err.println("Le serveur distant n'est plus accessible.");
        }
    }

}
