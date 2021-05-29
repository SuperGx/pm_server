package com.cojo.passwordmanager;


import java.time.LocalDateTime;
import java.util.UUID;

import com.cojo.passwordmanager.util.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class UserController {

    @Autowired
    private UserDataRepository repository;

    @Autowired
    private UserActions uActions;

    @Autowired
    private UserTokenRepository userTokenRepository;

    @Autowired
    private Response response;

    private UserToken userToken;

    @PostMapping("/register")
    ResponseEntity<?> registerUser(@RequestBody UserData userData) {
        try {
            if (uActions.register(userData)) {
                response.setMessage("User Created!");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                response.setMessage("User not saved!");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (UserAlreadyExistException e) {
            e.printStackTrace();
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/reset-password")
    ResponseEntity<String> resetPassword(@RequestParam(value = "token") String token, @RequestBody String password ) {
        if (uActions.resetPassword(token, password)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Password reseted successfuly!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password not reseted!");
        }
    }

    @PostMapping("/forgot-password")
    ResponseEntity<String> forgotPassword(@RequestParam(value = "email") String email)
    { 
        String myToken = UUID.randomUUID().toString();
        userToken = new UserToken();
        userToken.setUserEmail(email);
        userToken.setResetToken(myToken);
        userToken.setCreateDate(LocalDateTime.now());
        userToken.setEnabled(true);
        userTokenRepository.save(userToken);
        uActions.sendMail(email, myToken);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Email sent1");
    }
}
