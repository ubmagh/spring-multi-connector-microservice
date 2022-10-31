package me.ubmagh.springmulticonnectorms.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {

    private final String username;

    public UsernameAlreadyExistsException(String username){
        super("The username '"+username+"' Already exists !");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
