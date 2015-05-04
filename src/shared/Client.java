package shared;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.Serializable;

public class Client implements Serializable{
    private String login;
    private InetAddress ipAddress;
   
    public Client(String login,InetAddress ipAddress){
        this.login=login;
        try {
            ipAddress = InetAddress.getLocalHost();
            this.ipAddress=ipAddress;
        } catch (UnknownHostException e){e.printStackTrace();}
    }
    
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
    
    
}
