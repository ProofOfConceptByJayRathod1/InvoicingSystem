package com.simform.invoicingsystem.filter;

import com.simform.invoicingsystem.service.CustomUserDetailsService;
import com.simform.invoicingsystem.util.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private CustomUserDetailsService customUserDetailsService;

    public JwtFilter(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = null;
        JwtUtil jwtUtil = new JwtUtil();
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("token")) {
                    authorizationHeader = c.getValue();
                }
            }
        }

        String token = null;
        String userName = null;

        if (authorizationHeader != null) {
            token = authorizationHeader;
            userName = jwtUtil.extractUsername(token);
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = null;
            try {
                userDetails = customUserDetailsService.loadUserByUsername(userName);
            } catch (UsernameNotFoundException e) {
                System.out.println(e.getMessage());
                httpServletResponse.sendRedirect("/");
            }
            if (userDetails != null && jwtUtil.validateToken(token, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
