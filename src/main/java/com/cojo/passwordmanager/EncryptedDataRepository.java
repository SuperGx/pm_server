package com.cojo.passwordmanager;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EncryptedDataRepository extends JpaRepository<EncryptedData, Long> {
    List<EncryptedData> findByUserDataEmail(String email);
}
