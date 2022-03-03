package com.alerner.testBackEnd.AOP;

import com.alerner.testBackEnd.exception.TestDataAccessException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectException
{
    @AfterThrowing(pointcut = "within(com.alerner.testBackEnd.service.*)", throwing = "dataAccessException")
    public void handledDataException(DataAccessException dataAccessException)throws TestDataAccessException
    {
        throw new TestDataAccessException("Ha ocurrido un error.");
    }
}
