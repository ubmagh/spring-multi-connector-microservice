package me.ubmagh.springmulticonnectorms.exceptions;

public class PasswordIncorrectException extends RuntimeException {


    public PasswordIncorrectException(){
        super("Incorrect password !");
    }

}
