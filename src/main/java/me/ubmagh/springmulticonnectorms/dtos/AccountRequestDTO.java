package me.ubmagh.springmulticonnectorms.dtos;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.ubmagh.springmulticonnectorms.enums.AccountTypeEnum;


@Builder
@XmlRootElement(name = "AccountRequestDTO")
public class AccountRequestDTO {

    private String avatar;

    private String username;
    private String password;

    private String email;
    private String website;
    private AccountTypeEnum account_type;

    private String current_password;

    public AccountRequestDTO() {
    }

    public AccountRequestDTO(String avatar, String username, String password, String email, String website, AccountTypeEnum account_type, String current_password) {
        this.avatar = avatar;
        this.username = username;
        this.password = password;
        this.email = email;
        this.website = website;
        this.account_type = account_type;
        this.current_password = current_password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCurrent_password() {
        return current_password;
    }

    public void setCurrent_password(String current_password) {
        this.current_password = current_password;
    }
}
