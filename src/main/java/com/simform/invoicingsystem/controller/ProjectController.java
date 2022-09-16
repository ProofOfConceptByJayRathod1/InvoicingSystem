package com.simform.invoicingsystem.controller;

import com.simform.invoicingsystem.dto.GenericResponse;
import com.simform.invoicingsystem.dto.ProjectDetails;
import com.simform.invoicingsystem.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;


@Controller
@RequestMapping("/projects")

public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/add")
    public ResponseEntity<GenericResponse> addProject(@RequestBody ProjectDetails projectDetails) {
        projectService.addProject(projectDetails);

        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setMessage("Created");
        genericResponse.setCode(201);
        genericResponse.setTimestamp(LocalDateTime.now());
        genericResponse.setSuccess(true);
        return new ResponseEntity<>(genericResponse, HttpStatus.CREATED);
    }
}
