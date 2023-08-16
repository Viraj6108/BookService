package com.Book.Exception;

public class UserNotFoundException extends Exception {
    String msg;
    public UserNotFoundException(String msg) {
        super(msg);

    }
}
