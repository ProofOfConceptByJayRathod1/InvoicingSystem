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
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


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

    private final RateRepository rateRepository;

    public ProjectService(ModelMapper mapper, ProjectRepository projectRepository, ProjectModelRepository projectModelRepository,
                          ClientService clientService, InvoiceCycleRepository invoiceCycleRepository, AccTypeRepository accTypeRepository,
                          CsmRepository csmRepository, SalesPersonRepository salesPersonRepository, LeadSourceRepository leadSourceRepository,
                          MarketingChannelRepository marketingChannelRepository, TechStackRepository techStackRepository, RateRepository rateRepository) {
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
        this.rateRepository = rateRepository;
    }

    public ProjectDetails addProject(ProjectDetails projectDetails) {
        if (projectRepository.findByName(projectDetails.getName()).isPresent()) {
            throw new ProjectAlreadyExistException("Project with name " + projectDetails.getName() + " already exist");
        }
        String createdBy = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        Project project = mapper.map(projectDetails, Project.class);
        project.setCreatedAt(now);
        project.setCreatedBy(createdBy);
        project.setActive(true);

        projectModelRepository.findByModel(project.getProjectModel().getModel()).ifPresent(project::setProjectModel);
        invoiceCycleRepository.findByCycle(projectDetails.getCycle()).ifPresent(project::setInvoiceCycle);
        accTypeRepository.findByAccType(projectDetails.getAccType()).ifPresent(project::setAccType);
        leadSourceRepository.findBySource(projectDetails.getLeadSource()).ifPresent(project::setLeadSource);
        marketingChannelRepository.findByChannel(projectDetails.getChannel()).ifPresent(project::setMarketingChannel);
        csmRepository.findByName(projectDetails.getCsm()).ifPresent(project::setCsm);

        project.setCreatedAt(LocalDateTime.now());
        project.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());

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


        project.setSalesPersons(
                projectDetails.getSalesPersons().stream()
                        .map(salesPersonRepository::findByName)
                        .filter(Optional::isPresent).map(Optional::get).toList()
        );

        project.setClient(clientService.addClient(projectDetails.getClientDetails()));
        projectRepository.save(project);
        return projectDetails;
    }

    public ProjectDetails updateProject(ProjectDetails projectDetails, String projectName) {

        Project project = projectRepository.findByName(projectName).orElseThrow(() -> new ResourceNotFoundException(projectName + " Project name not found"));

        project.setName(projectDetails.getName());
        project.setActiveBillingFlag(projectDetails.isActiveBillingFlag());
        project.setPayModel(projectDetails.getPayModel());
        project.setAccStartDate(projectDetails.getAccStartDate());
        project.setStartDate(projectDetails.getProjectStartDate());
        project.setEndDate(projectDetails.getProjectEndDate());
        project.setContractLink(projectDetails.getContractLink());
        project.setUpdatedAt(LocalDateTime.now());
        project.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());


        ProjectModel projectModel = projectModelRepository.findByModel(projectDetails.getModel()).orElseThrow(() ->
                new ResourceNotFoundException(projectDetails.getModel() + " Project Model not found"));
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


        InvoiceCycle invoiceCycle = invoiceCycleRepository.findByCycle(projectDetails.getCycle()).orElseThrow(
                () -> new ResourceNotFoundException(projectDetails.getCycle() + " Invoice Cycle not found"));
        project.setInvoiceCycle(invoiceCycle);
        project.setInvoiceTerm(projectDetails.getInvoiceTerm());


        AccType accType = accTypeRepository.findByAccType(projectDetails.getAccType()).orElseThrow(
                () -> new ResourceNotFoundException(projectDetails.getAccType() + " Account Type not found"));
        project.setAccType(accType);

        Csm csm = csmRepository.findByName(projectDetails.getCsm()).orElseThrow(() -> new ResourceNotFoundException(projectDetails.getCsm() + " CSM name not found"));
        project.setCsm(csm);

        Collection<SalesPerson> salesPeople = new ArrayList<>(
                projectDetails.getSalesPersons().stream().map(salesPersonName -> salesPersonRepository.findByName(salesPersonName)
                        .orElseThrow(() -> new ResourceNotFoundException(salesPersonName + " SalesPerson name not found"))).toList()
        );

        project.setSalesPersons(salesPeople);

        LeadSource leadSource = leadSourceRepository.findBySource(projectDetails.getSource())
                .orElseThrow(() -> new ResourceNotFoundException(projectDetails.getSource() + " LeadSource name not found"));
        project.setLeadSource(leadSource);

        MarketingChannel marketingChannel = marketingChannelRepository.findByChannel(projectDetails.getChannel())
                .orElseThrow(() -> new ResourceNotFoundException(projectDetails.getChannel() + " Marketing Channel not found"));
        project.setMarketingChannel(marketingChannel);

        projectRepository.save(project);
        return projectDetails;
    }

    public List<ProjectClassicView> searchProject(String projectName) {
        List<Project> projects = projectRepository.searchProjectByName(projectName);
        if (projects.isEmpty()) {
            throw new ResourceNotFoundException(projectName + " Project Name not found");
        } else {
            return projectRepository.searchProjectByName(projectName).stream().map(project -> new ProjectClassicView(project.getName(),
                    project.getProjectModel().getModel(), project.getClient().getName(), project.getClient().getEmail(),
                    project.getInvoiceCycle().getCycle(), project.getPayModel(), project.getAccType().getAccType())).toList();
        }
    }

    public ProjectDetails findByName(String projectName) {
        Project project = projectRepository.findByName(projectName).orElseThrow(() ->
                new ResourceNotFoundException("Project with name " + projectName + " not found")
        );
        return mapper.map(project, ProjectDetails.class);
    }

    public ProjectDetailsViewUpdate updateProjectDetails(String projectName, ProjectDetailsViewUpdate projectDetailsViewUpdate) {

        Project project = projectRepository.findByName(projectName).orElseThrow(() ->
                new ResourceNotFoundException("Project with name " + projectName + " not found")
        );

        project.setDefaultRate(projectDetailsViewUpdate.getDefaultRate());
        project.setName(projectDetailsViewUpdate.getName());
        project.getClient().setName(projectDetailsViewUpdate.getClientName());
        project.getClient().setCompanyName(projectDetailsViewUpdate.getCompanyName());

        csmRepository.findByName(projectDetailsViewUpdate.getCsm()).ifPresent(project::setCsm);
        projectModelRepository.findByModel(projectDetailsViewUpdate.getModel()).ifPresent(project::setProjectModel);

        projectRepository.save(project);
        return projectDetailsViewUpdate;
    }

    public ProjectClassicViewResponse viewProjects(int pageNo, int pageSize, String sortBy, String order) {
        sortBy = Objects.equals(sortBy, "") || sortBy == null ? "name" : sortBy;
        order = Objects.equals(order, "") || order == null ? "ASC" : order;
        Pageable pageable = PageRequest.of(pageNo != 0 ? pageNo - 1 : 0, pageSize != 0 ? pageSize : 10, Sort.Direction.valueOf(order.toUpperCase()), sortBy);
        Page<Project> projects = projectRepository.findAll(pageable);
        List<ProjectClassicView> projectClassicViews = projects.getContent().stream().map(project -> new ProjectClassicView(project.getName(),
                project.getProjectModel().getModel(), project.getClient().getName(), project.getClient().getEmail(),
                project.getInvoiceCycle().getCycle(), project.getPayModel(), project.getAccType().getAccType())).collect(Collectors.toList());
        return new ProjectClassicViewResponse(projectClassicViews, projects.getTotalPages(), projects.getTotalElements());
    }
}
