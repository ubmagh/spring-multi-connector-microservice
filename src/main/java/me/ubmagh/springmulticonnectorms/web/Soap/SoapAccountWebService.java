package me.ubmagh.springmulticonnectorms.web.Soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebEndpoint;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ubmagh.springmulticonnectorms.dtos.AccountResponseDTO;
import me.ubmagh.springmulticonnectorms.exceptions.AccountIdNotFoundException;
import me.ubmagh.springmulticonnectorms.exceptions.AccountUsernameNotFoundException;
import me.ubmagh.springmulticonnectorms.exceptions.PasswordIncorrectException;
import me.ubmagh.springmulticonnectorms.exceptions.UsernameAlreadyExistsException;
import me.ubmagh.springmulticonnectorms.services.AccountService;

import java.util.List;

//@WebService(name = "AccountsService", targetNamespace = "http://github.com/ubmagh")
@WebService( name = "Accounts", serviceName = "AccountService" )
@Slf4j
public class SoapAccountWebService /*implements AccountService*/ {

    private AccountService accountService;

    public SoapAccountWebService(AccountService accountService ) {
        this.accountService = accountService;
    }

    public String hello (){
        log.info("==============================>");
        return "Hello wolld:!";
    }

    @WebMethod( operationName = "GetAccountById")
    public AccountResponseDTO getAccountById( @WebParam(name = "accountId") String accountId)  throws AccountIdNotFoundException {
        log.info("==============================>2");
        AccountResponseDTO account = null;
        account = this.accountService.getAccountById(accountId);
        return account;
    }


    /*

    @WebMethod( operationName = "GetAccountByUsername")
    public AccountResponseDTO getAccountByUsername( @WebParam(name = "Username") String Username)  {
        AccountResponseDTO account = null;
        try{
            account = this.accountService.getAccountByUsername(Username);
        }catch (AccountUsernameNotFoundException exc){

        }
        return account;
    }


    @WebMethod( operationName = "GetAccountsList")
    public List<AccountResponseDTO> getAccountsList() {
        List<AccountResponseDTO> accounts = accountService.getAccountsList();
        return accounts;
    }


    @WebMethod( operationName = "CreateAccount")
    public AccountResponseDTO createAccount( @WebParam(name = "accountRequestDTO") AccountRequestDTO accountRequestDTO) {
        AccountResponseDTO responseDTO = null;
        try{
            responseDTO = accountService.createAccount(accountRequestDTO);
        }catch (UsernameAlreadyExistsException exc){

        }
        return responseDTO;
    }

    
    @WebMethod( operationName = "UpdateAccount")
    public AccountResponseDTO updateAccount( @WebParam(name = "accountId") String accountId, @WebParam(name = "accountRequestDTO") AccountRequestDTO accountRequestDTO) {
        AccountResponseDTO account = null;
        try{
            account = accountService.updateAccount(accountId, accountRequestDTO); // # todo
        }catch (AccountIdNotFoundException exc){

        } catch ( PasswordIncorrectException exc) {

        }
        return account;
    }

    
    @WebMethod( operationName = "DeleteAccount")
    public AccountResponseDTO deleteAccount( @WebParam(name = "accountId") String accountId) {
        AccountResponseDTO account = null;
        try{
            account = accountService.deleteAccount(accountId); // # todo
        }catch (AccountIdNotFoundException exc){

        }
        return account;
    }

    
    @WebMethod( operationName = "ToggleFollowAccount")
    public List<AccountResponseDTO> toggleFollowAccount( @WebParam(name = "account1Id") String account1Id, @WebParam(name = "Account2Id") String Account2Id) {
        List<AccountResponseDTO> accounts = null;
        try{
            accounts = accountService.toggleFollowAccount( account1Id, Account2Id); // # todo
        }catch (AccountIdNotFoundException exc){

        }
        return accounts;
    }

    
    @WebMethod( operationName = "ActivateAccount")
    public AccountResponseDTO activateAccount( @WebParam(name = "accountId") String accountId) {
        AccountResponseDTO account = null;
        try{
            account = accountService.activateAccount( accountId); // # todo
        }catch (AccountIdNotFoundException exc){

        }
        return account;
    }

    
    @WebMethod( operationName = "DesactivateAccount")
    public AccountResponseDTO desactivateAccount( @WebParam(name = "accountId") String accountId) {
        AccountResponseDTO account = null;
        try{
            account = accountService.desactivateAccount( accountId); // # todo
        }catch (AccountIdNotFoundException exc){

        }
        return account;
    }

    
    @WebMethod( operationName = "AccountTypes")
    public List<String> accountTypes() {
        return accountService.accountTypes();
    }

    
    @WebMethod( operationName = "Login")
    public AccountResponseDTO login( @WebParam(name = "loginRequest") LoginRequest loginRequest) {
        AccountResponseDTO account = null;
        try{
            account = accountService.login( loginRequest); // # todo
        }catch (AccountUsernameNotFoundException exc){

        }catch (PasswordIncorrectException exc){

        }
        return account;
    }

     */

}
