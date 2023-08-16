package com.Book.Dto;

public class RegistrationResponse {
    private boolean registered ;
    private String message;


    public RegistrationResponse(boolean registered, String message)
    {
        this.registered = registered;
        this.message = message;
    }
    public RegistrationResponse(String message)
    {
        this.message = message;
    }

    public boolean isRegistered() {
        return registered;
    }

    public String getMessage() {
        return message;
    }
}
