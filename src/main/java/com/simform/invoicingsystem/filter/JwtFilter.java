package com.simform.invoicingsystem.filter;

import com.simform.invoicingsystem.service.CustomUserDetailsService;
import com.simform.invoicingsystem.util.CookieUtil;
import com.simform.invoicingsystem.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private CustomUserDetailsService customUserDetailsService;

    public JwtFilter(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        JwtUtil jwtUtil = new JwtUtil();
        String token = CookieUtil.getCookieValueByName(httpServletRequest, "token");

        String userName = null;

        if (!token.equals("")) {
            try {
                userName = jwtUtil.extractUsername(token);
            } catch (IllegalArgumentException e) {
                log.error("An error occurred while getting username from token: ", e);
            } catch (ExpiredJwtException e) {
                log.warn("The token is expired and not valid anymore: ", e);
            } catch (Exception exception) {
                log.error("An error occurred while processing authentication : ", exception);
            }
        }
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = null;
            try {
                userDetails = customUserDetailsService.loadUserByUsername(userName);
            } catch (UsernameNotFoundException e) {
                log.error("Error: ", e);
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


