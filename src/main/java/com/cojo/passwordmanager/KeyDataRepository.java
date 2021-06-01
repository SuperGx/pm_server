package com.cojo.passwordmanager;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyDataRepository extends JpaRepository<KeyData, Long> {
    KeyData findByKeymail(String email);
}
