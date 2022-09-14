package com.simform.invoicingsystem.service;

import com.simform.invoicingsystem.dto.UserDetail;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetail userDetail = new UserDetail();
        if (Objects.equals(userDetail.getUsername(), username))
            return new org.springframework.security.core.userdetails.User(userDetail.getUsername(), userDetail.getPassword(), List.of(new SimpleGrantedAuthority(userDetail.getRole())));
        throw new UsernameNotFoundException("User with username " + username + " not found");
    }
}
