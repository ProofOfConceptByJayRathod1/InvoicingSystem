package com.simform.invoicingsystem.controller;

import com.simform.invoicingsystem.dto.ClientDetails;
import com.simform.invoicingsystem.dto.ProjectDetails;
import com.simform.invoicingsystem.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/projects")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @Operation(summary = "ADD PROJECT API", description = "Here, user can add new project", tags = {"Add Project Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project added successfully"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })

    @PostMapping("/add")
    public ResponseEntity<ProjectDetails> addProject(@Validated @RequestBody ProjectDetails projectDetails, BindingResult result) {
        if(result.hasErrors()){
            System.out.println(result);
            return new ResponseEntity<ProjectDetails>(projectDetails, HttpStatus.NOT_ACCEPTABLE);
        }
        System.out.println(projectDetails);
        return new ResponseEntity<ProjectDetails>(projectDetails, HttpStatus.CREATED);
    }
}
