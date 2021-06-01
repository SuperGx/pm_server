
package com.cojo.passwordmanager.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

	@Autowired
    private JavaMailSender javaMailSender;
    
	@Async
    public void sendEmail(SimpleMailMessage email) {

        javaMailSender.send(email);
        
	}
}