package messengerclient;
import shared.Message;
import shared.Client;

import java.io.*;
import java.util.Scanner;

public class EmissionObjet implements Runnable{
  
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
}
