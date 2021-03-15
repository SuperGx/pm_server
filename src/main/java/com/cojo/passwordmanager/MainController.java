package com.cojo.passwordmanager;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MainController {

    @GetMapping("/passwords")
    public EncryptedPasswords passwords(@RequestParam(value = "id", defaultValue = "-1") int id) {
        return new EncryptedPasswords(id);
    }   

} 