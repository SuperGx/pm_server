package com.cojo.passwordmanager;

import com.cojo.passwordmanager.util.UserHelper;

import java.security.SecureRandom;

import javax.persistence.*;


@Entity
public class EncryptedData {
    @Lob
    String encrypted_data;
    Integer some_random_number;
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne(targetEntity = UserData.class, cascade= CascadeType.ALL)
    @JoinColumn(name = "email")
    private UserData userData;

    public EncryptedData(String encrypted_data) {
        some_random_number = generateRandomNumber();
        this.encrypted_data = encrypted_data;
    }

    public EncryptedData()
    {
    }

    public boolean verifyRequesterEmail() {
        UserHelper userHelper = new UserHelper();
        String loggedUserEmail = userHelper.getLoggedUserEmail();
        return loggedUserEmail.equals(userData.getEmail());
    }

    private Integer generateRandomNumber() {
        SecureRandom random = new SecureRandom();
        return Integer.valueOf(random.nextInt());
    }

    public String getEncrypted_data() {
        return encrypted_data;
    }

    public void setEncrypted_data(String encrypted_data) {
        this.encrypted_data = encrypted_data;
    }

    public Integer getSome_random_number() {
        return some_random_number;
    }

    public void setSome_random_number() {
        this.some_random_number = generateRandomNumber();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    
}