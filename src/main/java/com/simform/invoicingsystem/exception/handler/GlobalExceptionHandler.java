package com.simform.invoicingsystem.exception.handler;

import com.simform.invoicingsystem.dto.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<Object> userNotFoundException(UsernameNotFoundException exception) {
        GenericResponse genericResponse = new GenericResponse(false, exception.getMessage(),
                HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
        log.error("handling UserNotFoundException...");
        return new ResponseEntity<>(genericResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<Object> badCredentialException(BadCredentialsException exception) {
        GenericResponse genericResponse = new GenericResponse(false, exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        log.error("handling BadCredentialsException...");
        return new ResponseEntity<>(genericResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public final ResponseEntity<GenericResponse> handleAccessDeniedException(AccessDeniedException ex) {
        GenericResponse genericResponse = new GenericResponse(false, ex.getMessage() + " or you don't have permission", 403, LocalDateTime.now());
        log.error("handling AccessDeniedException...");
        return new ResponseEntity<>(genericResponse, HttpStatus.FORBIDDEN);
    }
}
