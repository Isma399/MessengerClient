package messengerclient;
import shared.Message;
import shared.Client;

import java.io.*;
import java.util.Scanner;


public class EmissionObjet implements Runnable{
  
   public ObjectOutputStream out2;
   
   
   
    public EmissionObjet(ObjectOutputStream out2){
        this.out2=out2;
    }
@Override
public void run(){
    
//    Scanner scanner = new Scanner (System.in);
//    while(true){
//        String  text = scanner.nextLine();
//        Message.setText(text);
//    } 
    try{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Adresse IP " + Connexion.client.getIpAddress());
        while (true){
            Message message = new Message(Connexion.client,"");
            String text = scanner.nextLine();
                       
            message.setText(text);
            out2.writeObject(message);
            out2.flush();
//            out2.reset();

        }

    }catch(IOException e){e.printStackTrace();}
    finally{
        try{ 
            out2.close();
        }catch(IOException e){e.printStackTrace();}}
}
}
