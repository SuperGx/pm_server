package com.cojo.passwordmanager;


import java.util.List;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class MainController {

    private final EncryptedDataRepository repository;

    MainController(EncryptedDataRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/passwords")
    ResponseEntity<List<EncryptedData>> all() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.findAll());
    }

    @GetMapping("/passwords/{id}")
    ResponseEntity<EncryptedData> one(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.findById(id).orElseThrow());
    }

    @PostMapping("/passwords")
    ResponseEntity<EncryptedData> insertEncryptedData(@RequestParam(value = "content") String content) 
    {
        EncryptedData eData = new EncryptedData(content);
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
    
}