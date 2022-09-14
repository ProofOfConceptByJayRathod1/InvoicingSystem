package com.simform.invoicingsystem.exception.handler;

import com.simform.invoicingsystem.exception.IncorrectPasswordException;
import com.simform.invoicingsystem.exception.GeneralSignInException;
import com.simform.invoicingsystem.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SignInExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundException(UserNotFoundException exception){
        return new ResponseEntity<>("User not found" , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IncorrectPasswordException.class)
    public ResponseEntity<Object> incorrectPasswordException(IncorrectPasswordException exception){
        return new ResponseEntity<>("Invalid password" , HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = GeneralSignInException.class)
    public ResponseEntity<Object> otherSignInException(GeneralSignInException exception){
        return new ResponseEntity<>("Some other error occurred while sign-in" , HttpStatus.FORBIDDEN);
    }
}
