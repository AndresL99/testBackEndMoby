package com.alerner.testBackEnd.exception;

public class TechnologyExistException extends RuntimeException
{
    public TechnologyExistException(String message)
    {
        super(message);
    }
}
