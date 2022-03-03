package com.alerner.testBackEnd.exception;

public class UserExistException extends RuntimeException
{
    public UserExistException(String message)
    {
        super(message);
    }
}
