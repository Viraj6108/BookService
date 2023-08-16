package com.Book.Exception;


public class CustomerException extends Exception{

    String msg;
    public CustomerException(String msg)
    {
        super(msg);

    }
}
