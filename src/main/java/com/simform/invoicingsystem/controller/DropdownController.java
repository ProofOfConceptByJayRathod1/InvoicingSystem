package com.simform.invoicingsystem.controller;

import com.simform.invoicingsystem.service.DropdownService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class DropdownController {
    private DropdownService dropdownService;

    public DropdownController(DropdownService dropdownService) {
        this.dropdownService = dropdownService;
    }
    @Operation(summary = "Get CSM Name API", description = "Here, all the attributes of the CSM dropdown will be retrieved.", tags = {"Dropdown Controller"})
    @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Retrieved all the CSM data successfully"),
      @ApiResponse(responseCode = "403", description = "Forbidden"),
      @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/getCsm")
    public List<String> getCsm() {
        return dropdownService.getCsm();
    }

    @Operation(summary = "Get SalesPerson Name API", description = "Here, all the attributes of the SalesPerson dropdown will be retrieved.", tags = {"Dropdown Controller"})
    @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Retrieved all the SalesPerson data successfully"),
      @ApiResponse(responseCode = "403", description = "Forbidden"),
      @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/getSalesPerson")
    public List<String> getSalesPerson() {
        return dropdownService.getSalesPerson();
    }


    @Operation(summary = "Get LeadSource Name API", description = "Here, all the attributes of the LeadSource dropdown will be retrieved.", tags = {"Dropdown Controller"})
    @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Retrieved all the LeadSource data successfully"),
      @ApiResponse(responseCode = "403", description = "Forbidden"),
      @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/getLeadSource")
    public List<String> getLeadSource() {
        return dropdownService.getLeadSource();
    }

    @Operation(summary = "Get MarketingChannel Name API", description = "Here, all the attributes of the MarketingChannel dropdown will be retrieved.", tags = {"Dropdown Controller"})
    @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Retrieved all the MarketingChannel data successfully"),
      @ApiResponse(responseCode = "403", description = "Forbidden"),
      @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/getMarketingChannel")
    public List<String> getMarketingChannel() {
        return dropdownService.getMarketingChannel();
    }

    @Operation(summary = "Get ProjectModel Name API", description = "Here, all the attributes of the ProjectModel dropdown will be retrieved.", tags = {"Dropdown Controller"})
    @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Retrieved all the ProjectModel data successfully"),
      @ApiResponse(responseCode = "403", description = "Forbidden"),
      @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping(value = "/getProjectModel")
    public List<String> getProjectModel(){
        return dropdownService.getProjectModel();
    }

    @Operation(summary = "Get InvoiceCycle Name API", description = "Here, all the attributes of the InvoiceCycle dropdown will be retrieved.", tags = {"Dropdown Controller"})
    @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Retrieved all the InvoiceCycle data successfully"),
      @ApiResponse(responseCode = "403", description = "Forbidden"),
      @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping(value = "/getInvoiceCycle")
    public List<String> getInvoiceCycle(){
        return dropdownService.getInvoiceCycle();
    }

    @Operation(summary = "Get AccType Name API", description = "Here, all the attributes of the AccType dropdown will be retrieved.", tags = {"Dropdown Controller"})
    @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Retrieved all the AccType data successfully"),
      @ApiResponse(responseCode = "403", description = "Forbidden"),
      @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping(value = "/getAccType")
    public List<String> getAccType(){
        return dropdownService.getAccType();
    }

    @Operation(summary = "Get PayModel Name API", description = "Here, all the attributes of the PayModel dropdown will be retrieved.", tags = {"Dropdown Controller"})
    @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Retrieved all the PayModel data successfully"),
      @ApiResponse(responseCode = "403", description = "Forbidden"),
      @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping(value = "/getPayModel")
    public List<String> getPayModel(){
        return dropdownService.getPayModel();
    }

    @Operation(summary = "Get Active Billing Name API", description = "Here, all the attributes of the Active Billing dropdown will be retrieved.", tags = {"Dropdown Controller"})
    @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Retrieved all the ActiveBilling data successfully"),
      @ApiResponse(responseCode = "403", description = "Forbidden"),
      @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping(value = "/getActiveBilling")
    public List<String> getActiveBilling(){
        return dropdownService.getActiveBilling();
    }

}
