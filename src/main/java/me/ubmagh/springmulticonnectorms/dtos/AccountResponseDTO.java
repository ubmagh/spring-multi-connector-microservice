package me.ubmagh.springmulticonnectorms.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.ubmagh.springmulticonnectorms.enums.AccountTypeEnum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountResponseDTO {

    private String id;

    private String avatar;
    private int profile_visits;

    private String username;

    private String email;
    private String website;
    private AccountTypeEnum account_type;

    private List<AccountResponseDTO> followings = new ArrayList<>();

    private Date created_at;
    private Date updated_at;
    private Date lastLogin;

    private boolean activated;
}
