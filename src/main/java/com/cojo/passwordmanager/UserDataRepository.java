package com.cojo.passwordmanager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;


public interface UserDataRepository extends JpaRepository<UserData, Long> {
    UserData findByEmail(String email);
}
