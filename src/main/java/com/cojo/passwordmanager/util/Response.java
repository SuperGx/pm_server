package com.cojo.passwordmanager.util;

import java.io.Serializable;

import org.springframework.stereotype.Service;

@Service
public class Response implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Response() {
        
    }
    
}