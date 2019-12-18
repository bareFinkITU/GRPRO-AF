package Exceptions;
public class invalidRegistration extends Exception{
    //checked exception
    public invalidRegistration(String msg){
        super(msg);
    }
    //referer til superklassens konstruktor
}
