package com.simform.invoicingsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.simform.invoicingsystem.dto.ClientDetails;
import com.simform.invoicingsystem.dto.ProjectDetail;
import com.simform.invoicingsystem.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ProjectControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProjectService projectService;

  ObjectMapper objectMapper = JsonMapper.builder()
    .addModule(new JavaTimeModule())
    .build();

  @Test
  void updateProjectTest() throws Exception {
    ClientDetails clientDetails = new ClientDetails("XYZ", "Companyname", "xyz@gmail.com", "Ahmedabad", "Gujarat", "India", "1234556");
    Collection<String> salesPerson = Arrays.asList("AAA", "BBB");
    ProjectDetail projectDetail = new ProjectDetail("Project", "model", clientDetails, "cycle", 0, "paymodel", "accType", LocalDate.now(), LocalDate.now(), LocalDate.now(), "csm", salesPerson, "contractLink", "source", "channel", true);

    Mockito.when(projectService.updateProject(projectDetail, "XYZ")).thenReturn(projectDetail);

    mockMvc.perform(MockMvcRequestBuilders
        .put("/update/" + "XYZ")
        .content(objectMapper.writeValueAsString(projectDetail))
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());

  }

}


