package com.cojo.passwordmanager;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EncryptedDataRepository extends JpaRepository<EncryptedData, Long> {
    
}
