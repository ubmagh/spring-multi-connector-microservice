package me.ubmagh.springmulticonnectorms.web;

import lombok.AllArgsConstructor;
import me.ubmagh.springmulticonnectorms.dtos.AccountRequestDTO;
import me.ubmagh.springmulticonnectorms.dtos.AccountResponseDTO;
import me.ubmagh.springmulticonnectorms.dtos.LoginRequest;
import me.ubmagh.springmulticonnectorms.exceptions.AccountIdNotFoundException;
import me.ubmagh.springmulticonnectorms.exceptions.AccountUsernameNotFoundException;
import me.ubmagh.springmulticonnectorms.exceptions.PasswordIncorrectException;
import me.ubmagh.springmulticonnectorms.services.AccountService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@AllArgsConstructor
public class AccountsQraphQlController {

    private AccountService accountService;

    @QueryMapping
    public List<AccountResponseDTO> getAcountsList(){
        return accountService.getAccountsList();
    }

    @QueryMapping
    public AccountResponseDTO getAcount( @Argument String accountId){
        AccountResponseDTO account=null;
        try{
            account = accountService.getAccountById(accountId);
        }catch (AccountIdNotFoundException exc){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, exc.getMessage() );
        }
        return account;
    }


    @QueryMapping
    public AccountResponseDTO getAcountByUsername(@Argument String username){
        AccountResponseDTO account=null;
        try{
            account = accountService.getAccountByUsername(username);
        }catch (AccountUsernameNotFoundException exc){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, exc.getMessage() );
        }
        return account;
    }


    @QueryMapping
    public AccountResponseDTO createAccount(@Argument AccountRequestDTO request){
        return accountService.createAccount(request);
    }


    @QueryMapping
    public AccountResponseDTO updateAccount( @Argument String accountId, @Argument AccountRequestDTO requestDTO ){
        AccountResponseDTO account=null;
        try{
            account = accountService.updateAccount( accountId, requestDTO);
        }catch (AccountIdNotFoundException exc){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, exc.getMessage() );
        }catch (PasswordIncorrectException exc){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, exc.getMessage() );
        }
        return account;
    }


    @QueryMapping
    public AccountResponseDTO deleteAccount( @Argument String accountId ){
        AccountResponseDTO account=null;
        try{
            account = accountService.deleteAccount( accountId );
        }catch (AccountIdNotFoundException exc){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, exc.getMessage() );
        }
        return account;
    }

    @QueryMapping
    public List<AccountResponseDTO> following( @Argument String acc1, @Argument String acc2 ){
        List<AccountResponseDTO> followings=null;
        try{
            followings = accountService.toggleFollowAccount( acc1, acc2);
        }catch (AccountIdNotFoundException exc){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, exc.getMessage() );
        }
        return followings;
    }


    @QueryMapping
    public AccountResponseDTO activateAccount( @Argument String accountId, @Argument String activate ){
        boolean do_activate = Integer.parseInt(activate)>0;
        AccountResponseDTO account=null;
        try{
            if( do_activate )
                account = accountService.activateAccount( accountId );
            else
                account = accountService.desactivateAccount( accountId );
        }catch (AccountIdNotFoundException exc){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, exc.getMessage() );
        }
        return account;
    }


    @QueryMapping
    public List<String> getAccountsTypes(){
        return accountService.accountTypes();
    }


    @QueryMapping
    public AccountResponseDTO loginAccount(@Argument LoginRequest loginRequest){
        AccountResponseDTO accountResponseDTO = null;
        try{
            accountResponseDTO = accountService.login(loginRequest);
        } catch ( Exception exc ){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, exc.getMessage() );
        }
        return accountResponseDTO;
    }

}
