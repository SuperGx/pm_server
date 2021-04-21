package com.cojo.passwordmanager;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

@Entity
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long uid;

    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @OneToMany(targetEntity=EncryptedData.class, cascade = CascadeType.ALL)
    private Set<EncryptedData> encryptedData;

    public UserData() {
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public Long getUid() {
        return uid;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<EncryptedData> getEncryptedData() {
        return encryptedData;
    }
    
  //getter and setter
}