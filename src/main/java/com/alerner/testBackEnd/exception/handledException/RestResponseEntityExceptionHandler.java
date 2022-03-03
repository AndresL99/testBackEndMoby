package com.alerner.testBackEnd.exception.handledException;

import javax.validation.ConstraintViolationException;

import com.alerner.testBackEnd.exception.CandidateExistException;
import com.alerner.testBackEnd.exception.CandidateForTechnologyExistException;
import com.alerner.testBackEnd.exception.CandidateForTechnologyNotExistException;
import com.alerner.testBackEnd.exception.CandidateNotExistException;
import com.alerner.testBackEnd.exception.TechnologyExistException;
import com.alerner.testBackEnd.exception.TechnologyNotExistException;
import com.alerner.testBackEnd.exception.UserExistException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object>handledConstraintViolation(ConstraintViolationException exception, WebRequest webRequest)
    {
        List<String>errors = new ArrayList<>();
        for(ConstraintViolation constraintViolation: exception.getConstraintViolations())
        {
            errors.add(constraintViolation.getRootBeanClass().getName()+""+constraintViolation.getMessage());
        }
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,exception.getLocalizedMessage(),errors);
        return new ResponseEntity<Object>(apiError,new HttpHeaders(),apiError.getHttpStatus());
    }


    @ExceptionHandler({CandidateExistException.class})
    public ResponseEntity<Object>candidateAlreadyExist(CandidateExistException c)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(c.getMessage());
    }

    @ExceptionHandler({CandidateNotExistException.class})
    public ResponseEntity<Object>candidateNotExist(CandidateNotExistException c)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(c.getMessage());
    }

    @ExceptionHandler({CandidateForTechnologyExistException.class})
    public ResponseEntity<Object>candidateForTechnologyAlreadyExist(CandidateForTechnologyExistException c)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(c.getMessage());
    }

    @ExceptionHandler({CandidateForTechnologyNotExistException.class})
    public ResponseEntity<Object>candidateForTechnologyNotExist(CandidateForTechnologyNotExistException c)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(c.getMessage());
    }

    @ExceptionHandler({TechnologyExistException.class})
    public ResponseEntity<Object>technologyAlreadyExist(TechnologyExistException t)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(t.getMessage());
    }

    @ExceptionHandler({TechnologyNotExistException.class})
    public ResponseEntity<Object>technologyNotExist(TechnologyNotExistException t)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(t.getMessage());
    }

    @ExceptionHandler({UserExistException.class})
    public ResponseEntity<Object>userAlreadyExist(UserExistException u)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(u.getMessage());
    }

}
