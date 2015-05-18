package controller;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import view.*;


public class View {
    private State state;
    public View(State state,Chat chat){
     this.state=state;  
//     chat.getSendButton().addActionListener(
//             new ActionListener() {
//                 @Override
//                 public void actionPerformed(ActionEvent se){
//                  //   View.this.state.sendText();
//                 }
//             });
    chat.getConnect().addActionListener((ActionEvent ee) -> {
        View.this.state.connect();
    });
    chat.getExit().addActionListener((ActionEvent ee) -> {
        View.this.state.exit();
    });
    chat.getDisconnect().addActionListener((ActionEvent ee) -> {
        View.this.state.disconnect();
    });
    chat.getConnectDialog().getCancel().addActionListener((ActionEvent ee) -> {
        View.this.state.cancel();
    });
    chat.getConnectDialog().getConnect().addActionListener((ActionEvent ee) -> {
        View.this.state.connectValidator();
    });
}






//@Override
//    public void actionPerformed(ActionEvent ae) {
//        Message message = new Message(client,"");
//        message.setClient(client);
//        message.setText(view.Chat.chatWrite.getText());
//        //String text = view.ViewClient.chatWrite.getText();
//        try {
//            out.writeObject(message);
//            out.flush();
//            out.reset();
//        } catch(IOException ex){
//            ex.printStackTrace();
//        }
//    }
    
}