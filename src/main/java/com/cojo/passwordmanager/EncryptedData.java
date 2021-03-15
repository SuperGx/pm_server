package com.cojo.passwordmanager;

import java.security.SecureRandom;

import javax.annotation.Generated;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.context.annotation.Role;

@Entity
public class EncryptedData {
    String encrypted_data;
    Integer some_random_number;
    Integer user_id;
    @Id
    @GeneratedValue
    Integer pwd_id;

    public EncryptedData(Integer user_id, String encrypted_data) {
        this.user_id = user_id;
        some_random_number = generateRandomNumber();
        this.encrypted_data = encrypted_data;
    }

    public EncryptedData()
    {
        
    }

    private Integer generateRandomNumber() {
        SecureRandom random = new SecureRandom();
        return random.nextInt();
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

    public void setSome_random_number(Integer some_random_number) {
        this.some_random_number = some_random_number;
    }

    public Integer getPwd_id() {
        return pwd_id;
    }

    public void setPwd_id(Integer pwd_id) {
        this.pwd_id = pwd_id;
    }
}