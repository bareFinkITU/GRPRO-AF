package Exceptions;
//TODO husk at lave en checked exception
public class invalidRegistration extends RuntimeException{

    public invalidRegistration(String msg){
        super(msg);
    }
}
