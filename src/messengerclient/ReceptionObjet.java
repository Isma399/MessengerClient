package messengerclient;
import shared.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReceptionObjet implements Runnable{
    private final         ObjectInputStream in2; 
    private String message = null;
    public ReceptionObjet(ObjectInputStream in2){
        this.in2 = in2;
    }

    @Override
    public void run() {
        while (true){
            try{
                Object objectReceived = in2.readObject();
                if (objectReceived instanceof Message){
                Message message = (Message)objectReceived;
                System.out.println(message.getLogin() + " : " + message.getText());
                }else{System.out.println("Bad Object.");}                 
            } catch (IOException e){System.err.println(e);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ReceptionObjet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
