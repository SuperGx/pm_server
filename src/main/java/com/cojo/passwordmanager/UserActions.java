package com.cojo.passwordmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserActions implements UserActionsInterface{

    private UserDataRepository userDataRepository;
    @Override
    public boolean checkUserExists(String email) {
        return userDataRepository.findByEmail(email) != null? true : false;
    }
    @Override
    public boolean register(UserData userData) throws UserAlreadyExistException {
        if (checkUserExists(userData.getEmail())) {
            throw new UserAlreadyExistException("User already exists for this email");
        }
        // returns true or false if save is successful
        return userDataRepository.save(userData) !=null ? true : false;
    }

    public UserActions(UserDataRepository repository)
    {
        this.userDataRepository = repository;
    }

}
