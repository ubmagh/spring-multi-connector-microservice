package me.ubmagh.springmulticonnectorms.exceptions;

public class AccountUsernameNotFoundException extends RuntimeException {

    private String username;

    public AccountUsernameNotFoundException(String username){
        super("Account With usnermae '"+username+"' was not found !");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
