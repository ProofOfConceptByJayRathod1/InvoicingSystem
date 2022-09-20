package com.simform.invoicingsystem.controller;

import com.simform.invoicingsystem.dto.GenericResponse;
import com.simform.invoicingsystem.dto.ProjectDetails;
import com.simform.invoicingsystem.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
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
    public ResponseEntity<GenericResponse> addProject(@Validated @RequestBody ProjectDetails projectDetails) {
        projectDetails = projectService.addProject(projectDetails);
        return new ResponseEntity<>(new GenericResponse(true, "Created", projectDetails, 201, LocalDateTime.now()), HttpStatus.CREATED);
    }

    @Operation(summary = "Project Update API", description = "Here, some of the fields of the project table will be updated.", tags = {"Project Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project updated successfully"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PutMapping(value = "/update/{projectName}")
    public ResponseEntity<GenericResponse> updateProject(@RequestBody ProjectDetails projectDetails, @PathVariable("projectName") String projectName) {
        projectDetails = projectService.updateProject(projectDetails, projectName);
        return new ResponseEntity<>(new GenericResponse(true, "Updated", projectDetails, 200, LocalDateTime.now()), HttpStatus.OK);

    }
}
