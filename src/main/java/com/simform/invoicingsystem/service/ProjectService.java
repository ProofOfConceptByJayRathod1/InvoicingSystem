package com.simform.invoicingsystem.service;

import com.simform.invoicingsystem.dto.ClientDetails;
import com.simform.invoicingsystem.dto.ProjectDetails;
import com.simform.invoicingsystem.entity.*;
import com.simform.invoicingsystem.repository.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class ProjectService {

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

    public void addProject(ProjectDetails projectDetails) {
        //convert projectDetails to project
        Project project = new Project();

        Optional<ProjectModel> projectModel = projectModelRepository.findByModel(projectDetails.getModel());
        projectModel.ifPresent(project::setProjectModel);

        Client client = addClient(projectDetails.getClientDetails());
        project.setClient(client);

        Optional<InvoiceCycle> invoiceCycle = invoiceCycleRepository.findByCycle(projectDetails.getBillingDetails().getCycle());
        invoiceCycle.ifPresent(project::setInvoiceCycle);

        Optional<AccType> accType = accTypeRepository.findByAccType(projectDetails.getBillingDetails().getAccType());
        accType.ifPresent(project::setAccType);

        Optional<Csm> csm = csmRepository.findByName(projectDetails.getOtherDetails().getCsm());
        csm.ifPresent(project::setCsm);

        Collection<SalesPerson> salesPeople = projectDetails.getOtherDetails().getSalesman().stream().map(salesPersonRepository::findByName).filter(Optional::isPresent).map(Optional::get).toList();

        Optional<LeadSource> leadSource = leadSourceRepository.findBySource(projectDetails.getOtherDetails().getSource());
        leadSource.ifPresent(project::setLeadSource);

        Optional<MarketingChannel> marketingChannel = marketingChannelRepository.findByChannel(projectDetails.getOtherDetails().getChannel());
        marketingChannel.ifPresent(project::setMarketingChannel);


//        project.setTechnologies(addTechn);

        projectRepository.save(project);
    }

    public Client addClient(ClientDetails clientDetails) {
        Optional<Client> optionalClient = clientRepository.findByEmail(clientDetails.getEmail());
        if (optionalClient.isPresent()) {
            //convert dto to entity
            Client client = new Client();
            return clientRepository.save(client);
        } else {
            //create new client from client DTO
            Client client = new Client();
            client.setName(clientDetails.getName());
            client.setCompanyName(clientDetails.getCompanyName());
            client.setEmail(clientDetails.getEmail());
            client.setCity(clientDetails.getCity());
            client.setState(clientDetails.getState());
            client.setCountry(clientDetails.getCountry());
            client.setPhoneNumber(clientDetails.getPhoneNumber());
            client.setCreatedAt(LocalDateTime.now());
            client.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            return clientRepository.save(client);
        }
    }

/*    public Collection<Technology> addTechnologies()
    {

    }*/

}
