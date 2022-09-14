package com.simform.invoicingsystem.controller;

import com.simform.invoicingsystem.dto.GenericResponse;
import com.simform.invoicingsystem.dto.SignInRequest;
import com.simform.invoicingsystem.service.CustomUserDetailsService;
import com.simform.invoicingsystem.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class SignInController {


    private CustomUserDetailsService customUserDetailsService;
    private AuthenticationManager authenticationManager;

    public SignInController(CustomUserDetailsService customUserDetailsService, AuthenticationManager authenticationManager) {
        this.customUserDetailsService = customUserDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @Operation(summary = "Hello Swagger Api", description = "Here it is implemented for demo purpose", tags = {"Demo Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @GetMapping(value = "/signIn")
    public String useSignIn(@RequestBody @Validated SignInRequest signInRequest, HttpServletResponse response) throws UsernameNotFoundException {
        JwtUtil jwtUtil = new JwtUtil();
        //try {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(signInRequest.getEmail());
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),
                            signInRequest.getPassword(), userDetails.getAuthorities());
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);



        Cookie cookie = new Cookie("token", jwtUtil.generateToken(signInRequest.getEmail()));
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 10);
        response.addCookie(cookie);
        return "Signed in successfully";
    }

}
