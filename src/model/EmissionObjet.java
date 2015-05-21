package model;
import shared.Message;
import shared.Client;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Scanner;
import java.util.Observer;
import controller.State;

public class EmissionObjet implements Runnable, Observer{
  
    public ObjectOutputStream out;
    private final Client client;
    public Message message;
    private Scanner scanner;
    private String text;
    public State state;
       
    public EmissionObjet(ObjectOutputStream out,Client client,State state){
        this.out=out;this.client=client;state.addObserver(this);
    }
@Override
public void run(){} 

    @Override
    public void update(Observable observable, Object o1) {
        if (observable instanceof State) {
            State value=(State) observable;
            text = value.getText();
            try{
                message = new Message(client,"");
                message.setClient(client);
                message.setText(text);
                out.writeObject(message);
                out.flush();
                out.reset();
            }catch(IOException e){e.printStackTrace();}
        }
    }
}
