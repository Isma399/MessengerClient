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
import javax.swing.JTextArea;

import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import model.MessengerClient;


public class ConnectScreen extends JFrame{
  JDialog connectScreen = new JDialog();
  public static JTextArea alert;
  public static JTextField ipServer ;
  public static JTextField portServer;
  public static JTextField userName;
  private JButton connect,cancel;
  public static Socket socket = null;
  
  public ConnectScreen(){
        setSize(400, 300);
        setTitle("Connection");
        setLocationRelativeTo(null);
        
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
        
        portServer = new JTextField("5000");
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
        connect.addActionListener(new model.MessengerClient());
        cancel = new JButton("Cancel");
        cancel.addActionListener(new DialogListener());
        JPanel bottom = new JPanel();
        bottom.add(connect);
        bottom.add(cancel);
        container.add(bottom);
        
          alert = new JTextArea("");
        alert.setPreferredSize(new Dimension(375,100));
        JPanel bottom2 = new JPanel();
        bottom2.add(alert);
        container.add(bottom2);
        
        add(container);
        setVisible(true);
        connectScreen.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
}
  class DialogListener implements ActionListener{
      
    @Override
    public void actionPerformed(ActionEvent e){
        Object button = e.getSource();
            if (button==cancel){setVisible(false);dispose();}

    }
  }
  
}