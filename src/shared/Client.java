package shared;
import java.io.Serializable;

public class Client implements Serializable{
    private static final long serialVersionUID = 9069032088387632208L;
    private String login;
    public Client(){}
    public Client(String login){this.login=login;}
    public String getLogin() {return login;}
    public void setLogin(String login) {this.login = login;}
    @Override
    public String toString(){return login;}
}
