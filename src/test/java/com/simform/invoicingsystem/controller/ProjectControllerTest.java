package com.simform.invoicingsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.simform.invoicingsystem.service.ProjectService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();


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

/*

    @Test
    void updateProjectTest() throws Exception {
        ClientDetails clientDetails = new ClientDetails("XYZ", "Companyname", "xyz@gmail.com", "Ahmedabad", "Gujarat", "India", "1234556");
        Collection<String> salesPerson = Arrays.asList("AAA", "BBB");
        ProjectDetails projectDetails = new ProjectDetails("Project", "model", clientDetails, "cycle", 0, "paymodel", "accType",
                LocalDate.now(), LocalDate.now(), LocalDate.now(), "csm", salesPerson, "contractLink", "source", "channel", true);

        Mockito.when(projectService.updateProject(projectDetails, "XYZ")).thenReturn(projectDetails);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/update/" + "XYZ")
                        .content(objectMapper.writeValueAsString(projectDetails))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
*/

}
