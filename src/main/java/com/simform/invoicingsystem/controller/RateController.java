package com.simform.invoicingsystem.controller;

import com.simform.invoicingsystem.service.RateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rates")
public class RateController {
    private RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }
}
