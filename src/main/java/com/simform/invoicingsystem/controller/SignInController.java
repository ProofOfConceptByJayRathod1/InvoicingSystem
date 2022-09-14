package com.simform.invoicingsystem.controller;

import com.simform.invoicingsystem.dto.SignInDTO;
import com.simform.invoicingsystem.service.DemoUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignInController {

  @Autowired
  private DemoUserService demoUserService;

  @Operation(summary = "Hello Swagger Api",description = "Here it is implemented for demo purpose", tags = {"Demo Controller"})
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "User logged successfully"),
          @ApiResponse(responseCode = "400",description = "Bad Request")
  })
  @GetMapping(value = "/signIn")
  public SignInDTO useSignIn(@RequestBody SignInDTO signInDTO){
    return demoUserService.signInService(signInDTO.getEmail() , signInDTO.getPassword());
  }

}
