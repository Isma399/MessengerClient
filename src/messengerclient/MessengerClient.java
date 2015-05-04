package messengerclient;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MessengerClient {
    
    public static Socket socket = null;
    public static Thread thread1;
    
   
    
    public static InetAddress whichIP(Scanner scanner)throws UnknownHostException{
        String ip = scanner.nextLine();
        InetAddress ipServer = InetAddress.getByName(ip);
        return ipServer;
    }

    public static void main(String[] args) {
        
        try {
            System.out.println("Quel serveur voulez-vous contacter? Ip localhost = 127.0.0.1.");
            Scanner scanner = new Scanner(System.in);
            socket = new Socket( whichIP(scanner),5000);
                      
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