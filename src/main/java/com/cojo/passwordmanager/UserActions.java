package com.cojo.passwordmanager;

import org.springframework.stereotype.Service;

import com.cojo.passwordmanager.util.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

@Service
public class UserActions implements UserActionsInterface {

    private UserDataRepository userDataRepository;

    @Autowired
    private UserTokenRepository userTokenRepository;

    @Autowired
    private EmailService emailService;

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
    @Override
    public Boolean resetPassword(String token, String newPassword) {
        

        UserToken userToken = userTokenRepository.findByResetToken(token);
        String email = userToken.getUserEmail();
        if(checkUserExists(email) && canResetPassword(userToken))
        {
            UserData my_user = userDataRepository.findByEmail(email);
            my_user.setPassword(newPassword);
            System.out.println("Savlez userul");
            return userDataRepository.save(my_user) != null? true : false;
        }
        return false;
    }
    @Override
    public boolean canResetPassword(UserToken userToken) {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public void sendMail(String email, String token) {
        // TODO Auto-generated method stub
        String appUrl = "localhost:8080";
        SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setFrom("cojo.intelligence@gmail.com");
			passwordResetEmail.setTo(email);
			passwordResetEmail.setSubject("Password Reset Request");
			passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
					+ "/reset-password?token=" + token);
			
            emailService.sendEmail(passwordResetEmail);

    }

}
