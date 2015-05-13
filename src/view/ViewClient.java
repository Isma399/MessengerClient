package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import messengerclient.EmissionObjet;

public class ViewClient extends JFrame{
   public static JTextArea chatWrite;
   private JButton sendButton;
    
   public ViewClient(){

    //Initial Setup
    super("NFP Messenger");
    setResizable(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800,600);
    setLocationRelativeTo(null);
    
    //Main Panels
    JPanel window = new JPanel(new BorderLayout());
    JPanel center = new JPanel(new BorderLayout());
    center.setBorder(BorderFactory.createLineBorder(Color.darkGray));
    
    JPanel left = new JPanel(new BorderLayout());
    left.setBorder(BorderFactory.createLoweredBevelBorder());

    //Panels
    JPanel display = new JPanel( new BorderLayout());
    
    Icon icon = new ImageIcon("wavy.gif");
       
    JPanel users = new JPanel(new BorderLayout());
    JScrollPane userList = new JScrollPane();
    userList.setPreferredSize(new Dimension(200, 800));
    userList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    
    userList.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 20, icon));
    userList.setBackground(Color.lightGray);
    users.add(userList);
    users.setBorder(BorderFactory.createLineBorder(Color.black));
    
    JPanel chat = new JPanel();
     chat.setLayout(new BoxLayout(chat, BoxLayout.Y_AXIS));
    chatWrite = new JTextArea();
    chatWrite.setBorder(BorderFactory.createMatteBorder( 20, 0, 0, 2, icon));
    
    sendButton = new JButton("Send");
    sendButton.addActionListener(new messengerclient.EmissionObjet());
    
    JPanel chatButton = new JPanel();
    chatButton.add(sendButton);
    chat.add(chatWrite);
    chat.add(chatButton);
    chat.setBorder(BorderFactory.createLineBorder(Color.black));

    //Menu bar
    JMenuBar menu = new JMenuBar();
        JMenu connection = new JMenu("Connection");
            JMenuItem connect = new JMenuItem("Connect");
            JMenuItem disconnect = new JMenuItem("Disconnect");
            JMenuItem exit = new JMenuItem("Exit");
            
    connection.add(connect);
    connection.add(disconnect);
    connection.add(exit);
    menu.add(connection);

    left.add(users);
    center.add(chat, BorderLayout.SOUTH);
    center.add(display, BorderLayout.CENTER);
    center.add(left, BorderLayout.WEST);
    window.add(center, BorderLayout.CENTER);
    
    window.add(menu, BorderLayout.NORTH);
    add(window);
    setVisible(true);

    //Listeners
    chatWrite.addKeyListener(new KeyLis());
    connect.addActionListener(new ActLis());
    exit.addActionListener(new ActLis());
    sendButton.addActionListener(new ActLis);
    
}

static class KeyLis implements KeyListener{

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            System.out.println("Message recieved.");
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }
}

static class ActLis implements ActionListener{
    public JTextField ipServer;
    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getActionCommand() == "Exit"){
        System.exit(0);
    } else if(e.getActionCommand() == "Connect"){
        ConnectScreen connectDialog = new ConnectScreen();
        connectDialog.setVisible(true);
    } else if(e.getActionCommand() == "Send"){
        
    }
}
}
}