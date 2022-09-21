package com.simform.invoicingsystem.controller;

import com.simform.invoicingsystem.dto.GenericResponse;
import com.simform.invoicingsystem.dto.SignInRequest;
import com.simform.invoicingsystem.service.SignInService;
import com.simform.invoicingsystem.util.EmptyJsonBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Slf4j
@RestController
public class SignInController {

    private SignInService signInService;

    public SignInController(SignInService signInService) {
        this.signInService = signInService;
    }

    @Operation(summary = "SignIn API", description = "Here, user have to enter username and password for SignIn purpose", tags = {"Sign In Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping(value = "/sign-in")
    public ResponseEntity<GenericResponse> userSignIn(@RequestBody @Validated SignInRequest signInRequest, HttpServletResponse response) throws UsernameNotFoundException {
        log.debug("Entering /sign-in end-point");
        Cookie cookie = signInService.addCookie(signInRequest);
        response.addCookie(cookie);
        log.info("SignIn successfully");
        log.debug("Exiting /sign-in end-point");
        GenericResponse genericResponse = new GenericResponse(true, "signed in successfully", new EmptyJsonBody(), 200, LocalDateTime.now());
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }
}
