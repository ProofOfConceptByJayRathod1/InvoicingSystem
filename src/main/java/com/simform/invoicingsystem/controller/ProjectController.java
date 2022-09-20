package com.simform.invoicingsystem.controller;

import com.simform.invoicingsystem.dto.GenericResponse;
import com.simform.invoicingsystem.dto.ProjectDetail;
import com.simform.invoicingsystem.service.ProjectService;
import com.simform.invoicingsystem.util.EmptyJsonBody;
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

import java.time.LocalDateTime;




@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

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
    public ResponseEntity<GenericResponse> addProject(@Validated @RequestBody ProjectDetail projectDetails, BindingResult result) {

        GenericResponse genericResponse = new GenericResponse();
        if(result.hasErrors()){
            genericResponse.setMessage("Unable to create");
            genericResponse.setCode(406);
            genericResponse.setTimestamp(LocalDateTime.now());
            genericResponse.setSuccess(false);
            genericResponse.setData(new EmptyJsonBody());
            return new ResponseEntity<>(genericResponse, HttpStatus.NOT_ACCEPTABLE);
        }
        genericResponse.setMessage("Created");
        genericResponse.setCode(201);
        genericResponse.setTimestamp(LocalDateTime.now());
        genericResponse.setSuccess(true);
        genericResponse.setData(new EmptyJsonBody());
        projectService.addProject(projectDetails);
        return new ResponseEntity<>(genericResponse, HttpStatus.CREATED);

    }
}
