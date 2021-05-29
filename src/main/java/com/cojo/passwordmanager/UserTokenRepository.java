package com.cojo.passwordmanager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;


public interface UserTokenRepository extends JpaRepository<UserToken, String> {
    UserToken findByUserEmail(String email);
    UserToken findByResetToken(String token);
}
