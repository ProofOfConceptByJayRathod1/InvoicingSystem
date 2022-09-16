package com.simform.invoicingsystem.controller;

import com.simform.invoicingsystem.entity.Project;
import com.simform.invoicingsystem.service.TestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("/testAdd")
    public Project addProject(){
        return testService.getProject();
    }
}
