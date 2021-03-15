package com.cojo.passwordmanager;

import java.security.SecureRandom;

import org.apache.tomcat.util.codec.binary.Base64;

public class EncryptedPasswords {
    String encrypted_passwords;
    int some_random_number;
    int user_id;

    public EncryptedPasswords(int user_id)
    {
        this.user_id = user_id;
        encrypted_passwords = Base64.encodeBase64String(select_user_passwords());
        some_random_number = generateRandomNumber();
    }

    private byte[] select_user_passwords()
    {
        if (user_id > 10)
            return new byte[] { 0, 1, 2, 3 };
        else
            return new byte[] { 4, 5, 6 };
    }

    private int generateRandomNumber()
    {
        SecureRandom random = new SecureRandom();
        return random.nextInt();
    }

    public String getEncrypted_passwords() {
        return encrypted_passwords;
    }

    public int getSome_random_number() {
        return some_random_number;
    }

}
