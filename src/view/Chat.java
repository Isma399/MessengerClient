package view;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import javax.swing.*;
import java.util.Observer;
import controller.State;

public class Chat extends JFrame implements Observer{
   public static ConnectScreen connectDialog = new ConnectScreen();
   private JButton sendButton;
   private  static JTextArea userList,chatWrite;
   private static JTextArea chatDisplay = new JTextArea ("");
   private JMenuItem connect,disconnect,exit;
    
   public Chat(State state){
    super("NFP Messenger");
    initWindow();
    state.addObserver(this);
    pack();
    setVisible(true);
   }
   private void initWindow(){ 
    //Le serveur démarre sur la gauche de l'écran. On fait démarrer le client sur la droite.
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - 750;
        setLocation(x, 0);
           
    setResizable(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800,600);
    
    
    //Main Panels
    JPanel window = new JPanel(new BorderLayout());
    JPanel center = new JPanel(new BorderLayout());
    JPanel left = new JPanel(new BorderLayout());
    left.setBorder(BorderFactory.createLoweredBevelBorder());
    center.setBorder(BorderFactory.createLineBorder(Color.darkGray));
    
    //Chat Display
    JPanel display = new JPanel( new BorderLayout());
    chatDisplay.setBackground(Color.orange);
    chatDisplay.setPreferredSize(new Dimension(500, 300));
    display.add(chatDisplay);
    
    //UserList
    Icon icon = new ImageIcon("wavy.gif");
    JPanel users = new JPanel(new BorderLayout());
    userList = new JTextArea();
    userList.setEditable(false);
    userList.setPreferredSize(new Dimension(200, 300));
    userList.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 20, icon));
    userList.setBackground(Color.lightGray);
    users.add(userList);
    users.setBorder(BorderFactory.createLineBorder(Color.black));
    
    //ChatWriter
    JPanel chat = new JPanel();
     chat.setLayout(new BoxLayout(chat, BoxLayout.Y_AXIS));
    chatWrite = new JTextArea("Veuillez-vous connecter.");
    chatWrite.setEditable(false);
    chatWrite.setBorder(BorderFactory.createMatteBorder( 20, 0, 0, 2, icon));
    sendButton = new JButton("Send");
    JPanel chatButton = new JPanel();
    chatButton.add(sendButton);
    chat.add(chatWrite);
    chat.add(chatButton);
    chat.setBorder(BorderFactory.createLineBorder(Color.black));

    //Menu bar
    JMenuBar menu = new JMenuBar();
    JMenu connection = new JMenu("Connection");
        connect = new JMenuItem("Connect");
        disconnect = new JMenuItem("Disconnect");
        exit = new JMenuItem("Exit");
    connection.add(connect);
    connection.add(disconnect);
    connection.add(exit);
    menu.add(connection);

    //Adding panel to window
    left.add(users);
    center.add(chat, BorderLayout.SOUTH);
    center.add(display, BorderLayout.CENTER);
    center.add(left, BorderLayout.WEST);
    window.add(center, BorderLayout.CENTER);
    
    window.add(menu, BorderLayout.NORTH);
    add(window);
    setVisible(true);

//    //Listeners
//    chatWrite.addKeyListener(new KeyLis());
 //   connect.addActionListener(new Action());
    //exit.addActionListener(new Action());
//    sendButton.addActionListener(new Action());
    
    }
   
   public JButton getSendButton() {return sendButton;}
   public String getChatWrite() {return chatWrite.getText();}
   public JMenuItem getExit() {return exit;}
   public JMenuItem getDisconnect() {return disconnect;}
   public JMenuItem getConnect() {return connect;}
   public ConnectScreen getConnectDialog(){return connectDialog;}
   public static void setUserList ( String list){
       userList.setText(list);
   }
   public static void setDisplayChatGray(String text){
       String s = chatDisplay.getText();
       chatDisplay.setText(s + "\n" + text); chatDisplay.setForeground(Color.gray);
   }
    public  static void setDisplayChat(String text){
       String s = chatDisplay.getText();
       chatDisplay.setText(s + "\n" + text); chatDisplay.setForeground(Color.black);
   } 
    public static void clearChatWrite(){
        chatWrite.setText("");
    }
    public static void isEditableChatWrite(boolean b){
        chatWrite.setEditable(b);
    }
   @Override
   public void update(Observable obs, Object arg) { // réaction au changement de modèle
        if (obs instanceof State) {
            State value=(State) obs;
        }
    }
}
