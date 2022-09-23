package com.simform.invoicingsystem.service;

import com.simform.invoicingsystem.dto.ProjectClassicView;
import com.simform.invoicingsystem.dto.ProjectClassicViewResponse;
import com.simform.invoicingsystem.dto.ProjectDetails;
import com.simform.invoicingsystem.dto.ProjectDetailsViewUpdate;
import com.simform.invoicingsystem.entity.*;
import com.simform.invoicingsystem.exception.ProjectAlreadyExistException;
import com.simform.invoicingsystem.exception.ResourceNotFoundException;
import com.simform.invoicingsystem.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@Transactional
public class ProjectService {

    private final ModelMapper mapper;
    private final ProjectRepository projectRepository;
    private final ProjectModelRepository projectModelRepository;
    private final ClientService clientService;
    private final InvoiceCycleRepository invoiceCycleRepository;
    private final AccTypeRepository accTypeRepository;
    private final CsmRepository csmRepository;
    private final SalesPersonRepository salesPersonRepository;
    private final LeadSourceRepository leadSourceRepository;
    private final MarketingChannelRepository marketingChannelRepository;
    private final TechStackRepository techStackRepository;

    private final CompanyRepository companyRepository;
    private final RateRepository rateRepository;

    public ProjectService(ModelMapper mapper, ProjectRepository projectRepository, ProjectModelRepository projectModelRepository,
                          ClientService clientService, InvoiceCycleRepository invoiceCycleRepository, AccTypeRepository accTypeRepository,
                          CsmRepository csmRepository, SalesPersonRepository salesPersonRepository, LeadSourceRepository leadSourceRepository,
                          MarketingChannelRepository marketingChannelRepository, TechStackRepository techStackRepository, CompanyRepository companyRepository,
                          RateRepository rateRepository) {
        this.mapper = mapper;
        this.projectRepository = projectRepository;
        this.projectModelRepository = projectModelRepository;
        this.clientService = clientService;
        this.invoiceCycleRepository = invoiceCycleRepository;
        this.accTypeRepository = accTypeRepository;
        this.csmRepository = csmRepository;
        this.salesPersonRepository = salesPersonRepository;
        this.leadSourceRepository = leadSourceRepository;
        this.marketingChannelRepository = marketingChannelRepository;
        this.techStackRepository = techStackRepository;
        this.companyRepository = companyRepository;
        this.rateRepository = rateRepository;
    }

    public ProjectDetails addProject(ProjectDetails projectDetails) {
        if (projectRepository.findByName(projectDetails.getName()).isPresent()) {
            throw new ProjectAlreadyExistException("Project with name '" + projectDetails.getName() + "' already exist");
        }

        if (projectRepository.existsByZohoProjectId(projectDetails.getZohoProjectId())) {
            throw new ProjectAlreadyExistException("Project with Zoho Id '" + projectDetails.getZohoProjectId() + "' already exist");
        }

        String createdBy = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        Project project = mapper.map(projectDetails, Project.class);
        project.setCreatedAt(now);
        project.setCreatedBy(createdBy);
        project.setActive(true);
        project.setCreatedAt(now);
        project.setCreatedBy(createdBy);

        projectModelRepository.findByModel(project.getProjectModel().getModel()).ifPresent(project::setProjectModel);
        invoiceCycleRepository.findByCycle(projectDetails.getCycle()).ifPresent(project::setInvoiceCycle);
        accTypeRepository.findByAccType(projectDetails.getAccType()).ifPresent(project::setAccType);
        leadSourceRepository.findBySource(projectDetails.getLeadSource()).ifPresent(project::setLeadSource);
        marketingChannelRepository.findByChannel(projectDetails.getChannel()).ifPresent(project::setMarketingChannel);

        project.setRates(
                techStackRepository.findAll().stream().map(techStackRate -> {
                    Rate rate = new Rate();
                    rate.setRate("0");
                    rate.setStack(techStackRate.getName());
                    rate.setCreatedAt(now);
                    rate.setCreatedBy(createdBy);
                    rate.setTechStack(techStackRate);
                    return rateRepository.save(rate);
                }).toList()
        );

        project.setCsm(
                csmRepository.findByName(projectDetails.getCsm()).orElseGet(() -> {
                    Csm csm = new Csm();
                    csm.setName(projectDetails.getCsm());
                    csm.setCreatedAt(now);
                    csm.setCreatedBy(createdBy);
                    return csmRepository.save(csm);
                })
        );


        Collection<SalesPerson> salesPeople = new ArrayList<>(
                projectDetails.getSalesPersons().stream().map(salesPersonName ->
                        salesPersonRepository.findByName(salesPersonName)
                                .orElseGet(() -> {
                                    SalesPerson salesPerson = new SalesPerson();
                                    salesPerson.setName(salesPersonName);
                                    salesPerson.setCreatedAt(now);
                                    salesPerson.setCreatedBy(createdBy);
                                    return salesPersonRepository.save(salesPerson);
                                })
                ).toList());
        project.setSalesPersons(salesPeople);


        project.setClient(clientService.addClient(projectDetails.getClientDetails()));
        project = projectRepository.save(project);
        //set sale persons
        projectDetails.setSalesPersons(project.getSalesPersons().stream().map(SalesPerson::getName).toList());
        return projectDetails;
    }

