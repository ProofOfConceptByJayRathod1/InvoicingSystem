package com.simform.invoicingsystem;

import com.simform.invoicingsystem.entity.*;
import com.simform.invoicingsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SetupLoader implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private MarketingChannelRepository marketingChannelRepository;
    @Autowired
    private ProjectModelRepository projectModelRepository;
    @Autowired
    private InvoiceCycleRepository invoiceCycleRepository;

    @Autowired
    private AccTypeRepository accTypeRepository;

    @Autowired
    private SalesPersonRepository salesPersonRepository;

    @Autowired
    private LeadSourceRepository leadSourceRepository;

    @Autowired
    private CsmRepository csmRepository;

    @Autowired
    private TechnologyRepository technologyRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (!projectModelRepository.findByModel("Fixed").isEmpty()) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        String createdBy = "default";

        ProjectModel projectModel = new ProjectModel();
        projectModel.setModel("Fixed");
        projectModel.setCreatedAt(now);
        projectModel.setCreatedBy(createdBy);
        projectModelRepository.save(projectModel);

        InvoiceCycle invoiceCycle = new InvoiceCycle();
        invoiceCycle.setCycle("Monthly");
        invoiceCycle.setCreatedBy(createdBy);
        invoiceCycle.setCreatedAt(now);
        invoiceCycleRepository.save(invoiceCycle);

        AccType accType = new AccType();
        accType.setAccType("Partner");
        accType.setCreatedBy(createdBy);
        accType.setCreatedAt(now);
        accTypeRepository.save(accType);

        Csm csm = new Csm();
        csm.setName("Digvijay");
        csm.setCreatedBy(createdBy);
        csm.setCreatedAt(now);
        csmRepository.save(csm);

        Csm csm2 = new Csm();
        csm2.setName("Laxit");
        csm2.setCreatedBy(createdBy);
        csm2.setCreatedAt(now);
        csmRepository.save(csm2);

        SalesPerson salesPerson = new SalesPerson();
        salesPerson.setCreatedAt(now);
        salesPerson.setCreatedBy(createdBy);
        salesPerson.setName("Aakash");
        salesPersonRepository.save(salesPerson);

        LeadSource leadSource = new LeadSource();
        leadSource.setSource("Existing");
        leadSource.setCreatedBy(createdBy);
        leadSource.setCreatedAt(now);
        leadSourceRepository.save(leadSource);

        MarketingChannel marketingChannel = new MarketingChannel();
        marketingChannel.setChannel("SEM");
        marketingChannel.setCreatedBy(createdBy);
        marketingChannel.setCreatedAt(now);
        marketingChannelRepository.save(marketingChannel);


        Technology technology = new Technology();
        technology.setName("Backend");
        technology.setCreatedBy(createdBy);
        technology.setCreatedAt(now);
        Technology backend = technologyRepository.save(technology);


        Technology technology1 = new Technology();
        technology1.setName("Frontend");
        technology1.setCreatedBy(createdBy);
        technology1.setCreatedAt(now);
        Technology frontEnd = technologyRepository.save(technology1);

        Rate rate = new Rate();
        rate.setRate("$50");
        rate.setCreatedAt(now);
        rate.setCreatedBy(createdBy);
        rate.setSpecial(false);
        rate.setStack("Java");
        rate.setTechnologies(List.of(frontEnd));
        rateRepository.save(rate);

        Rate rate2 = new Rate();
        rate2.setRate("$5000");
        rate2.setCreatedAt(now);
        rate2.setCreatedBy(createdBy);
        rate2.setSpecial(false);
        rate2.setTechnologies(List.of(backend));
        rate2.setStack("Spring boot");
        rateRepository.save(rate2);
    }
}


