package com.cojo.passwordmanager;


public interface UserActionsInterface {
    public boolean checkUserExists(String email);

    public boolean register(UserData userData) throws UserAlreadyExistException;

    // public Boolean resetPassword();

    // public Boolean deleteUser();
}
