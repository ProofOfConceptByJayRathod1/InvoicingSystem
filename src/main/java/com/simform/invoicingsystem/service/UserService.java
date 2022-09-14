package com.simform.invoicingsystem.service;

import com.simform.invoicingsystem.dto.SignInRequest;
import com.simform.invoicingsystem.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;
/*
  public SignInRequest signInService(String email , String password){
    *//*if(!demoUserRepository.existsByEmail(email)){
      //user not found exception
    }*//*
    User demoUser = userRepository.findByEmail(email);
    *//*if(!demoUser.equals(password)){
    //incorrect password exception
    }*//*
    SignInRequest signInRequest = new SignInRequest();
    BeanUtils.copyProperties(demoUser , signInRequest);
    return signInRequest;
  }*/




}
