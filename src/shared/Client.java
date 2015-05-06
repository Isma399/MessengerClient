package shared;
import java.net.InetAddress;
import java.io.Serializable;

public class Client implements Serializable{
    private String login;
    private InetAddress ipAddress;
   
   public Client(String login,InetAddress ipAddress){this.login=login;this.ipAddress=ipAddress;}
        
       public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(InetAddress ipAddress) {
        this.ipAddress = ipAddress;
    }
     @Override
    public String toString(){
        return login + ipAddress;
    }
    
    
}
