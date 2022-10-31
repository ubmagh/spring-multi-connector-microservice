package me.ubmagh.springmulticonnectorms.web;

import lombok.AllArgsConstructor;
import me.ubmagh.springmulticonnectorms.dtos.AccountRequestDTO;
import me.ubmagh.springmulticonnectorms.dtos.AccountResponseDTO;
import me.ubmagh.springmulticonnectorms.dtos.LoginRequest;
import me.ubmagh.springmulticonnectorms.exceptions.AccountIdNotFoundException;
import me.ubmagh.springmulticonnectorms.exceptions.AccountUsernameNotFoundException;
import me.ubmagh.springmulticonnectorms.exceptions.PasswordIncorrectException;
import me.ubmagh.springmulticonnectorms.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("rest")
public class AccountController {

    private AccountService accountService;

    @GetMapping("/accounts")
    public List<AccountResponseDTO> getAcountsList(){
        return accountService.getAccountsList();
    }

    @GetMapping("/accounts/{accountId}")
    public AccountResponseDTO getAcount(@PathVariable("accountId") String accountId){
        AccountResponseDTO account=null;
        try{
            account = accountService.getAccountById(accountId);
        }catch (AccountIdNotFoundException exc){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, exc.getMessage() );
        }
        return account;
    }


    @GetMapping("/search-account/{accountId}")
    public AccountResponseDTO getAcountByUsername(@PathVariable("username") String username){
        AccountResponseDTO account=null;
        try{
            account = accountService.getAccountByUsername(username);
        }catch (AccountUsernameNotFoundException exc){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, exc.getMessage() );
        }
        return account;
    }


    @PostMapping("/accounts")
    public AccountResponseDTO createAccount(AccountRequestDTO request){
        return accountService.createAccount(request);
    }


    @PutMapping("/accounts/{accountId}")
    public AccountResponseDTO updateAccount( @PathVariable("accountId") String accountId, @RequestBody AccountRequestDTO requestDTO ){
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


    @DeleteMapping("/accounts/{accountId}")
    public AccountResponseDTO deleteAccount( @PathVariable("accountId") String accountId ){
        AccountResponseDTO account=null;
        try{
            account = accountService.deleteAccount( accountId );
        }catch (AccountIdNotFoundException exc){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, exc.getMessage() );
        }
        return account;
    }

    @GetMapping("/follow/{acc1}/{acc2}")
    public List<AccountResponseDTO> following( @PathVariable("acc1") String acc1, @PathVariable("acc2") String acc2 ){
        List<AccountResponseDTO> followings=null;
        try{
            followings = accountService.toggleFollowAccount( acc1, acc2);
        }catch (AccountIdNotFoundException exc){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, exc.getMessage() );
        }
        return followings;
    }


    @GetMapping("/account-activation/{accountId}/{activate}")
    public AccountResponseDTO activateAccount( @PathVariable("accountId") String accountId, @PathVariable("activate") String activate ){
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


    @GetMapping("/accounts-types")
    public List<String> getAccountsTypes(){
        return accountService.accountTypes();
    }


    @PostMapping("/account-login")
    public AccountResponseDTO loginAccount(@RequestBody LoginRequest loginRequest){
        AccountResponseDTO accountResponseDTO = null;
        try{
            accountResponseDTO = accountService.login(loginRequest);
        } catch ( Exception exc ){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, exc.getMessage() );
        }
        return accountResponseDTO;
    }

}
