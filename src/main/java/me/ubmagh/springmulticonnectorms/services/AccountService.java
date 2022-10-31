package me.ubmagh.springmulticonnectorms.services;

import me.ubmagh.springmulticonnectorms.dtos.AccountRequestDTO;
import me.ubmagh.springmulticonnectorms.dtos.AccountResponseDTO;
import me.ubmagh.springmulticonnectorms.dtos.LoginRequest;
import me.ubmagh.springmulticonnectorms.exceptions.AccountIdNotFoundException;
import me.ubmagh.springmulticonnectorms.exceptions.AccountUsernameNotFoundException;
import me.ubmagh.springmulticonnectorms.exceptions.PasswordIncorrectException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface AccountService {

    AccountResponseDTO getAccountById( String accountId) throws AccountIdNotFoundException;
    AccountResponseDTO getAccountByUsername( String Username) throws AccountUsernameNotFoundException;
    List<AccountResponseDTO> getAccountsList();

    AccountResponseDTO createAccount( AccountRequestDTO accountRequestDTO);
    AccountResponseDTO updateAccount( String accountId, AccountRequestDTO accountRequestDTO) throws AccountIdNotFoundException, PasswordIncorrectException;
    AccountResponseDTO deleteAccount( String accountId ) throws AccountIdNotFoundException;

    List<AccountResponseDTO> toggleFollowAccount( String account1Id, String Account2Id ) throws AccountIdNotFoundException;
    AccountResponseDTO activateAccount( String accountId) throws AccountIdNotFoundException;
    AccountResponseDTO desactivateAccount( String accountId) throws AccountIdNotFoundException;

    List<String> accountTypes();

    AccountResponseDTO login( LoginRequest loginRequest) throws AccountUsernameNotFoundException, PasswordIncorrectException;

    boolean verifyPassword( String pwd1, String pwd2);

}

