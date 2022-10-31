package me.ubmagh.springmulticonnectorms.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.ubmagh.springmulticonnectorms.enums.AccountTypeEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "app_accounts")
public class Account {

    @Id
    private String id;

    private String avatar;
    private int profile_visits;

    @Column(unique = true, nullable = false)
    private String username;
    @Transient
    private char[] password;

    private String email;
    private String website;
    @Enumerated(EnumType.STRING)
    private AccountTypeEnum account_type;

    @ManyToMany()
    private List<Account> followings = new ArrayList<>();

    private Long created_at;
    private Long updated_at;
    private Long lastLogin;

    private boolean activated;
}