    public ProjectDetails updateProject(ProjectDetails projectDetails) {

        String projectName = projectDetails.getName();
        Project project = projectRepository.findByName(projectName).orElseThrow(() ->
                new ResourceNotFoundException(projectName + " Project name not found")
        );

        String createdBy = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();


        project.setName(projectDetails.getName());
        project.setActiveBillingFlag(projectDetails.isActiveBillingFlag());
        project.setPayModel(projectDetails.getPayModel());
        project.setAccStartDate(projectDetails.getAccStartDate());
        project.setStartDate(projectDetails.getProjectStartDate());
        project.setEndDate(projectDetails.getProjectEndDate());
        project.setContractLink(projectDetails.getContractLink());
        project.setUpdatedAt(LocalDateTime.now());
        project.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());

        Client client = project.getClient();
        client.setName(projectDetails.getClientDetails().getName());
        client.setCompany(
                companyRepository.findByName(projectDetails.getClientDetails().getCompanyName()).orElseGet(() -> {
                    Company company = new Company();
                    company.setName(projectDetails.getClientDetails().getCompanyName());

                    company.setCreatedAt(now);
                    company.setCreatedBy(createdBy);
                    return companyRepository.save(company);
                })
        );

        client.setEmail(projectDetails.getClientDetails().getEmail());
        client.setCity(projectDetails.getClientDetails().getCity());
        client.setState(projectDetails.getClientDetails().getState());
        client.setCountry(projectDetails.getClientDetails().getCountry());
        client.setPhoneNumber(projectDetails.getClientDetails().getPhoneNumber());
        project.setClient(client);

        project.setInvoiceTerm(projectDetails.getInvoiceTerm());
        projectModelRepository.findByModel(projectDetails.getModel()).ifPresent(project::setProjectModel);
        invoiceCycleRepository.findByCycle(projectDetails.getCycle()).ifPresent(project::setInvoiceCycle);
        accTypeRepository.findByAccType(projectDetails.getAccType()).ifPresent(project::setAccType);
        leadSourceRepository.findBySource(projectDetails.getSource()).ifPresent(project::setLeadSource);
        marketingChannelRepository.findByChannel(projectDetails.getChannel()).ifPresent(project::setMarketingChannel);


        project.setCsm(
                csmRepository.findByName(projectDetails.getCsm()).orElseGet(() -> {
                    Csm csm = new Csm();
                    csm.setName(projectDetails.getCsm());
                    csm.setCreatedAt(now);
                    csm.setCreatedBy(createdBy);
                    return csmRepository.save(csm);
                })
        );


