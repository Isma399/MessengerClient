package controller;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Observable;
import model.Connexion;
import model.EmissionObjet;
import static model.MessengerClient.thread1;
import shared.*;

public class State extends Observable{
    public Socket socket;
    public String text;
    public Client client;
    //public Message message = new Message(null,"");
    public State(Socket socket,String text,Client client){this.socket=socket;this.text=text;this.client=client;}
//    public State(Socket socket,Message message){
//        this.socket=socket;this.message=message;
//    }
     public void sendMessage(String text){
        this.text=text;
        view.Chat.setDisplayChat(client.getLogin() + " : "+ text);
        view.Chat.clearChatWrite();
        setChanged();notifyObservers();
    }
     public void setClient(Client client){
         this.client=client;
     }
     public String getText(){
         return text;
        // setChanged();notifyObservers();
     }
    public void exit(){
        System.exit(0);setChanged(); notifyObservers();
    }
    public void disconnect(){
        try{
            socket.close();
        }catch(IOException e){
           // e.printStackTrace();
        }
    }
    public void connect(){
        view.Chat.connectDialog.setVisible(true);
        setChanged(); notifyObservers();
    }
    public void cancel(){
        view.Chat.connectDialog.setVisible(false);
        view.Chat.connectDialog.dispose();
        setChanged();notifyObservers();
    }
    public void connectValidator(){
        boolean isValidIP = false;
        boolean isValidPort = false;
        String ipServer = view.ConnectScreen.ipServer.getText();
        InetAddress ip = null;
        String login = view.ConnectScreen.userName.getText();
        String error = "";
        int portServer = Integer.valueOf(view.ConnectScreen.portServer.getText());
        try{
            ip = InetAddress.getByName(ipServer);
            isValidIP  = true;
            view.ConnectScreen.alert.setText("Adresse OK.");
        }catch(IOException e){
           error ="Adresse erronée." + "\n";
        }
        if (portServer==5000){
            isValidPort = true;
        } else {
             error +=" Port non supporté actuellement.\n";
        }
        if (!(isValidIP) || !(isValidPort)){
            error+=" \n Veuillez vérifier les informations de connexions au serveur.";
            view.ConnectScreen.alert.setText(error);
        }else
            if ("".equals(login)){
                view.ConnectScreen.alert.setText("\n Veuillez fournir un pseudo.");
            }else{
                try{
                    socket = new Socket(ip,portServer);
                    //this.message.setClient(login);
                    thread1 = new Thread(new Connexion(socket,login,this));
                    thread1.start();
                    view.ConnectScreen.alert.setText("Connexion etablie avec le serveur sur " + socket.getRemoteSocketAddress() );
                    view.Chat.isEditableChatWrite(true);
                    view.Chat.clearChatWrite();
                }catch(IOException ex){
                    view.ConnectScreen.alert.setText("Problème de connexion. Veuillez réessayer ultérieurement.");
                }
            }
        }
   
    }


