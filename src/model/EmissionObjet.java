package model;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import shared.*;
import view.ViewClient;
import java.io.*;
import java.util.Scanner;

public class EmissionObjet implements ActionListener,Runnable{
  
    public ObjectOutputStream out;
    private final Client client;
         
    public EmissionObjet(ObjectOutputStream out,Client client){
        this.out=out;this.client=client;
    }
@Override
public void run(){
  
    try{
        Scanner scanner = new Scanner(System.in);
        while (true){
            Message message = new Message(client,"");
            String text = scanner.nextLine();
            message.setClient(client);
            message.setText(text);
            out.writeObject(message);
            out.flush();
            out.reset();
        }
    }catch(IOException e){e.printStackTrace();}
    finally{
        try{ 
            out.close();
        }catch(IOException e){e.printStackTrace();}}
}

    @Override
    public void actionPerformed(ActionEvent ae) {
        Message message = new Message(client,"");
        message.setClient(client);
        message.setText(view.ViewClient.chatWrite.getText());
        //String text = view.ViewClient.chatWrite.getText();
        try {
            out.writeObject(message);
            out.flush();
            out.reset();
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
