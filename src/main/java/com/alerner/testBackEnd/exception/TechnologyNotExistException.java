package com.alerner.testBackEnd.exception;

public class TechnologyNotExistException extends RuntimeException
{
    public TechnologyNotExistException(String message)
    {
        super(message);
    }
}
