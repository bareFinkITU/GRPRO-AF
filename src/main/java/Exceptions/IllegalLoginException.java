package Exceptions;

public class IllegalLoginException extends RuntimeException { //unchecked exception
    public IllegalLoginException(String msg){
        super(msg);
    } //henter superklassens konstruktor
    //som er RuntimeExceptions

    public IllegalLoginException(){
        super("Wrong password");
    }
    //tom konstruktor, som blot printer "Wrong password" ud
}
