package UserMVC;

public class IllegalLoginException extends RuntimeException {
    public IllegalLoginException(String msg){
        super(msg);
    }

    public IllegalLoginException(){
        super("Wrong password");
    }
}
