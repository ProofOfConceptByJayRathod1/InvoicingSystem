package com.simform.invoicingsystem.controller;

import com.simform.invoicingsystem.service.DropdownService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DropdownController {

    private DropdownService dropdownService1;

    public DropdownController(DropdownService dropdownService1) {
        this.dropdownService1 = dropdownService1;
    }

    @GetMapping(value = "/getProjectModel")
    public List<String> getProjectModel(){
        return dropdownService1.getProjectModel();
    }

    @GetMapping(value = "/getInvoiceCycle")
    public List<String> getInvoiceCycle(){
        return dropdownService1.getInvoiceCycle();
    }

    @GetMapping(value = "/getAccType")
    public List<String> getAccType(){
        return dropdownService1.getAccType();
    }

    @GetMapping(value = "/getPayModel")
    public List<String> getPayModel(){
        return dropdownService1.getPayModel();
    }

    @GetMapping(value = "/getActiveBilling")
    public List<String> getActiveBilling(){
        return dropdownService1.getActiveBilling();
    }

}
