package com.cojo.passwordmanager;

public class UserAlreadyExistException extends Exception{

    UserAlreadyExistException(String s) {
        super(s);
    }

}
