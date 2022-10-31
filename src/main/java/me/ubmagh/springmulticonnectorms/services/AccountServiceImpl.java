package me.ubmagh.springmulticonnectorms.services;

import lombok.AllArgsConstructor;
import me.ubmagh.springmulticonnectorms.dtos.AccountRequestDTO;
import me.ubmagh.springmulticonnectorms.dtos.AccountResponseDTO;
import me.ubmagh.springmulticonnectorms.dtos.LoginRequest;
import me.ubmagh.springmulticonnectorms.entities.Account;
import me.ubmagh.springmulticonnectorms.enums.AccountTypeEnum;
import me.ubmagh.springmulticonnectorms.exceptions.AccountIdNotFoundException;
import me.ubmagh.springmulticonnectorms.exceptions.AccountUsernameNotFoundException;
import me.ubmagh.springmulticonnectorms.exceptions.PasswordIncorrectException;
import me.ubmagh.springmulticonnectorms.exceptions.UsernameAlreadyExistsException;
import me.ubmagh.springmulticonnectorms.mappers.AccountsMapper;
import me.ubmagh.springmulticonnectorms.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private AccountsMapper mapper;

    @Override
    public AccountResponseDTO getAccountById(String accountId) throws AccountIdNotFoundException {
        Account account = accountRepository.findById( accountId ).orElseThrow(() -> new AccountIdNotFoundException(accountId));
        account.setProfile_visits( account.getProfile_visits()+1 );
        return mapper.fromAccount(account);
    }

    @Override
    public AccountResponseDTO getAccountByUsername(String Username) throws AccountUsernameNotFoundException {
        Account account = accountRepository.findByUsername( Username ).orElseThrow(() -> new AccountUsernameNotFoundException(Username));
        account.setProfile_visits( account.getProfile_visits()+1 );
        return mapper.fromAccount(account);
    }

    @Override
    public List<AccountResponseDTO> getAccountsList() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(account -> mapper.fromAccount(account)).collect(Collectors.toList());
    }

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO) {
        Account account = mapper.fromAccountRequestDTO( accountRequestDTO );
        account.setId( UUID.randomUUID().toString() );
        account.setActivated(false);
        account.setCreated_at(new Date());
        accountRepository.save(account);
        return mapper.fromAccount(account);
    }

    @Override
    public AccountResponseDTO updateAccount( String accountId, AccountRequestDTO accountRequestDTO) throws AccountIdNotFoundException, PasswordIncorrectException {
        Account account = accountRepository.findById( accountId ).orElseThrow(() -> new AccountIdNotFoundException(accountId));
        Account check_exists = accountRepository.findByUsername( accountRequestDTO.getUsername() ).orElse(null);

        if( check_exists!=null && !check_exists.getId().equals(accountId)  )
            throw new UsernameAlreadyExistsException(accountRequestDTO.getUsername());

        if( !verifyPassword(account.getPassword().toString(), accountRequestDTO.getCurrent_password().toString()) )
            throw new PasswordIncorrectException();

        if( accountRequestDTO.getUsername()!=null ) account.setUsername( accountRequestDTO.getUsername() );
        if( accountRequestDTO.getWebsite()!=null ) account.setWebsite( accountRequestDTO.getWebsite() );
        if( accountRequestDTO.getPassword()!=null ) account.setPassword( accountRequestDTO.getPassword() );
        if( accountRequestDTO.getAvatar()!=null ) account.setAvatar( accountRequestDTO.getAvatar() );
        if( accountRequestDTO.getAccount_type()!=null ) account.setAccount_type( accountRequestDTO.getAccount_type() );
        if( accountRequestDTO.getEmail()!=null ) account.setEmail( accountRequestDTO.getEmail() );
        account.setUpdated_at(new Date());

        Account saved = accountRepository.save(account);
        return mapper.fromAccount(saved);
    }

    @Override
    public AccountResponseDTO deleteAccount(String accountId) throws AccountIdNotFoundException {
        Account account = accountRepository.findById( accountId ).orElseThrow(() -> new AccountIdNotFoundException(accountId));
        accountRepository.deleteById(accountId);
        return mapper.fromAccount(account);
    }

    @Override
    public List<AccountResponseDTO> toggleFollowAccount(String account1Id, String Account2Id) throws AccountIdNotFoundException {
        Account account1 = accountRepository.findById( account1Id ).orElseThrow(() -> new AccountIdNotFoundException(account1Id));
        Account account2 = accountRepository.findById( Account2Id ).orElseThrow(() -> new AccountIdNotFoundException(Account2Id));
        account1.getFollowings().add(account2);
        Account saved = accountRepository.save(account1);
        return saved.getFollowings().stream().map(account -> mapper.fromAccount(account)).collect(Collectors.toList());
    }

    @Override
    public AccountResponseDTO activateAccount(String accountId) throws AccountIdNotFoundException {
        Account account = accountRepository.findById( accountId ).orElseThrow(() -> new AccountIdNotFoundException(accountId));
        account.setActivated(true);
        Account saved = accountRepository.save(account);
        return mapper.fromAccount(saved);
    }

    @Override
    public AccountResponseDTO desactivateAccount(String accountId) throws AccountIdNotFoundException {
        Account account = accountRepository.findById( accountId ).orElseThrow(() -> new AccountIdNotFoundException(accountId));
        account.setActivated(false);
        Account saved = accountRepository.save(account);
        return mapper.fromAccount(saved);
    }

    @Override
    public List<String> accountTypes() {
        return Arrays.stream(AccountTypeEnum.values()).toList().stream().map(e->e.name()).collect(Collectors.toList());
    }

    @Override
    public AccountResponseDTO login(LoginRequest loginRequest) throws AccountUsernameNotFoundException, PasswordIncorrectException {
        Account account = accountRepository.findByUsername( loginRequest.getUsername() ).orElseThrow(() -> new AccountUsernameNotFoundException(loginRequest.getUsername()));
        if( !verifyPassword( account.getPassword().toString(), loginRequest.getPassword() ) )
            throw new PasswordIncorrectException();
        account.setLastLogin(new Date());
        Account saved = accountRepository.save(account);
        return mapper.fromAccount(saved);
    }

    @Override
    public boolean verifyPassword( String pwd1, String pwd2){
        return pwd1.equals(pwd2);
    }
}
