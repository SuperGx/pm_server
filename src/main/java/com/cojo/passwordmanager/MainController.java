package com.cojo.passwordmanager;


import java.util.List;

import com.cojo.passwordmanager.util.Response;
import com.cojo.passwordmanager.util.UserHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
public class MainController {

    @Autowired
    private EncryptedDataRepository repository;

    @Autowired
    private UserDataRepository userRepository;

    @Autowired
    private UserHelper helper;

    @Autowired
    private KeyDataRepository keyDataRepository;

    @Autowired
    private Response response;

    @GetMapping("/passwords")
    ResponseEntity<List<EncryptedData>> all() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.findByUserDataEmail(helper.getLoggedUserEmail()));
    }

    @GetMapping("/passwords/{id}")
    ResponseEntity<EncryptedData> one(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.findById(id).orElseThrow());
    }

    @PostMapping("/passwords")
    ResponseEntity<?> insertEncryptedData(@RequestBody EncryptedData encryptedData)
    {
        encryptedData.setUserData(userRepository.findByEmail(helper.getLoggedUserEmail()));
        repository.save(encryptedData);
        response.setMessage("Passwords created!");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/passwords/{id}")
    ResponseEntity<?> updateEncryptedData(@PathVariable Long id ,@RequestBody EncryptedData encryptedData) {
        EncryptedData encryptedDataDB = repository.findById(id).orElseThrow();
        if(encryptedDataDB.verifyRequesterEmail()) {
            encryptedDataDB.setEncrypted_data(encryptedData.getEncrypted_data());
            repository.save(encryptedDataDB);
        } else {
            response.setMessage("Cannot update for this user!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response.setMessage("Password updated!");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/passwords/{id}")
    ResponseEntity<?> deleteData(@PathVariable Long id) {
        EncryptedData encryptedDataDB = repository.findById(id).orElseThrow();
        if(encryptedDataDB.verifyRequesterEmail()) {
            repository.deleteById(id);
        } else {
            response.setMessage("Cannot delete for this user!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response.setMessage("Password deleted!");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/mfakey")
    ResponseEntity<?> storeMFAKey(@RequestBody KeyData keyData) {
        keyDataRepository.save(keyData);
        response.setMessage("Key Created!");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/mfakey")
    ResponseEntity<KeyData> getMFAKey() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(keyDataRepository.findByKeymail(helper.getLoggedUserEmail()));
    }
}