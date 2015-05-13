package messengerclient;
import java.net.*;
import java.util.Scanner;
import java.io.*;
import shared.Client;

public class Connexion  implements Runnable{
    private  Socket socket = null;
    public static Thread thread2;
    public String login;
    private PrintWriter out;
    private BufferedReader in;
    private Scanner scanner = null;
    private boolean isConnected = false;
    public static Client client;
       
    public Connexion(Socket socket,String login){
        this.socket = socket;this.login=login;
    }
    @Override
    public void run(){
        try{
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                try{
                    out.println(login);
                    out.flush();
                    //String loginTest = in.readLine();
                    //System.err.println(loginTest);
                    while(isConnected!=true){  
                        if(in.readLine().equals("connecte")){
                            client = new Client(login);
                            
                            System.out.println("Reception du signal connecte.");
                            view.ConnectScreen.alert.setText("Pseudo OK.");
                            isConnected = true;
//                            thread2 = new Thread(new ActionClient(socket,client));
//                            thread2.start();
                            
                        } else if(in.readLine().equals("loginAlreadyUsed")){
                            view.ConnectScreen.alert.setText("Ce pseudo est déjà utilisé.");
                            isConnected = true;
                            //  socket.close();
                        } else {
                            
                            //System.out.println(in.getClass());
                        }
                         System.out.println("Sortie de boucle? : " + isConnected);
                    }
                   
                }catch(IOException ex){
                        view.ConnectScreen.alert.setText("Pertubations avec le serveur.");
                        }
            
        } catch (IOException e){
            view.ConnectScreen.alert.setText("Le serveur ne répond pas.");
        }
    }
}
 
