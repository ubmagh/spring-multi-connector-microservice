package me.ubmagh.springmulticonnectorms.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.ubmagh.springmulticonnectorms.enums.AccountTypeEnum;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRequestDTO {

    private String avatar;

    private String username;
    private char[] password;

    private String email;
    private String website;
    private AccountTypeEnum account_type;

    private char[] current_password;

}
