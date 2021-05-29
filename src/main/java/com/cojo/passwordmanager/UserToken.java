package com.cojo.passwordmanager;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class UserToken {
    
    @ManyToOne(targetEntity = UserData.class, cascade= CascadeType.ALL)
    @JoinColumn(name = "email")
    private UserData userData;

    @Id
    private String userEmail;
    private String resetToken;
    private boolean enabled;
    private LocalDateTime createDate;

    public UserData getUserData() {
        return userData;
    }
    public void setUserData(UserData userData) {
        this.userData = userData;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String email) {
        this.userEmail = email;
    }
    public String getResetToken() {
        return resetToken;
    }
    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public UserToken() {
    }
    public LocalDateTime getCreateDate() {
        return createDate;
    }
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

}
