package com.simform.invoicingsystem.exception.handler;

import com.simform.invoicingsystem.dto.GenericResponse;
import com.simform.invoicingsystem.exception.ProjectAlreadyExistException;
import com.simform.invoicingsystem.exception.ResourceNotFoundException;
import com.simform.invoicingsystem.util.EmptyJsonBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        GenericResponse genericResponse = new GenericResponse(false, "Invalid input details", errors.toString(),
                HttpStatus.NOT_ACCEPTABLE.value(), LocalDateTime.now());
        log.error("handling MethodArgumentNotValidException...");
        return new ResponseEntity<>(genericResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        GenericResponse genericResponse = new GenericResponse(false, exception.getMessage(), new EmptyJsonBody(), 405, LocalDateTime.now());
        log.error("handling HttpRequestMethodNotSupported...");
        return new ResponseEntity<>(genericResponse, HttpStatus.METHOD_NOT_ALLOWED);
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


    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<GenericResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
        GenericResponse genericResponse = new GenericResponse(false, exception.getMessage(), new EmptyJsonBody(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
        log.error("handling ResourceNotFoundException...");
        return new ResponseEntity<>(genericResponse, HttpStatus.NOT_FOUND);
    }


}
