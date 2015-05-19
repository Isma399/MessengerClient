package model;
import java.net.*;
import view.Chat;
import javax.swing.SwingUtilities;
import controller.State;
import controller.View;

public class MessengerClient  {
  
    public static Socket socket = null;
    public static Thread thread1;
    public static View controllerView;

    public static void main(String[] args) {
        State state= new State(socket,"");
        SwingUtilities.invokeLater(() -> {
            Chat chat = new Chat(state);
            controllerView = new View(state, chat);
        });
    }
}
