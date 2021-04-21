package com.cojo.passwordmanager;

import org.springframework.boot.builder.SpringApplicationBuilder;

public class PasswordmanagerApplication {

    public static void main(String[] args) {
        
        Class<?>[] sources = {MainController.class, UserController.class };
        new SpringApplicationBuilder().sources(sources).run(args);
	}
}


