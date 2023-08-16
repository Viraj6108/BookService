package com.Book.Exception;

public class CartNotFoundException extends Exception{
    String msg ;
    public CartNotFoundException(String msg)
    {
        super(msg);

    }
}