        Collection<SalesPerson> salesPeople = new ArrayList<>(
                projectDetails.getSalesPersons().stream().map(salesPersonName ->
                        salesPersonRepository.findByName(salesPersonName)
                                .orElseGet(() -> {
                                    SalesPerson salesPerson = new SalesPerson();
                                    salesPerson.setName(salesPersonName);
                                    salesPerson.setCreatedAt(now);
                                    salesPerson.setCreatedBy(createdBy);
                                    return salesPersonRepository.save(salesPerson);
                                })
                ).toList());
        project.setSalesPersons(salesPeople);
        //set sale persons
        projectDetails.setSalesPersons(project.getSalesPersons().stream().map(SalesPerson::getName).toList());
        projectRepository.save(project);
        return projectDetails;
    }

    public ProjectClassicViewResponse searchProject(String projectName, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo != 0 ? pageNo - 1 : 0, pageSize != 0 ? pageSize : 10);
        Page<Project> projects = projectRepository.searchProjectByName(projectName, pageable);

        if (projects.isEmpty()) {
            throw new ResourceNotFoundException(projectName + " Project Name not found");
        } else {
            List<ProjectClassicView> projectClassicViews = projects.getContent().stream()
                    .map(project -> new ProjectClassicView(project.getName(), project.getProjectModel().getModel(),
                            project.getClient().getName(), project.getClient().getEmail(),
                            project.getInvoiceCycle().getCycle(), project.getPayModel(),
                            project.getAccType().getAccType())).toList();

            return new ProjectClassicViewResponse(projectClassicViews, projects.getTotalPages(), projects.getTotalElements());
        }
    }

    public ProjectDetails findByName(String projectName) {
        Project project = projectRepository.findByName(projectName).orElseThrow(() ->
                new ResourceNotFoundException("Project with name " + projectName + " not found")
        );
        ProjectDetails projectDetails = mapper.map(project, ProjectDetails.class);
        projectDetails.setSalesPersons(project.getSalesPersons().stream().map(SalesPerson::getName).toList());
        return projectDetails;
    }

    public ProjectDetailsViewUpdate updateProjectDetails(ProjectDetailsViewUpdate projectDetailsViewUpdate) {
        String projectName = projectDetailsViewUpdate.getName();
        Project project = projectRepository.findByName(projectName).orElseThrow(() ->
                new ResourceNotFoundException("Project with name " + projectName + " not found")
        );

        project.setDefaultRate(projectDetailsViewUpdate.getDefaultRate());
        project.setName(projectDetailsViewUpdate.getName());
        project.getClient().setName(projectDetailsViewUpdate.getClientName());
        project.getClient().setCompany(
                companyRepository.findByName(projectDetailsViewUpdate.getCompanyName()).orElseGet(() -> {
                    Company company = new Company();
                    company.setName(projectDetailsViewUpdate.getCompanyName());
                    company.setCreatedAt(LocalDateTime.now());
                    company.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
                    return companyRepository.save(company);
                })
        );
        csmRepository.findByName(projectDetailsViewUpdate.getCsm()).ifPresent(project::setCsm);
        projectModelRepository.findByModel(projectDetailsViewUpdate.getModel()).ifPresent(project::setProjectModel);

        projectRepository.save(project);
        return projectDetailsViewUpdate;
    }

    public ProjectClassicViewResponse viewProjects(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo != 0 ? pageNo - 1 : 0, pageSize != 0 ? pageSize : 10);
        Page<Project> projects = projectRepository.findAll(pageable);

        List<ProjectClassicView> projectClassicViews = projects.getContent().stream()
                .map(project -> new ProjectClassicView(project.getName(), project.getProjectModel().getModel(),
                        project.getClient().getName(), project.getClient().getEmail(),
                        project.getInvoiceCycle().getCycle(), project.getPayModel(),
                        project.getAccType().getAccType())).toList();

        return new ProjectClassicViewResponse(projectClassicViews, projects.getTotalPages(), projects.getTotalElements());
    }
}
