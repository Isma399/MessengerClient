package messengerclient;
import shared.Message;

import java.io.*;
import java.util.Scanner;

public class EmissionObjet implements Runnable{
   //Message message = new Message("yann","C'est un message de test depuis client.");
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
        while (true){
           // String text = scanner.nextLine();
            //message.setText("text de test");
            //message.setLogin(Connexion.login);
            Message message = new Message(Connexion.login, "test");
            out2.writeObject(message);
            out2.flush();
//            out2.reset();
            out2.close();
        }

    }catch(IOException e){e.printStackTrace();}
}
}
