package com.example.carent.exception;

import com.example.carent.dto.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(value= PasswordMismatchException.class)
    public final ResponseEntity<ExceptionResponse> handleUserException(PasswordMismatchException exception){
          ExceptionResponse exceptionResponse = ExceptionResponse.builder().
                  message(exception.getMessage())
                  .status(HttpStatus.BAD_REQUEST)
                  .timeStamp(LocalDateTime.now()).
        build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value= UserAlreadyExistException.class)
    public final ResponseEntity<ExceptionResponse> handleUserAlreadyException(UserAlreadyExistException exception){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().
                message(exception.getMessage())
                .status(HttpStatus.CONFLICT)
                .timeStamp(LocalDateTime.now()).
                build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }
}
