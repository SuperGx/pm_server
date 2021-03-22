package com.cojo.passwordmanager;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class UserController {
    private final UserDataRepository repository;

    UserController(UserDataRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/register") 
    ResponseEntity<String> registerUser(@RequestBody UserData userData)
    {
        UserActions uActions = new UserActions(repository);
        try {
            if(uActions.register(userData))
            {
                return ResponseEntity.status(HttpStatus.CREATED).body("User Created");
            } 
            else
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usert not saved");
            }
        } catch (UserAlreadyExistException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
