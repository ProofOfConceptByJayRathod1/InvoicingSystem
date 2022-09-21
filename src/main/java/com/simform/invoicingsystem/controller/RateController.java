package com.simform.invoicingsystem.controller;

import com.simform.invoicingsystem.dto.GenericResponse;

import com.simform.invoicingsystem.dto.RateDetails;
import com.simform.invoicingsystem.dto.SpecialRateDetails;
import com.simform.invoicingsystem.service.RateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/projects/rates")
@Slf4j
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
    @PutMapping(value = "/update")
    public ResponseEntity<GenericResponse> updateRateDetails(@RequestParam("projectName") String projectName, @RequestBody List<RateDetails> rateDetailsList) {
        log.debug("Entering /projects/rates/update end-point");
        Collection<RateDetails> rateDetails = rateService.updateRate(projectName, rateDetailsList);
        log.info("Rate Updated successfully");
        log.debug("Exiting /projects/rates/update end-point");
        return new ResponseEntity<>(new GenericResponse(true, "Updated", rateDetails, 200, LocalDateTime.now()), HttpStatus.OK);
    }

    @Operation(summary = "Update Special Rate API", description = "Here, some of the special rates of project will be update", tags = {"Rate Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Special Rate updated successfully"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PutMapping(value = "/specialRate/update")
    public ResponseEntity<GenericResponse> updateSpecialRate(@RequestParam("projectName") String projectName, @RequestBody Collection<SpecialRateDetails> specialRateDetails) {
        log.debug("Entering /projects/special-rates/update end-point");
        rateService.updateSpecialRate(projectName, specialRateDetails);
        log.info("Special Rate Updated successfully");
        log.debug("Exiting /projects/special-rates/update end-point");
        return new ResponseEntity<>(new GenericResponse(true, "Updated", specialRateDetails, 200, LocalDateTime.now()), HttpStatus.OK);
    }
}
