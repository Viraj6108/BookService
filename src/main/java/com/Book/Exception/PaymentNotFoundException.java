package com.Book.Exception;

public class PaymentNotFoundException extends Exception{

    String msg;

    public PaymentNotFoundException(String msg)
    {
        super(msg);

    }
}
