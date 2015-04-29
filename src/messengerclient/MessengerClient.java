package messengerclient;
import java.io.*;
import java.net.*;

public class MessengerClient {
    
    public static Socket socket = null;
    public static Thread thread1;

    public static void main(String[] args) {
        try {
            System.out.print("Demande de connexion | ");
            socket = new Socket("127.0.0.1",5000);//TODO IP externe, serveur distant
            System.out.println("Connexion etablie avec le serveur -> Authentification :  ");
            thread1 = new Thread(new Connexion(socket));
            thread1.start();
        } catch (UnknownHostException e){
            System.err.println("Impossible de se connecter à l'adresse " +  socket.getLocalAddress());
        } catch (IOException e){
            System.err.println("Aucun serveur à l'écoute du port : " + socket.getPort());
        }
    }
}