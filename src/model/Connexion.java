package model;
import java.net.*;
import java.io.*;
import shared.Client;
import controller.State;


public class Connexion  implements Runnable{
    private  Socket socket = null;
    public static Thread thread2;
    public String login;
    private PrintWriter out;
    private BufferedReader in;
    private boolean isConnected = false;
    public static Client client;
    public State state;
           
    public Connexion(Socket socket,String login,State state){
        this.socket = socket;this.login=login;this.state=state;
    }
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
//                                state.message.setClient(client);
                                view.ConnectScreen.alert.setText("Pseudo OK.");
                                isConnected = true;
                                view.Chat.connectDialog.setVisible(false);
                                view.Chat.connectDialog.dispose();
                            thread2 = new Thread(new ActionClient(socket,client,state));
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
 
