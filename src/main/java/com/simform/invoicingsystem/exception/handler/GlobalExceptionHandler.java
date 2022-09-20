package com.simform.invoicingsystem.exception.handler;

import com.simform.invoicingsystem.dto.GenericResponse;
import com.simform.invoicingsystem.exception.ProjectAlreadyExistException;
import com.simform.invoicingsystem.util.EmptyJsonBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<GenericResponse> handleUsernameNotFoundException(UsernameNotFoundException exception) {
        GenericResponse genericResponse = new GenericResponse(false, exception.getMessage(), new EmptyJsonBody(),
                HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
        log.error("handling UsernameNotFoundException...");
        return new ResponseEntity<>(genericResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<GenericResponse> handleBadCredentialException(BadCredentialsException exception) {
        GenericResponse genericResponse = new GenericResponse(false, exception.getMessage(), new EmptyJsonBody(),
                HttpStatus.UNAUTHORIZED.value(), LocalDateTime.now());
        log.error("handling BadCredentialsException...");
        return new ResponseEntity<>(genericResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public final ResponseEntity<GenericResponse> handleAccessDeniedException(AccessDeniedException exception) {
        GenericResponse genericResponse = new GenericResponse(false, exception.getMessage() + " or you don't have permission", new EmptyJsonBody(), 403, LocalDateTime.now());
        log.error("handling AccessDeniedException...");
        return new ResponseEntity<>(genericResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = ProjectAlreadyExistException.class)
    public final ResponseEntity<GenericResponse> handleAccessDeniedException(ProjectAlreadyExistException exception) {
        GenericResponse genericResponse = new GenericResponse(false, exception.getMessage(), new EmptyJsonBody(), 409, LocalDateTime.now());
        log.error("handling ProjectAlreadyExistException...");
        return new ResponseEntity<>(genericResponse, HttpStatus.CONFLICT);
    }
}
