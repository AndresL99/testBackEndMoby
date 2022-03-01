package com.alerner.testBackEnd.exception;

public class CandidateExistException extends RuntimeException
{
    public CandidateExistException(String message)
    {
        super(message);
    }
}
