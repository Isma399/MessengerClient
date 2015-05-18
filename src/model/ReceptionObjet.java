package model;
import shared.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReceptionObjet implements Runnable {
    private final         ObjectInputStream in; 
    private String message = null;
    public ReceptionObjet(ObjectInputStream in){
        this.in = in;
    }

    @Override
    public void run() {
        boolean isConnected = true;
        while (isConnected){
            try{
                Object objectReceived = in.readObject();
                if (objectReceived instanceof Message){
                Message message = (Message)objectReceived;
                    switch (message.getClient().toString()) {
                        case "server":
                            view.Chat.setUserList(message.getText());
                            break;
                        case "welcomeserver":
                            view.Chat.setDisplayChatGray("\t--- " + message.getText());
                            break;
                        default:
                            view.Chat.setDisplayChat(message.toString());
                            break;
                    }
                }else{System.out.println("Bad Object.");}                 
            } catch (IOException e){
                System.out.println("TODO : deconnexion du serveur");
                isConnected=false;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ReceptionObjet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
