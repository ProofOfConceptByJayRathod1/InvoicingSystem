package com.simform.invoicingsystem.controller;

import com.simform.invoicingsystem.dto.GenericResponse;
import com.simform.invoicingsystem.dto.ProjectClassicView;
import com.simform.invoicingsystem.service.DropdownService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/get-data")
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
        log.debug("Entering /getCsm");
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
        log.debug("Entering /getSalesPerson");
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
        log.debug("Entering /getLeadSource end-point");
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
        log.debug("Entering /getMarketingChannel end-point");
        return dropdownService.getMarketingChannel();
    }

    @Operation(summary = "Get ProjectModel Name API", description = "Here, all the attributes of the ProjectModel dropdown will be retrieved.", tags = {"Dropdown Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved all the ProjectModel data successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping(value = "/getProjectModel")
    public List<String> getProjectModel() {
        log.debug("Entering /getProjectModel end-point");
        return dropdownService.getProjectModel();
    }

    @Operation(summary = "Get InvoiceCycle Name API", description = "Here, all the attributes of the InvoiceCycle dropdown will be retrieved.", tags = {"Dropdown Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved all the InvoiceCycle data successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping(value = "/getInvoiceCycle")
    public List<String> getInvoiceCycle() {
        log.debug("Entering /getInvoiceCycle end-point");
        return dropdownService.getInvoiceCycle();
    }

    @Operation(summary = "Get AccType Name API", description = "Here, all the attributes of the AccType dropdown will be retrieved.", tags = {"Dropdown Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved all the AccType data successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping(value = "/getAccType")
    public List<String> getAccType() {
        log.debug("Entering /getAccType end-point");
        return dropdownService.getAccType();
    }

    @Operation(summary = "Get PayModel Name API", description = "Here, all the attributes of the PayModel dropdown will be retrieved.", tags = {"Dropdown Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved all the PayModel data successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping(value = "/getPayModel")
    public List<String> getPayModel() {
        log.debug("Entering /getPayModel end-point");
        return dropdownService.getPayModel();
    }

    @Operation(summary = "Get Active Billing Name API", description = "Here, all the attributes of the Active Billing dropdown will be retrieved.", tags = {"Dropdown Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved all the ActiveBilling data successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping(value = "/getActiveBilling")
    public List<String> getActiveBilling() {
        log.debug("Entering /getActiveBilling");
        return dropdownService.getActiveBilling();
    }

    @Operation(summary = "AutoSuggest Company Name API", description = "Here, the api will automatically suggest the company name based on the value entered by user .", tags = {"Dropdown Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Company found successfully"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")

    })
    @GetMapping(value = "/autoSuggestion/companyName")
    public ResponseEntity<GenericResponse> autoSuggestionCompanyName(@RequestParam String companyName) {
        List<String> companyNameList = dropdownService.autoSuggestionCompanyName(companyName);
        return new ResponseEntity<>(new GenericResponse(true, "Found Successfully", companyNameList, 200, LocalDateTime.now()), HttpStatus.OK);
    }

}
