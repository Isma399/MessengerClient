package controller;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Observable;
import model.Connexion;
import static model.MessengerClient.socket;
import static model.MessengerClient.thread1;

public class State extends Observable{
    public Socket socket;
    public State(Socket socket){this.socket=socket;}
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
                    thread1 = new Thread(new Connexion(socket,login));
                    thread1.start();
                    view.ConnectScreen.alert.setText("Connexion etablie avec le serveur sur " + socket.getRemoteSocketAddress() );
                }catch(IOException ex){
                    view.ConnectScreen.alert.setText("Problème de connexion. Veuillez réessayer ultérieurement.");
                }
            }
        }
    }


