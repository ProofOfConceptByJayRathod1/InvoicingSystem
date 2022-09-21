package com.simform.invoicingsystem.controller;

import com.simform.invoicingsystem.dto.GenericResponse;
import com.simform.invoicingsystem.dto.SpecialRateDetails;
import com.simform.invoicingsystem.service.RateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/projects/rates")
@Slf4j
public class RateController {
    private RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @PutMapping(value = "/update")
    public ResponseEntity<GenericResponse> updateSpecialRate(@RequestParam String projectName, @RequestBody Collection<SpecialRateDetails> specialRateDetails){
        log.debug("Entering /projects/rates/update end-point");
        rateService.updateSpecialRate(projectName,specialRateDetails);
        log.info("Special Rate Updated successfully");
        log.debug("Exiting /projects/rates/update end-point");
        return new ResponseEntity<>(new GenericResponse(true, "Updated", specialRateDetails, 200, LocalDateTime.now()), HttpStatus.OK);
    }
}
