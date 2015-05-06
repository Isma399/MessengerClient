package messengerclient;
import shared.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReceptionObjet implements Runnable{
    private final         ObjectInputStream in; 
    private String message = null;
    public ReceptionObjet(ObjectInputStream in){
        this.in = in;
    }

    @Override
    public void run() {
        while (true){
            try{
                Object objectReceived = in.readObject();
                if (objectReceived instanceof Message){
                Message message = (Message)objectReceived;
                System.out.println(message.getText());
                }else{System.out.println("Bad Object.");}                 
            } catch (IOException e){System.err.println(e);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ReceptionObjet.class.getName()).log(Level.SEVERE, null, ex);
            }
            //finally{try{in.reset();}catch(IOException ex){ex.printStackTrace();}}
            //finally{try{in.close();}catch(IOException ex){ex.printStackTrace();}}
        }
    }
}
