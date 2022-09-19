package com.simform.invoicingsystem.service;

import com.simform.invoicingsystem.dto.ProjectDetail;
import com.simform.invoicingsystem.entity.Client;
import com.simform.invoicingsystem.entity.Project;
import com.simform.invoicingsystem.entity.SalesPerson;
import com.simform.invoicingsystem.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

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
    private final TechnologyRepository technologyRepository;

    public ProjectService(ModelMapper mapper, ProjectRepository projectRepository, ProjectModelRepository projectModelRepository, ClientService clientService, InvoiceCycleRepository invoiceCycleRepository, AccTypeRepository accTypeRepository, CsmRepository csmRepository, SalesPersonRepository salesPersonRepository, LeadSourceRepository leadSourceRepository, MarketingChannelRepository marketingChannelRepository, TechnologyRepository technologyRepository) {
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
        this.technologyRepository = technologyRepository;
    }

    public void addProject(ProjectDetail projectDetail) {

        Project project = mapper.map(projectDetail, Project.class);
        project.setActive(true);
        projectModelRepository.findByModel(project.getProjectModel().getModel()).ifPresent(project::setProjectModel);
        invoiceCycleRepository.findByCycle(project.getInvoiceCycle().getCycle()).ifPresent(project::setInvoiceCycle);
        accTypeRepository.findByAccType(project.getAccType().getAccType()).ifPresent(project::setAccType);
        leadSourceRepository.findBySource(project.getLeadSource().getSource()).ifPresent(project::setLeadSource);
        marketingChannelRepository.findByChannel(project.getMarketingChannel().getChannel()).ifPresent(project::setMarketingChannel);
        csmRepository.findByName(project.getCsm().getName()).ifPresent(project::setCsm);
        project.setTechnologies(technologyRepository.findAll());
        project.setCreatedAt(LocalDateTime.now());
        project.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());

        Collection<SalesPerson> salesPeople = project.getSalesPersons().stream()
                .map(salesPerson -> salesPersonRepository.findByName(salesPerson.getName()))
                .filter(Optional::isPresent).map(Optional::get).toList();
        project.setSalesPersons(salesPeople);

        Client client = clientService.addClient(projectDetail.getClientDetails());
        project.setClient(client);

        projectRepository.save(project);
    }
}
