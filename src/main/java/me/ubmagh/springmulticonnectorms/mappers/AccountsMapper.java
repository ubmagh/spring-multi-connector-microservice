package me.ubmagh.springmulticonnectorms.mappers;

import me.ubmagh.springmulticonnectorms.dtos.AccountRequestDTO;
import me.ubmagh.springmulticonnectorms.dtos.AccountResponseDTO;
import me.ubmagh.springmulticonnectorms.entities.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountsMapper  {

    AccountResponseDTO fromAccount(Account account);
    Account fromAccountRequestDTO(AccountRequestDTO accountRequestDTO);

}
