package com.simform.invoicingsystem.exception.handler;

import com.simform.invoicingsystem.dto.GenericResponse;
import com.simform.invoicingsystem.exception.IncorrectPasswordException;
import com.simform.invoicingsystem.exception.GeneralSignInException;
import com.simform.invoicingsystem.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class SignInExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IncorrectPasswordException.class)
    public ResponseEntity<Object> incorrectPasswordException(IncorrectPasswordException exception) {
        return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = GeneralSignInException.class)
    public ResponseEntity<Object> otherSignInException(GeneralSignInException exception) {
        return new ResponseEntity<>("Some other error occurred while sign-in", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<Object> badCredentialException(BadCredentialsException exception) {

        GenericResponse genericResponse = new GenericResponse(false, exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        // log.error("handling BadCredentialsException...");
        return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<GenericResponse> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setCode(403);
       // genericResponse.setData(ex);
        genericResponse.setMessage(ex.getMessage() + " or you don't have permission");
        genericResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(genericResponse, HttpStatus.FORBIDDEN);
    }
/*
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException exception)
    {
        Map<String, Object> response = new HashMap<>();
        if (exception.hasFieldErrors()) {
            List<Map<String, String>> errors = new ArrayList<>();
            for (FieldError error : exception.getFieldErrors()) {
                Map<String, String> transformedError = new HashMap<>();
                transformedError.put("field", error.getField());
                transformedError.put("error", error.getDefaultMessage());
                errors.add(transformedError);
            }
            response.put("errors", errors);
        }

        GenericResponse genericResponse = new GenericResponse(false, "Password Should Contain At Least 1 Capital letter, 1 Symbol and 1 Digit", response, HttpStatus.BAD_REQUEST.value());
     //   log.error("handling MethodArgumentNotValidException...");
        return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
    }*/


}
