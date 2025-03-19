package com.website.ecom_project.error;

public class OTPExpiredException extends RuntimeException {
    public OTPExpiredException(String message){
        super(message);
    }
}
