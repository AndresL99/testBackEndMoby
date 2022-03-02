package com.alerner.testBackEnd.exception;

public class CandidateForTechnologyNotExistException extends RuntimeException
{
    public CandidateForTechnologyNotExistException(String message)
    {
        super(message);
    }
}
