package com.simform.invoicingsystem;

import com.simform.invoicingsystem.entity.*;
import com.simform.invoicingsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class SetupLoader implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;
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

    @Autowired
    private TechStackRepository techStackRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        projectModel = projectModelRepository.save(projectModel);

        InvoiceCycle invoiceCycle = new InvoiceCycle();
        invoiceCycle.setCycle("Monthly");
        invoiceCycle.setCreatedBy(createdBy);
        invoiceCycle.setCreatedAt(now);
        invoiceCycle = invoiceCycleRepository.save(invoiceCycle);

        AccType accType = new AccType();
        accType.setAccType("Partner");
        accType.setCreatedBy(createdBy);
        accType.setCreatedAt(now);
        accType = accTypeRepository.save(accType);

        Csm csm = new Csm();
        csm.setName("Digvijay");
        csm.setCreatedBy(createdBy);
        csm.setCreatedAt(now);
        csm = csmRepository.save(csm);

        Csm csm2 = new Csm();
        csm2.setName("Laxit");
        csm2.setCreatedBy(createdBy);
        csm2.setCreatedAt(now);
        csm2 = csmRepository.save(csm2);

        SalesPerson salesPerson = new SalesPerson();
        salesPerson.setCreatedAt(now);
        salesPerson.setCreatedBy(createdBy);
        salesPerson.setName("Aakash");
        salesPerson = salesPersonRepository.save(salesPerson);

        LeadSource leadSource = new LeadSource();
        leadSource.setSource("Existing");
        leadSource.setCreatedBy(createdBy);
        leadSource.setCreatedAt(now);
        leadSource = leadSourceRepository.save(leadSource);

        MarketingChannel marketingChannel = new MarketingChannel();
        marketingChannel.setChannel("SEM");
        marketingChannel.setCreatedBy(createdBy);
        marketingChannel.setCreatedAt(now);
        marketingChannel = marketingChannelRepository.save(marketingChannel);


        Technology resourceDesign = new Technology();
        resourceDesign.setName("Resource & Design");
        resourceDesign.setCreatedAt(now);
        resourceDesign.setCreatedBy(createdBy);
        resourceDesign = technologyRepository.save(resourceDesign);

        Technology mobileTechnologies = new Technology();
        mobileTechnologies.setName("Mobile Technologies");
        mobileTechnologies.setCreatedAt(now);
        mobileTechnologies.setCreatedBy(createdBy);
        mobileTechnologies = technologyRepository.save(mobileTechnologies);


        Technology frontEndTechnologies = new Technology();
        frontEndTechnologies.setName("Front End Technologies");
        frontEndTechnologies.setCreatedAt(now);
        frontEndTechnologies.setCreatedBy(createdBy);
        frontEndTechnologies = technologyRepository.save(frontEndTechnologies);

        Technology backEndTechnologies = new Technology();
        backEndTechnologies.setName("Back End Technologies");
        backEndTechnologies.setCreatedAt(now);
        backEndTechnologies.setCreatedBy(createdBy);
        backEndTechnologies = technologyRepository.save(backEndTechnologies);


        Technology otherTechnologies = new Technology();
        otherTechnologies.setName("Other Technologies");
        otherTechnologies.setCreatedAt(now);
        otherTechnologies.setCreatedBy(createdBy);
        otherTechnologies = technologyRepository.save(otherTechnologies);

        //R&D
        TechStack pm = new TechStack();
        pm.setName("PM");
        pm.setCreatedAt(now);
        pm.setCreatedBy(createdBy);
        pm.setTechnology(resourceDesign);
        pm = techStackRepository.save(pm);


        TechStack qa = new TechStack();
        qa.setName("QA");
        qa.setCreatedAt(now);
        qa.setCreatedBy(createdBy);
        qa.setTechnology(resourceDesign);
        qa = techStackRepository.save(qa);

        TechStack design = new TechStack();
        design.setName("Design");
        design.setCreatedAt(now);
        design.setCreatedBy(createdBy);
        design.setTechnology(resourceDesign);
        design = techStackRepository.save(design);

        //Mobile Techs
        TechStack androidNative = new TechStack();
        androidNative.setName("Android Native");
        androidNative.setCreatedAt(now);
        androidNative.setCreatedBy(createdBy);
        androidNative.setTechnology(mobileTechnologies);
        androidNative = techStackRepository.save(androidNative);


        TechStack iOSNative = new TechStack();
        iOSNative.setName("iOS Native");
        iOSNative.setCreatedAt(now);
        iOSNative.setCreatedBy(createdBy);
        iOSNative.setTechnology(mobileTechnologies);
        iOSNative = techStackRepository.save(iOSNative);


        TechStack reactNative = new TechStack();
        reactNative.setName("React Native");
        reactNative.setCreatedAt(now);
        reactNative.setCreatedBy(createdBy);
        reactNative.setTechnology(mobileTechnologies);
        reactNative = techStackRepository.save(reactNative);

        //Frontend
        TechStack flutter = new TechStack();
        flutter.setName("Flutter");
        flutter.setCreatedAt(now);
        flutter.setCreatedBy(createdBy);
        flutter.setTechnology(frontEndTechnologies);
        flutter = techStackRepository.save(flutter);


        TechStack reactJS = new TechStack();
        reactJS.setName("ReactJS");
        reactJS.setCreatedAt(now);
        reactJS.setCreatedBy(createdBy);
        reactJS.setTechnology(frontEndTechnologies);
        reactJS = techStackRepository.save(reactJS);


        TechStack nodeJS = new TechStack();
        nodeJS.setName("NodeJS");
        nodeJS.setCreatedAt(now);
        nodeJS.setCreatedBy(createdBy);
        nodeJS.setTechnology(frontEndTechnologies);
        nodeJS = techStackRepository.save(nodeJS);


        TechStack php = new TechStack();
        php.setName("PHP");
        php.setCreatedAt(now);
        php.setCreatedBy(createdBy);
        php.setTechnology(frontEndTechnologies);
        php = techStackRepository.save(php);


        //Backend
        TechStack java = new TechStack();
        java.setName("Java");
        java.setCreatedAt(now);
        java.setCreatedBy(createdBy);
        java.setTechnology(backEndTechnologies);
        java = techStackRepository.save(java);


        TechStack ror = new TechStack();
        ror.setName("ROR");
        ror.setCreatedAt(now);
        ror.setCreatedBy(createdBy);
        ror.setTechnology(backEndTechnologies);
        ror = techStackRepository.save(ror);

        TechStack python = new TechStack();
        python.setName("Python");
        python.setCreatedAt(now);
        python.setCreatedBy(createdBy);
        python.setTechnology(backEndTechnologies);
        python = techStackRepository.save(python);

        //other techs
        TechStack blockChain = new TechStack();
        blockChain.setName("BlockChain");
        blockChain.setCreatedAt(now);
        blockChain.setCreatedBy(createdBy);
        blockChain.setTechnology(otherTechnologies);
        blockChain = techStackRepository.save(blockChain);

        TechStack devOps = new TechStack();
        devOps.setName("DevOps");
        devOps.setCreatedAt(now);
        devOps.setCreatedBy(createdBy);
        devOps.setTechnology(otherTechnologies);
        devOps = techStackRepository.save(devOps);


        Company company = new Company();
        company.setCreatedAt(now);
        company.setCreatedBy(createdBy);
        company.setName("Google");
        company = companyRepository.save(company);

        Client client = new Client();
        client.setCompany(company);
        client.setCity("Oxford");
        client.setCountry("GB");
        client.setCreatedAt(now);
        client.setCreatedBy(createdBy);
        client.setEmail("jane.doe@example.org");
        client.setId(123L);
        client.setName("Name");
        client.setPhoneNumber("4105551212");
        client.setState("MD");
        client = clientRepository.save(client);


        Project project1 = new Project();
        project1.setDefaultRate("200");
        project1.setZohoProjectId("ABC-123");
        project1.setAccStartDate(LocalDate.ofEpochDay(3L));
        project1.setActive(true);
        project1.setActiveBillingFlag(true);
        project1.setContractLink("Contract Link");
        project1.setCreatedAt(now);
        project1.setCreatedBy(createdBy);
        project1.setEndDate(LocalDate.ofEpochDay(3L));
        project1.setInvoiceTerm(3);
        project1.setLeadSource(leadSource);
        project1.setMarketingChannel(marketingChannel);
        project1.setName("Name");
        project1.setPayModel("Pay Model");
        project1.setStartDate(LocalDate.ofEpochDay(3L));


        project1.setAccType(accType);
        project1.setProjectModel(projectModel);
        project1.setCsm(csm);
        project1.setInvoiceCycle(invoiceCycle);


        project1.setClient(client);
        project1.setSalesPersons(List.of(salesPerson));
        project1.setRates(
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
        project1 = projectRepository.save(project1);

        Client client2 = new Client();
        client2.setCompany(company);
        client2.setCity("Oxford");
        client2.setCountry("GB");
        client2.setCreatedAt(now);
        client2.setCreatedBy(createdBy);
        client2.setEmail("jane.doe@example.org");
        client2.setId(123L);
        client2.setName("Name");
        client2.setPhoneNumber("4105551212");
        client2.setState("MD");
        client2 = clientRepository.save(client2);


        Project project2 = new Project();
        project2.setDefaultRate("200");
        project2.setZohoProjectId("ABC-123");
        project2.setAccStartDate(LocalDate.ofEpochDay(3L));
        project2.setActive(true);
        project2.setActiveBillingFlag(true);
        project2.setContractLink("Contract Link");
        project2.setCreatedAt(now);
        project2.setCreatedBy(createdBy);
        project2.setEndDate(LocalDate.ofEpochDay(3L));
        project2.setInvoiceTerm(3);
        project2.setLeadSource(leadSource);
        project2.setMarketingChannel(marketingChannel);
        project2.setName("Name");
        project2.setPayModel("Pay Model");
        project2.setStartDate(LocalDate.ofEpochDay(3L));


        project2.setAccType(accType);
        project2.setProjectModel(projectModel);
        project2.setCsm(csm);
        project2.setInvoiceCycle(invoiceCycle);


        project2.setClient(client2);
        project2.setSalesPersons(List.of(salesPerson));

        project2.setRates(
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

        project2 = projectRepository.save(project2);


        User user = new User();
        user.setName("admin");
        user.setEmail("admin@gmail.com");
        user.setPassword("$2a$12$915Om6fz13t43bA1UDTUbe7.IAYdXTHpa29GXMy4wBcrnBuke5FZu");
        user.setStatus(true);
        user = userRepository.save(user);

    }
}


