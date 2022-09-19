package com.simform.invoicingsystem.service;

import com.simform.invoicingsystem.dto.ProjectDetail;
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

    public ProjectDetail updateProject(ProjectDetail projectDetail, String projectName) {

        Project project = projectRepository.findByName(projectName).orElseThrow(() -> new ResourceNotFoundException(projectName+" Project name not found"));

        project.setName(projectDetail.getName());

        ProjectModel projectModel = projectModelRepository.findByModel(projectDetail.getModel()).orElseThrow(() -> new ResourceNotFoundException(projectDetail.getModel()+" Project Model not found"));
        project.setProjectModel(projectModel);

        Client client = project.getClient();
        client.setName(projectDetail.getClientDetails().getName());
        client.setCompanyName(projectDetail.getClientDetails().getCompanyName());
        client.setEmail(projectDetail.getClientDetails().getEmail());
        client.setCity(projectDetail.getClientDetails().getCity());
        client.setState(projectDetail.getClientDetails().getState());
        client.setCountry(projectDetail.getClientDetails().getCountry());
        client.setPhoneNumber(projectDetail.getClientDetails().getPhoneNumber());
        project.setClient(client);

        InvoiceCycle invoiceCycle = invoiceCycleRepository.findByCycle(projectDetail.getCycle()).orElseThrow(() -> new ResourceNotFoundException(projectDetail.getCycle()+" Invoice Cycle not found"));
        project.setInvoiceCycle(invoiceCycle);

        project.setInvoiceTerm(projectDetail.getInvoiceTerm());
        project.setPayModel(projectDetail.getPayModel());

        AccType accType = accTypeRepository.findByAccType(projectDetail.getAccType()).orElseThrow(() -> new ResourceNotFoundException(projectDetail.getAccType()+" Account Type not found"));
        project.setAccType(accType);

        project.setAccStartDate(projectDetail.getAccStartDate());
        project.setStartDate(projectDetail.getProjectStartDate());
        project.setEndDate(projectDetail.getProjectEndDate());

        Csm csm = csmRepository.findByName(projectDetail.getCsm()).orElseThrow(() -> new ResourceNotFoundException(projectDetail.getCsm()+" CSM name not found"));
        project.setCsm(csm);

        List<SalesPerson> salesPersonListNew = new ArrayList<>();
        List<String> salesPersonName = (List<String>) projectDetail.getSalesPersons();
        SalesPerson salesPerson;

        for (int i = 0; i < salesPersonName.size(); i++) {
            String updatedSalesPerson=salesPersonName.get(i);
            salesPerson = salesPersonRepository.findByName(updatedSalesPerson).orElseThrow(() -> new ResourceNotFoundException(updatedSalesPerson+" SalesPerson name not found"));
            salesPersonListNew.add(salesPerson);
        }
        project.setSalesPersons(salesPersonListNew);
        project.setContractLink(projectDetail.getContractLink());

        LeadSource leadSource = leadSourceRepository.findBySource(projectDetail.getSource()).orElseThrow(() -> new ResourceNotFoundException(projectDetail.getSource()+" LeadSource name not found"));
        project.setLeadSource(leadSource);

        MarketingChannel marketingChannel = marketingChannelRepository.findByChannel(projectDetail.getChannel()).orElseThrow(() -> new ResourceNotFoundException(projectDetail.getChannel()+" Marketing Channel not found"));
        project.setMarketingChannel(marketingChannel);

        project.setActiveBillingFlag(projectDetail.isActiveBillingFlag());

        project.setUpdatedAt(LocalDateTime.now());
        project.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());

        projectRepository.save(project);
        return projectDetail;
    }
}
