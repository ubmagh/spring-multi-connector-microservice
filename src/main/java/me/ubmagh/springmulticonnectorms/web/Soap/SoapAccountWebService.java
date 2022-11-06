package me.ubmagh.springmulticonnectorms.web.Soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.extern.slf4j.Slf4j;
import me.ubmagh.springmulticonnectorms.dtos.AccountRequestDTO;
import me.ubmagh.springmulticonnectorms.dtos.AccountResponseDTO;
import me.ubmagh.springmulticonnectorms.dtos.LoginRequest;
import me.ubmagh.springmulticonnectorms.exceptions.AccountIdNotFoundException;
import me.ubmagh.springmulticonnectorms.exceptions.AccountUsernameNotFoundException;
import me.ubmagh.springmulticonnectorms.exceptions.PasswordIncorrectException;
import me.ubmagh.springmulticonnectorms.exceptions.UsernameAlreadyExistsException;
import me.ubmagh.springmulticonnectorms.services.AccountService;

import java.util.List;

@WebService( name = "Accounts", serviceName = "AccountService" )
@Slf4j
public class SoapAccountWebService  {

    private AccountService accountService;

    public SoapAccountWebService(AccountService accountService ) {
        this.accountService = accountService;
    }

    public String hello (){
        return "Hello wolld !";
    }


    @WebMethod( operationName = "GetAccountById")
    public AccountResponseDTO getAccountById( @WebParam(name = "accountId") String accountId)  throws AccountIdNotFoundException {
        AccountResponseDTO account = this.accountService.getAccountById(accountId);
        return account;
    }


    @WebMethod( operationName = "GetAccountByUsername")
    public AccountResponseDTO getAccountByUsername( @WebParam(name = "Username") String Username) throws AccountUsernameNotFoundException {
        AccountResponseDTO account =  this.accountService.getAccountByUsername(Username);
        return account;
    }


    @WebMethod( operationName = "GetAccountsList")
    public List<AccountResponseDTO> getAccountsList() {
        List<AccountResponseDTO> accounts = accountService.getAccountsList();
        return accounts;
    }


    @WebMethod( operationName = "CreateAccount")
    public AccountResponseDTO createAccount( @WebParam(name = "accountRequestDTO") AccountRequestDTO accountRequestDTO) throws UsernameAlreadyExistsException {
        AccountResponseDTO responseDTO = accountService.createAccount(accountRequestDTO);
        return responseDTO;
    }

    
    @WebMethod( operationName = "UpdateAccount")
    public AccountResponseDTO updateAccount( @WebParam(name = "accountId") String accountId, @WebParam(name = "accountRequestDTO") AccountRequestDTO accountRequestDTO) throws PasswordIncorrectException, AccountIdNotFoundException {
        AccountResponseDTO account = accountService.updateAccount(accountId, accountRequestDTO);
        return account;
    }

    
    @WebMethod( operationName = "DeleteAccount")
    public AccountResponseDTO deleteAccount( @WebParam(name = "accountId") String accountId) throws AccountIdNotFoundException {
        AccountResponseDTO account = accountService.deleteAccount(accountId);
        return account;
    }

    
    @WebMethod( operationName = "ToggleFollowAccount")
    public List<AccountResponseDTO> toggleFollowAccount( @WebParam(name = "account1Id") String account1Id, @WebParam(name = "Account2Id") String Account2Id) throws AccountIdNotFoundException {
        List<AccountResponseDTO> accounts =  accountService.toggleFollowAccount( account1Id, Account2Id);
        return accounts;
    }

    
    @WebMethod( operationName = "ActivateAccount")
    public AccountResponseDTO activateAccount( @WebParam(name = "accountId") String accountId) throws AccountIdNotFoundException {
        AccountResponseDTO account = accountService.activateAccount( accountId);
        return account;
    }

    
    @WebMethod( operationName = "DesactivateAccount")
    public AccountResponseDTO desactivateAccount( @WebParam(name = "accountId") String accountId) throws AccountIdNotFoundException {
        AccountResponseDTO account = accountService.desactivateAccount( accountId);
        return account;
    }

    
    @WebMethod( operationName = "AccountTypes")
    public List<String> accountTypes() {
        return accountService.accountTypes();
    }

    
    @WebMethod( operationName = "Login")
    public AccountResponseDTO login( @WebParam(name = "loginRequest") LoginRequest loginRequest) throws PasswordIncorrectException, AccountUsernameNotFoundException {
        AccountResponseDTO account = accountService.login( loginRequest);
        return account;
    }


}
