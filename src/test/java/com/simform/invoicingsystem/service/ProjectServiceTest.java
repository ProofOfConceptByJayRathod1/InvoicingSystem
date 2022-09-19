package com.simform.invoicingsystem.service;

import com.simform.invoicingsystem.entity.AccType;
import com.simform.invoicingsystem.entity.Client;
import com.simform.invoicingsystem.entity.Csm;
import com.simform.invoicingsystem.entity.InvoiceCycle;
import com.simform.invoicingsystem.entity.LeadSource;
import com.simform.invoicingsystem.entity.MarketingChannel;
import com.simform.invoicingsystem.entity.Project;
import com.simform.invoicingsystem.entity.ProjectModel;
import com.simform.invoicingsystem.entity.SalesPerson;
import com.simform.invoicingsystem.repository.ProjectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ProjectServiceTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProjectRepository projectRepository;

  @Test
  public void updateProjectTest() throws Exception {
    InvoiceCycle invoiceCycle = new InvoiceCycle(1L, "cycle", LocalDateTime.now(), "Jay", LocalDateTime.now(), "Jay", LocalDateTime.now(), "Jay");
    AccType accType = new AccType(1L, "accType", LocalDateTime.now(), "Jay", LocalDateTime.now(), "Jay", LocalDateTime.now(), "Jay");
    LeadSource leadSource = new LeadSource(1L, "source", LocalDateTime.now(), "Jay", LocalDateTime.now(), "Jay", LocalDateTime.now(), "Jay");
    Csm csm = new Csm(1L, "csm", LocalDateTime.now(), "Jay", LocalDateTime.now(), "Jay", LocalDateTime.now(), "Jay");
    ProjectModel projectModel = new ProjectModel(1L, "projectModel", LocalDateTime.now(), "Jay", LocalDateTime.now(), "Jay", LocalDateTime.now(), "Jay");
    Client client = new Client(1L, "client", "companyName", "abc@gmail.com", "Ahmedabad", "Gujarat", "India", "12345678", LocalDateTime.now(), "Jay", LocalDateTime.now(), "Jay", LocalDateTime.now(), "Jay");
    MarketingChannel marketingChannel = new MarketingChannel(1L, "channel", LocalDateTime.now(), "Jay", LocalDateTime.now(), "Jay", LocalDateTime.now(), "Jay");
    SalesPerson salesPerson = new SalesPerson(1l, "jay", LocalDateTime.now(), "Jay", LocalDateTime.now(), "Jay", LocalDateTime.now(), "Jay");
    Collection<SalesPerson> collectionSalesPerson = Arrays.asList(salesPerson);
    Project project = new Project(1L, "project", "model", 0, LocalDate.now(), true, "contractLink", LocalDate.now(), LocalDate.now(), true, LocalDateTime.now(), "Jay", LocalDateTime.now(), "Jay", LocalDateTime.now(), "Jay", invoiceCycle, accType, leadSource, csm, projectModel, client, marketingChannel, collectionSalesPerson);

    Mockito.when(projectRepository.findByName("project")).thenReturn(Optional.of(project));
    Mockito.when(projectRepository.save(project)).thenReturn(project);
    Assertions.assertEquals(project, project);

  }

}