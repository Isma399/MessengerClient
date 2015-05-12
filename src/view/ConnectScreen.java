package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class ConnectScreen extends JFrame{
  JDialog connectScreen = new JDialog();
  public JTextField ipServer ;
  public JFormattedTextField portServer;
  public JTextField userName;
  private JButton connect,cancel;
  public static Socket socket = null;
  
  public ConnectScreen(){
        setSize(300, 200);
        setTitle("Connection");
        
        JPanel container = new JPanel();
        
        ipServer = new JTextField("127.0.0.1");
        JLabel ipLabel = new JLabel("Adresse IP du serveur");
        JPanel top = new JPanel();
        ipServer.setPreferredSize(new Dimension(100, 20));
        ipLabel.setPreferredSize(new Dimension(130, 20));
        ipServer.setForeground(Color.BLUE);
        top.add(ipLabel);
        top.add(ipServer);
        container.add(top);
        
        portServer = new JFormattedTextField();
        portServer.setValue(new Integer("5000"));
        portServer.setPreferredSize(new Dimension(100, 20));
        JLabel portLabel = new JLabel("Port du serveur");
        portLabel.setPreferredSize(new Dimension(130, 20));
        JPanel middle1 = new JPanel();
        portServer.setForeground(Color.blue);
        middle1.add(portLabel);
        middle1.add(portServer);
        container.add(middle1);
        
        userName = new JTextField();
        userName.setPreferredSize(new Dimension(100, 20));
        JLabel userLabel = new JLabel("Pseudo");
        userLabel.setPreferredSize(new Dimension(130, 20));
        JPanel middle2 = new JPanel();
        middle2.add(userLabel);
        middle2.add(userName);
        container.add(middle2);
        
        connect = new JButton("Connect");
        connect.addActionListener(new DialogListener());
        cancel = new JButton("Cancel");
        cancel.addActionListener(new DialogListener());
        JPanel bottom = new JPanel();
        bottom.add(connect);
        bottom.add(cancel);
        container.add(bottom);
        
        add(container);
        setVisible(true);
        connectScreen.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
}
  class DialogListener implements ActionListener{
      
    @Override
    public void actionPerformed(ActionEvent e){
        Object button = e.getSource();
        if (button == connect){
            String user = userName.getText();
            int port = (int) portServer.getValue();
            InetAddress ip;
            try{
                ip = InetAddress.getByName(ipServer.getText()) ;
            }catch(UnknownHostException ex){
                System.err.println("Bad Address from ScreenConnect");
            }
            if (port!=5000){System.err.println("Bad Port. Only 5000 is allowed.");
            //return to JDialog...
            }
//            else {
//                try{
//                    socket = new Socket(ip,port);
//                } catch (UnknownHostException ex1){
//                    System.err.println("Impossible de se connecter à l'adresse " +  socket.getLocalAddress());
//                } 
//                catch (IOException ex2){
//                     System.err.println("Aucun serveur à l'écoute du port : " + socket.getPort());
//                }
            }
            else{setVisible(false);dispose();}
        }
    }
}