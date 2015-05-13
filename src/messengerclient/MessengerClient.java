package messengerclient;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import view.*;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;


public class MessengerClient implements ActionListener {
    
    public static Socket socket = null;
    public static Thread thread1;
        
    public static InetAddress whichIP(Scanner scanner)throws UnknownHostException{
        String ip = scanner.nextLine();
        InetAddress ipServer = InetAddress.getByName(ip);
        return ipServer;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new ViewClient();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
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
             if ("".equals(login)){view.ConnectScreen.alert.setText("\n Veuillez fournir un pseudo.");}
             else{
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