package com.cojo.passwordmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    UserDataRepository userDataRepository;
    Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final UserData customer = userDataRepository.findByEmail(email);
        if (customer == null) {
            throw new UsernameNotFoundException(email);
        }
        UserDetails user = User.withUsername(customer.getEmail())
                            .password(customer.getPassword())
                            .authorities("USER").build();
        return user;
    }
}