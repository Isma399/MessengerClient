package messengerclient;
import java.net.*;
import java.util.Scanner;
import java.io.*;
import shared.Client;
import java.net.InetAddress;

public class Connexion  implements Runnable{
    private  Socket socket = null;
    public static Thread thread2;
    public static String login = null, message1 = null, message2 = null, message3 = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private Scanner scanner = null;
    private boolean isConnected = false;
    public static Client client;
       
    public Connexion(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run(){
        try{
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            scanner = new Scanner(System.in);
            
            
            while(!isConnected){
                System.out.println(in.readLine());
                login = scanner.nextLine();
                out.println(login);
                out.flush();
                
                if(in.readLine().equals("connecte")){
                    System.out.print(login + " connecté.");
                    client = new Client(login,InetAddress.getLocalHost());
                    isConnected = true;
                } else {
                    System.err.println("Ce login est déjà utilisé.");
                }
            }
            thread2 = new Thread(new ActionClient(socket));
            thread2.start();
        } catch (IOException e){
            System.err.println("Le serveur ne répond pas.");
        }
    }
}
