package me.ubmagh.springmulticonnectorms.exceptions;

public class AccountIdNotFoundException extends RuntimeException {

    private String accountId;

    public AccountIdNotFoundException(String accountId){
        super("Account With Id '"+accountId+"' was not found !");
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }
}
