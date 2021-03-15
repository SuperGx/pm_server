package com.cojo.passwordmanager;


import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MainController {

    Integer user_id = 342;

    private final EncryptedDataRepository repository;

    MainController(EncryptedDataRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/passwords")
    List<EncryptedData> all(Integer user_id) {
        return repository.findAll();
    }

    @GetMapping("/passwords/{id}")
    EncryptedData one(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow();
    }

    @PostMapping("/passwords")
    EncryptedData eData(@RequestParam(value = "content") String content) 
    {
        EncryptedData eData = new EncryptedData(user_id, content);
        return repository.save(eData);
    }

} 