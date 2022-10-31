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

    private Long created_at;
    private Long updated_at;
    private Long lastLogin;

    private boolean activated;
}
