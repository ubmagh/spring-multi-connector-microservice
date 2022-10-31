package me.ubmagh.springmulticonnectorms.mappers;

import me.ubmagh.springmulticonnectorms.dtos.AccountRequestDTO;
import me.ubmagh.springmulticonnectorms.dtos.AccountResponseDTO;
import me.ubmagh.springmulticonnectorms.entities.Account;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountsMapper  {

    AccountResponseDTO fromAccount(Account account);

    @Mapping(target = "password", ignore = true)
    Account fromAccountRequestDTO(AccountRequestDTO accountRequestDTO);

    @AfterMapping
    default void setPassword(@MappingTarget Account account, AccountRequestDTO accountRequestDTO) {
        String password = accountRequestDTO.getPassword();
        account.setPassword(password.toCharArray());
    }

}
