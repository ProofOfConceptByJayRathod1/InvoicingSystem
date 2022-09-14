package com.simform.invoicingsystem.controller;

import com.simform.invoicingsystem.dto.SignInDTO;
import com.simform.invoicingsystem.service.DemoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignInController {

  @Autowired
  private DemoUserService demoUserService;

  @GetMapping(value = "/signIn")
  public SignInDTO useSignIn(@RequestBody SignInDTO signInDTO){
    return demoUserService.signInService(signInDTO.getEmail() , signInDTO.getPassword());
  }

}
