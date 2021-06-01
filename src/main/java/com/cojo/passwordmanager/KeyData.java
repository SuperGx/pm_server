package com.cojo.passwordmanager;

import javax.persistence.*;

@Entity
public class KeyData {

    @OneToOne(targetEntity = UserData.class, cascade= CascadeType.ALL)
    @JoinColumn(name = "email")
    private UserData userData;

    @Id
    @GeneratedValue
    private Long id;
    private String keymail;
    private String mfaKey;

    public KeyData() {
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeymail() {
        return keymail;
    }

    public void setKeymail(String keymail) {
        this.keymail = keymail;
    }

    public String getMfaKey() {
        return mfaKey;
    }

    public void setMfaKey(String mfaKey) {
        this.mfaKey = mfaKey;
    }
}
