package com.simform.invoicingsystem.controller;

import com.simform.invoicingsystem.dto.GenericResponse;
import com.simform.invoicingsystem.dto.RateDetails;
import com.simform.invoicingsystem.service.RateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/rates")
public class RateController {
    private RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @Operation(summary = "Update Rate API", description = "Here, some of the rates of project will be update", tags = {"Rate Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rate updated successfully"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PutMapping(value = "/update/{projectName}")
    public ResponseEntity<GenericResponse> updateRateDetails(@PathVariable("projectName") String projectName , @RequestBody List<RateDetails> rateDetailsList){
        Collection<RateDetails> rateDetails = rateService.updateRate(projectName , rateDetailsList);
        return new ResponseEntity<>(new GenericResponse(true, "Updated", rateDetails, 200, LocalDateTime.now()), HttpStatus.OK);
    }
}
