package com.simform.invoicingsystem.service;

import com.simform.invoicingsystem.dto.ProjectDetails;
import com.simform.invoicingsystem.entity.*;
import com.simform.invoicingsystem.exception.ResourceNotFoundException;
import com.simform.invoicingsystem.repository.*;
import com.simform.invoicingsystem.util.JwtUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProjectService {

    private ProjectRepository projectRepository;

    private ProjectModelRepository projectModelRepository;

    private InvoiceCycleRepository invoiceCycleRepository;

    private AccTypeRepository accTypeRepository;

    private SalesPersonRepository salesPersonRepository;

    private LeadSourceRepository leadSourceRepository;

    private MarketingChannelRepository marketingChannelRepository;

    private CsmRepository csmRepository;

    private ClientRepository clientRepository;

    private JwtUtil jwtUtil;

    public ProjectService(ProjectRepository projectRepository, ProjectModelRepository projectModelRepository, InvoiceCycleRepository invoiceCycleRepository, AccTypeRepository accTypeRepository, SalesPersonRepository salesPersonRepository, LeadSourceRepository leadSourceRepository, MarketingChannelRepository marketingChannelRepository, CsmRepository csmRepository, ClientRepository clientRepository, JwtUtil jwtUtil) {
        this.projectRepository = projectRepository;
        this.projectModelRepository = projectModelRepository;
        this.invoiceCycleRepository = invoiceCycleRepository;
        this.accTypeRepository = accTypeRepository;
        this.salesPersonRepository = salesPersonRepository;
        this.leadSourceRepository = leadSourceRepository;
        this.marketingChannelRepository = marketingChannelRepository;
        this.csmRepository = csmRepository;
        this.clientRepository = clientRepository;
        this.jwtUtil = jwtUtil;
    }

    public ProjectDetails  updateProject(ProjectDetails projectDetails, String projectName) {

        Project project = projectRepository.findByName(projectName).orElseThrow(() -> new ResourceNotFoundException(projectName+" Project name not found"));

        project.setName(projectDetails.getName());

        ProjectModel projectModel = projectModelRepository.findByModel(projectDetails.getModel()).orElseThrow(() -> new ResourceNotFoundException(projectDetails.getModel()+" Project Model not found"));
        project.setProjectModel(projectModel);

        Client client = project.getClient();
        client.setName(projectDetails.getClientDetails().getName());
        client.setCompanyName(projectDetails.getClientDetails().getCompanyName());
        client.setEmail(projectDetails.getClientDetails().getEmail());
        client.setCity(projectDetails.getClientDetails().getCity());
        client.setState(projectDetails.getClientDetails().getState());
        client.setCountry(projectDetails.getClientDetails().getCountry());
        client.setPhoneNumber(projectDetails.getClientDetails().getPhoneNumber());
        project.setClient(client);

        InvoiceCycle invoiceCycle = invoiceCycleRepository.findByCycle(projectDetails.getCycle()).orElseThrow(() -> new ResourceNotFoundException(projectDetails.getCycle()+" Invoice Cycle not found"));
        project.setInvoiceCycle(invoiceCycle);

        project.setInvoiceTerm(projectDetails.getInvoiceTerm());
        project.setPayModel(projectDetails.getPayModel());

        AccType accType = accTypeRepository.findByAccType(projectDetails.getAccType()).orElseThrow(() -> new ResourceNotFoundException(projectDetails.getAccType()+" Account Type not found"));
        project.setAccType(accType);

        project.setAccStartDate(projectDetails.getAccStartDate());
        project.setStartDate(projectDetails.getProjectStartDate());
        project.setEndDate(projectDetails.getProjectEndDate());

        Csm csm = csmRepository.findByName(projectDetails.getCsm()).orElseThrow(() -> new ResourceNotFoundException(projectDetails.getCsm()+" CSM name not found"));
        project.setCsm(csm);

        List<SalesPerson> salesPersonListNew = new ArrayList<>();
        List<String> salesPersonName = (List<String>) projectDetails.getSalesPersons();
        SalesPerson salesPerson;

        for (int i = 0; i < salesPersonName.size(); i++) {
            String updatedSalesPerson=salesPersonName.get(i);
            salesPerson = salesPersonRepository.findByName(updatedSalesPerson).orElseThrow(() -> new ResourceNotFoundException(updatedSalesPerson+" SalesPerson name not found"));
            salesPersonListNew.add(salesPerson);
        }
        project.setSalesPersons(salesPersonListNew);
        project.setContractLink(projectDetails.getContractLink());

        LeadSource leadSource = leadSourceRepository.findBySource(projectDetails.getSource()).orElseThrow(() -> new ResourceNotFoundException(projectDetails.getSource()+" LeadSource name not found"));
        project.setLeadSource(leadSource);

        MarketingChannel marketingChannel = marketingChannelRepository.findByChannel(projectDetails.getChannel()).orElseThrow(() -> new ResourceNotFoundException(projectDetails.getChannel()+" Marketing Channel not found"));
        project.setMarketingChannel(marketingChannel);

        project.setActiveBillingFlag(projectDetails.isActiveBillingFlag());

        project.setUpdatedAt(LocalDateTime.now());
        project.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());

        projectRepository.save(project);
        return projectDetails;
    }
}
