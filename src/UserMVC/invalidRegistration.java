package UserMVC;

public class invalidRegistration extends RuntimeException{

    public invalidRegistration(String msg){
        super(msg);
    }
}
