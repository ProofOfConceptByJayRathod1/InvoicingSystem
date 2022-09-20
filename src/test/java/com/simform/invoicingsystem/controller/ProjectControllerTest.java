package com.simform.invoicingsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.simform.invoicingsystem.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

public class ProjectControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    ObjectMapper objectMapper = new ObjectMapper();

    ObjectWriter objectWriter = objectMapper.writer();

//    @Test
//    void addProjectSuccess() throws Exception {
//        ClientDetails clientDetails = new ClientDetails("Chris", "Company Name", "chris@gmail.com", "LA", "CA", "USA", "1528252585");
//        ProjectDetails projectDetails = new ProjectDetails();
//
//        Mockito.when(projectService.addProject(projectDetails)).thenReturn(projectDetails);
//
//        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
//                .post("/projects/add")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectWriter.writeValueAsString(projectDetails));
//
//        mockMvc.perform(mockRequest)
//                .andExpect(status().isOk())
//                .andExpect(content().string(objectWriter.writeValueAsString(projectDetails)));
//    }
}
