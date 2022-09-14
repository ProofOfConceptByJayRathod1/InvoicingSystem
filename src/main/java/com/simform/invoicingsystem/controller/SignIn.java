package com.simform.invoicingsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignIn {


    @Operation(summary = "Hello Swagger Api",description = "Here it is implemented for demo purpose", tags = {"Demo Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged successfully"),
            @ApiResponse(responseCode = "400",description = "Bad Request")
    })
    @GetMapping("/hello")
    public String hello(){
        return ("hello I have succesfully implemented swagger");
    }
}
