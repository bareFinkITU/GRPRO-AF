package UserMVC;

public class loginException extends RuntimeException {
    public loginException(String msg){
        super("The following username/e-mail doesn't exist: " + msg);
    }

    public loginException(){
        super("Wrong password");
    }
}
