package com.simform.invoicingsystem.controller;

import com.simform.invoicingsystem.dto.ProjectDetail;
import com.simform.invoicingsystem.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Operation(summary = "Project Update API", description = "Here, some of the fields of the project table will be updated.", tags = {"Project Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project updated successfully"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PutMapping(value = "/update/{projectName}")
    public ResponseEntity<ProjectDetail> updateProject(HttpServletRequest request, @RequestBody ProjectDetail projectDetail, @PathVariable("projectName") String projectName) {
        return new ResponseEntity<>(projectService.updateProject(request, projectDetail, projectName), HttpStatus.OK);
    }
}
