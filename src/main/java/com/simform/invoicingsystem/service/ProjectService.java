package com.simform.invoicingsystem.service;

import com.simform.invoicingsystem.dto.ClientDetails;
import com.simform.invoicingsystem.dto.ProjectDetail;
import com.simform.invoicingsystem.dto.ProjectDetails;
import com.simform.invoicingsystem.entity.*;
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

    private ModelMapper mapper;
    private final ProjectRepository projectRepository;
    private final ProjectModelRepository projectModelRepository;
    private final ClientRepository clientRepository;
    private final InvoiceCycleRepository invoiceCycleRepository;
    private final AccTypeRepository accTypeRepository;
    private final CsmRepository csmRepository;
    private final SalesPersonRepository salesPersonRepository;
    private final LeadSourceRepository leadSourceRepository;
    private final MarketingChannelRepository marketingChannelRepository;

    private final TechnologyRepository technologyRepository;
    private final RateRepository rateRepository;

    public ProjectService(ProjectRepository projectRepository, ProjectModelRepository projectModelRepository, ClientRepository clientRepository, InvoiceCycleRepository invoiceCycleRepository, AccTypeRepository accTypeRepository, CsmRepository csmRepository, SalesPersonRepository salesPersonRepository, LeadSourceRepository leadSourceRepository, MarketingChannelRepository marketingChannelRepository, TechnologyRepository technologyRepository, RateRepository rateRepository) {
        this.projectRepository = projectRepository;
        this.projectModelRepository = projectModelRepository;
        this.clientRepository = clientRepository;
        this.invoiceCycleRepository = invoiceCycleRepository;
        this.accTypeRepository = accTypeRepository;
        this.csmRepository = csmRepository;
        this.salesPersonRepository = salesPersonRepository;
        this.leadSourceRepository = leadSourceRepository;
        this.marketingChannelRepository = marketingChannelRepository;
        this.technologyRepository = technologyRepository;
        this.rateRepository = rateRepository;
    }

    public void addProject(ProjectDetail projectDetails) {

        Project project = mapper.map(projectDetails, Project.class);
        projectModelRepository.findByModel(project.getProjectModel().getModel()).ifPresent(project::setProjectModel);
        invoiceCycleRepository.findByCycle(project.getInvoiceCycle().getCycle()).ifPresent(project::setInvoiceCycle);
        accTypeRepository.findByAccType(project.getAccType().getAccType()).ifPresent(project::setAccType);
        leadSourceRepository.findBySource(project.getLeadSource().getSource()).ifPresent(project::setLeadSource);
        marketingChannelRepository.findByChannel(project.getMarketingChannel().getChannel()).ifPresent(project::setMarketingChannel);
        csmRepository.findByName(project.getCsm().getName()).ifPresent(project::setCsm);

        Collection<SalesPerson> salesPeople = project.getSalesPersons().stream()
                .map(salesPerson -> salesPersonRepository.findByName(salesPerson.getName()))
                .filter(Optional::isPresent).map(Optional::get).toList();
        project.setSalesPersons(salesPeople);
        Client client = addClient(projectDetails.getClientDetails());

        project.setClient(client);

        project.setCreatedAt(LocalDateTime.now());
        project.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());

        projectRepository.save(project);
    }

    public Client addClient(ClientDetails clientDetails) {
        return clientRepository.findByEmail(clientDetails.getEmail()).orElseGet(() -> {
            Client client = mapper.map(clientDetails, Client.class);
            client.setCreatedAt(LocalDateTime.now());
            client.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            clientRepository.save(client);
            return client;
        });
    }

/*    public Collection<Technology> addTechnologies()
    {

    }*/

}
