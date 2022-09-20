package com.simform.invoicingsystem.controller;

import com.simform.invoicingsystem.service.DropdownService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DropdownController {

    private DropdownService dropdownService;

    public DropdownController(DropdownService dropdownService) {
        this.dropdownService = dropdownService;
    }

    @GetMapping("/getCsm")
    public List<String> getCsm() {
        return dropdownService.getCsm();
    }

    @GetMapping("/getSalesPerson")
    public List<String> getSalesPerson() {
        return dropdownService.getSalesPerson();
    }

    @GetMapping("/getLeadSource")
    public List<String> getLeadSource() {
        return dropdownService.getLeadSource();
    }

    @GetMapping("/getMarketingChannel")
    public List<String> getMarketingChannel() {
        return dropdownService.getMarketingChannel();
    }
}
