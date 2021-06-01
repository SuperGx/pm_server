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
    ResponseEntity<EncryptedData> insertEncryptedData(@RequestParam(value = "content") String content)
    {
        EncryptedData eData = new EncryptedData(content);
        eData.setUserData(userRepository.findByEmail(helper.getLoggedUserEmail()));
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(eData));
    }
    @PutMapping("/passwords/{id}")
    ResponseEntity<EncryptedData> updateEncryptedData(@PathVariable Long id ,@RequestParam(value = "content") String content)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            repository.findById(id).map(data -> {
            data.setEncrypted_data(content);
            data.setSome_random_number();
            return repository.save(data);
        }).orElseThrow());
    }
    
    @DeleteMapping("/passwords/{id}")
    ResponseEntity<Object> deleteData(@PathVariable Long id)
    {
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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