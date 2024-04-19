package dev.aditya.ecommercejavaproject.ControllerAdvice;

import dev.aditya.ecommercejavaproject.Exception.productNotFound;
import dev.aditya.ecommercejavaproject.dtos.ErrorDtos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {
    @ExceptionHandler(productNotFound.class)
public ResponseEntity<ErrorDtos>handleProductNotFoundException(productNotFound exception){
    ErrorDtos error = new ErrorDtos();
    error.setMessage(exception.getMessage());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
}
}
