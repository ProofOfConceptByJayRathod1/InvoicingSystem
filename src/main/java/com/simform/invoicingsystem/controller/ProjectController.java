package com.simform.invoicingsystem.controller;

import com.simform.invoicingsystem.dto.*;
import com.simform.invoicingsystem.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/projects")
@Slf4j
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Operation(summary = "Add Project API", description = "Here, user can add new project", tags = {"Project Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project added successfully"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("/add")
    public ResponseEntity<GenericResponse> addProject(@Validated @RequestBody ProjectDetails projectDetails) {
        log.debug("Entering /projects/add end-point");
        projectDetails = projectService.addProject(projectDetails);
        log.info("Project '" + projectDetails.getName() + "' added successfully");
        log.debug("Exiting /projects/add end-point");
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
        log.debug("Entering /projects/update/{projectName} end-point");
        projectDetails = projectService.updateProject(projectDetails, projectName);
        log.info("Project '" + projectDetails.getName() + "' Updated successfully");
        log.debug("Exiting /projects/update/{projectName} end-point");
        return new ResponseEntity<>(new GenericResponse(true, "Updated", projectDetails, 200, LocalDateTime.now()), HttpStatus.OK);

    }

    @Operation(summary = "Search Project API", description = "Here, one can search all the details of project by entering Project Name.", tags = {"Project Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project found successfully"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")

    })
    @GetMapping(value = "/searchProject")
    public ResponseEntity<GenericResponse> searchProject(@RequestParam String projectName) {
        log.debug("Entering /projects/searchProject end-point");
        List<ProjectClassicView> projectClassicView = projectService.searchProject(projectName);
        log.info("Project Name with '" + projectName + "' found successfully");
        log.debug("Exiting /projects/searchProject end-point");
        return new ResponseEntity<>(new GenericResponse(true, "Found Successfully", projectClassicView, 200, LocalDateTime.now()), HttpStatus.OK);
    }

    @Operation(summary = "Project Find By Name API", description = "Here, all project details will be returned.", tags = {"Project Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project get successfully"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping(value = "/findByName")
    public ResponseEntity<GenericResponse> findByName(@RequestParam String projectName) {
        log.debug("Entering /projects/findByName end-point");
        ProjectDetails projectDetails = projectService.findByName(projectName);
        log.info("Project '" + projectName + "' get successfully");
        log.debug("Exiting /projects/findByName end-point");
        return new ResponseEntity<>(new GenericResponse(true, "Get", projectDetails, 200, LocalDateTime.now()), HttpStatus.OK);

    }

    @Operation(summary = "Project Details Update API", description = "Here, details can be updated.", tags = {"Project Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project details updated successfully"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PutMapping(value = "/details/update")
    public ResponseEntity<GenericResponse> updateProjectDetailsViewUpdate(@RequestParam String projectName, @RequestBody ProjectDetailsViewUpdate projectDetailsViewUpdate) {
        log.debug("Entering /projects/details/update end-point");
        projectDetailsViewUpdate = projectService.updateProjectDetails(projectName, projectDetailsViewUpdate);
        log.info("Project '" + projectName + "' Updated successfully");
        log.debug("Exiting /projects/details/update end-point");
        return new ResponseEntity<>(new GenericResponse(true, "Updated", projectDetailsViewUpdate, 200, LocalDateTime.now()), HttpStatus.OK);
    }

    @Operation(summary = "View Project API", description = "Here, project details will be viewed in paginated format", tags = {"Project Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Projects displayed successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    @GetMapping("/")
    public ResponseEntity<GenericResponse> viewProjects(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                        @RequestParam(value = "sortBy", defaultValue = "name") String sortBy, @RequestParam(value = "order", defaultValue = "ASC") String order) {
        log.debug("Entering /projects/ end-point");
        ProjectClassicViewResponse projects = projectService.viewProjects(pageNo, pageSize, sortBy, order);
        log.debug("Exiting /projects/ end-point");
        return new ResponseEntity<>(new GenericResponse(true, "Fetched", projects, 200, LocalDateTime.now()), HttpStatus.OK);
    }

}
