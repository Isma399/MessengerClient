package view;

import controller.State;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class ConnectScreen extends JFrame implements Observer{
    JDialog connectScreen = new JDialog();
    public static JTextArea alert;
    public static JTextField ipServer ;
    public static JTextField portServer;
    public static JTextField userName;
    private final JButton connect,cancel;
    public static Socket socket = null;
  
public ConnectScreen(){
    setVisible(false);
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
        cancel = new JButton("Cancel");
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
        connectScreen.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
}
  public JButton getCancel() {return cancel;}
  public JButton getConnect() { return connect;}
  
  @Override
   public void update(Observable obs, Object arg) { // réaction au changement de modèle
        if (obs instanceof State) {
            State value=(State) obs;

        }
   }
}