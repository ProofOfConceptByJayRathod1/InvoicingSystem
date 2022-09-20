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

    @GetMapping(value = "/getProjectModel")
    public List<String> getProjectModel(){
        return dropdownService.getProjectModel();
    }

    @GetMapping(value = "/getInvoiceCycle")
    public List<String> getInvoiceCycle(){
        return dropdownService.getInvoiceCycle();
    }

    @GetMapping(value = "/getAccType")
    public List<String> getAccType(){
        return dropdownService.getAccType();
    }

    @GetMapping(value = "/getPayModel")
    public List<String> getPayModel(){
        return dropdownService.getPayModel();
    }

    @GetMapping(value = "/getActiveBilling")
    public List<String> getActiveBilling(){
        return dropdownService.getActiveBilling();
    }

}
