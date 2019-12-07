package UserMVC;

public class loginException extends RuntimeException {
    public loginException(String msg){
        super(msg);
    }

    public loginException(){
        super("Wrong password");
    }
}
