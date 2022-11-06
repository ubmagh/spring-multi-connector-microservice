package me.ubmagh.springmulticonnectorms.dtos;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.ubmagh.springmulticonnectorms.enums.AccountTypeEnum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Builder
@XmlRootElement(name = "AccountResponseDTO")
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

    public AccountResponseDTO() {
    }

    public AccountResponseDTO(String id, String avatar, int profile_visits, String username, String email, String website, AccountTypeEnum account_type, List<AccountResponseDTO> followings, Long created_at, Long updated_at, Long lastLogin, boolean activated) {
        this.id = id;
        this.avatar = avatar;
        this.profile_visits = profile_visits;
        this.username = username;
        this.email = email;
        this.website = website;
        this.account_type = account_type;
        this.followings = followings;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.lastLogin = lastLogin;
        this.activated = activated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getProfile_visits() {
        return profile_visits;
    }

    public void setProfile_visits(int profile_visits) {
        this.profile_visits = profile_visits;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public AccountTypeEnum getAccount_type() {
        return account_type;
    }

    public void setAccount_type(AccountTypeEnum account_type) {
        this.account_type = account_type;
    }

    public List<AccountResponseDTO> getFollowings() {
        return followings;
    }

    public void setFollowings(List<AccountResponseDTO> followings) {
        this.followings = followings;
    }

    public Long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Long created_at) {
        this.created_at = created_at;
    }

    public Long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Long updated_at) {
        this.updated_at = updated_at;
    }

    public Long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Long lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}
