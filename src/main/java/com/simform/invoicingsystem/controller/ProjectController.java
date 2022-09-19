package com.simform.invoicingsystem.controller;

import com.simform.invoicingsystem.dto.GenericResponse;
import com.simform.invoicingsystem.dto.ProjectDetail;
import com.simform.invoicingsystem.service.ProjectService;
import com.simform.invoicingsystem.util.EmptyJsonBody;
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

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/add")
    public ResponseEntity<GenericResponse> addProject(@RequestBody ProjectDetail projectDetails) {
        projectService.addProject(projectDetails);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setMessage("Created");
        genericResponse.setCode(201);
        genericResponse.setTimestamp(LocalDateTime.now());
        genericResponse.setSuccess(true);
        genericResponse.setData(new EmptyJsonBody());
        return new ResponseEntity<>(genericResponse, HttpStatus.CREATED);
    }
}
