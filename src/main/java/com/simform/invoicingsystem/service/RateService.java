package com.simform.invoicingsystem.service;

import com.simform.invoicingsystem.repository.RateRepository;
import org.springframework.stereotype.Service;

@Service
public class RateService {

    private RateRepository rateRepository;

    public RateService(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }
}
