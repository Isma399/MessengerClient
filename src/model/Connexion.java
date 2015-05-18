package model;
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
//    public void close(){
//        try{
//        socket.close();
//        }catch(IOException e){
//            System.out.println("Vous avez bien été déconecté.");
//        }
//    }
    @Override
    public void run(){
        try{
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                try{
                    out.println(login);
                    out.flush();
                    while(isConnected!=true){  
                        switch (in.readLine()) {
                            case "connecte":
                                client = new Client(login);
                                view.ConnectScreen.alert.setText("Pseudo OK.");
                                isConnected = true;
                                view.Chat.connectDialog.setVisible(false);
                                view.Chat.connectDialog.dispose();
                            thread2 = new Thread(new ActionClient(socket,client));
                            thread2.start();
                                break;
                            case "loginAlreadyUsed":
                                view.ConnectScreen.alert.setText("Ce pseudo est déjà utilisé.");
                                break;
                            default:
                                break;
                        }
                    }
                }catch(IOException ex){
                        view.ConnectScreen.alert.setText("Pertubations avec le serveur.");
                }
        } catch (IOException e){
            view.ConnectScreen.alert.setText("Le serveur ne répond pas.");
        }
    }
}
 
