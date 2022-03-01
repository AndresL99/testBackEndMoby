package com.alerner.testBackEnd.exception;

public class CandidateNotExistException extends RuntimeException
{
    public CandidateNotExistException(String message)
    {
        super(message);
    }
}
