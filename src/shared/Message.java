package shared;
import java.io.Serializable;

public class Message implements Serializable {
   private static final long serialVersionUID = -20897833L;
   private Client client;
   private  String text;
    
   public Message(){} 
    public Message(Client client, String text){}
    
    //@Override
    //public String toString(){ return client.getLogin() + " : " + this.getText();}

    public Client getClient() {
        return client;
    }
   
    public void setClient(Client client) {
        this.client = client;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    @Override
    public String toString(){
        return client.getLogin() + " : "  + text;
    }
   
       
}
