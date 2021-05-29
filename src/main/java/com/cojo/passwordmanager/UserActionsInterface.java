package com.cojo.passwordmanager;


public interface UserActionsInterface {
    public boolean checkUserExists(String email);

    public boolean canResetPassword(UserToken userToken);

    public boolean register(UserData userData) throws UserAlreadyExistException;

    public Boolean resetPassword(String Token, String newPassword);

    public void sendMail(String email, String token);

    // public Boolean deleteUser();
}
