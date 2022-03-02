package com.alerner.testBackEnd.exception;

public class CandidateForTechnologyExistException extends RuntimeException
{
    public CandidateForTechnologyExistException(String message)
    {
        super(message);
    }
}
