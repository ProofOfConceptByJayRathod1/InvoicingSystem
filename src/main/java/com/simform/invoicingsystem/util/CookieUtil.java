package com.simform.invoicingsystem.util;

import com.simform.invoicingsystem.service.CustomUserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.Cookie;

public class CookieUtil {
    public static Cookie cookieMaker(String username, String password, AuthenticationManager authenticationManager,
                                     JwtUtil jwtUtil, CustomUserDetailsService service) {
        try {
            UserDetails userDetails = service.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (Exception ex) {
            return null;
        }
        Cookie cookie = new Cookie("token", jwtUtil.generateToken(username));
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 10);
        return cookie;
    }
}
