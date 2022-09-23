package com.simform.invoicingsystem.service;

import com.simform.invoicingsystem.dto.JwtResponse;
import com.simform.invoicingsystem.dto.SignInRequest;
import com.simform.invoicingsystem.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.time.LocalDateTime;

@Service
public class SignInService {

    private CustomUserDetailsService customUserDetailsService;
    private AuthenticationManager authenticationManager;

    public SignInService(CustomUserDetailsService customUserDetailsService, AuthenticationManager authenticationManager) {
        this.customUserDetailsService = customUserDetailsService;
        this.authenticationManager = authenticationManager;
    }

    public Cookie addCookie(SignInRequest signInRequest) {
        JwtUtil jwtUtil = new JwtUtil();

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(signInRequest.getEmail());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword(), userDetails.getAuthorities());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        Cookie cookie = new Cookie("token", jwtUtil.generateToken(signInRequest.getEmail()));
        cookie.setPath("/");
        //age = 7 days
        cookie.setMaxAge(60 * 60 * 24 * 7);
        return cookie;
    }

    public JwtResponse generateToken(SignInRequest signInRequest) {
        JwtUtil jwtUtil = new JwtUtil();

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(signInRequest.getEmail());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword(), userDetails.getAuthorities());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setToken(jwtUtil.generateToken(signInRequest.getEmail()));
        jwtResponse.setExpiry(LocalDateTime.now().plusDays(7));
        return jwtResponse;
    }
}
