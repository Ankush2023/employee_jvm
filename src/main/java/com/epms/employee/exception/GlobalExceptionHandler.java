package com.epms.employee.exception;

import com.epms.employee.model.response.CustomResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({EmployeeNotFoundException.class})
    public ResponseEntity<?> employeeNotFoundException(Exception e){
        return new ResponseEntity<>(new CustomResponseMessage(e.getMessage(),-1), HttpStatus.NOT_FOUND);
    }
}
