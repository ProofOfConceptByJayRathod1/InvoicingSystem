package com.simform.invoicingsystem.service;

import com.simform.invoicingsystem.dto.SignInDTO;
import com.simform.invoicingsystem.entity.DemoUser;
import com.simform.invoicingsystem.repository.DemoUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoUserService {
  @Autowired
  private DemoUserRepository demoUserRepository;

  public SignInDTO signInService(String email , String password){
    /*if(!demoUserRepository.existsByEmail(email)){
      //user not found exception
    }*/
    DemoUser demoUser = demoUserRepository.findByEmail(email);
    /*if(!demoUser.equals(password)){
    //incorrect password exception
    }*/
    SignInDTO signInDTO = new SignInDTO();
    BeanUtils.copyProperties(demoUser , signInDTO);
    return signInDTO;
  }
}
