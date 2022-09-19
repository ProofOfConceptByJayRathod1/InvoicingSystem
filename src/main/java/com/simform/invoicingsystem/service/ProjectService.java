package com.simform.invoicingsystem.service;

import com.simform.invoicingsystem.dto.ProjectDetails;
import com.simform.invoicingsystem.entity.*;
import com.simform.invoicingsystem.repository.*;
import com.simform.invoicingsystem.util.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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

    public ProjectDetails updateProject(HttpServletRequest request , ProjectDetails projectDetails, String projectName){

        Project project = projectRepository.findByName(projectName).orElseThrow();

        project.setName(projectDetails.getName());

        ProjectModel projectModel = projectModelRepository.findByModel(projectDetails.getModel()).orElseThrow();
        project.setProjectModel(projectModel);

        //Client client = new Client();
        Client client = project.getClient();
        client.setName(projectDetails.getClientDetails().getName());
        client.setCompanyName(projectDetails.getClientDetails().getCompanyName());
        client.setEmail(projectDetails.getClientDetails().getEmail());
        client.setCity(projectDetails.getClientDetails().getCity());
        client.setState(projectDetails.getClientDetails().getState());
        client.setCountry(projectDetails.getClientDetails().getCountry());
        client.setPhoneNumber(projectDetails.getClientDetails().getPhoneNumber());
        project.setClient(client);


        InvoiceCycle invoiceCycle = invoiceCycleRepository.findByCycle(projectDetails.getBillingDetails().getCycle()).orElseThrow();
        project.setInvoiceCycle(invoiceCycle);

        project.setInvoiceTerm(projectDetails.getBillingDetails().getInvoiceTerm());
        project.setPayModel(projectDetails.getBillingDetails().getPayModel());

        AccType accType = accTypeRepository.findByAccType(projectDetails.getBillingDetails().getAccType()).orElseThrow();
        project.setAccType(accType);

        project.setAccStartDate(projectDetails.getBillingDetails().getAccStartDate());
        project.setStartDate(projectDetails.getBillingDetails().getProjectStartDate());

        Csm csm = csmRepository.findByName(projectDetails.getOtherDetails().getCsm()).orElseThrow();
        project.setCsm(csm);

        List<SalesPerson> salesPersonListNew = new ArrayList<>();
        List<String> salesPersonName = projectDetails.getOtherDetails().getSalesman();
        SalesPerson salesPerson;

        for(int i=0 ; i<salesPersonName.size() ; i++){
            salesPerson = salesPersonRepository.findByName(salesPersonName.get(i)).orElseThrow();
            salesPersonListNew.add(salesPerson);
        }
        project.setSalesPersons(salesPersonListNew);

        project.setContractLink(projectDetails.getOtherDetails().getContractLink());

        LeadSource leadSource = leadSourceRepository.findBySource(projectDetails.getOtherDetails().getSource()).orElseThrow();
        project.setLeadSource(leadSource);

        MarketingChannel marketingChannel = marketingChannelRepository.findByChannel(projectDetails.getOtherDetails().getChannel()).orElseThrow();
        project.setMarketingChannel(marketingChannel);

        project.setActiveBillingFlag(projectDetails.getOtherDetails().isActiveBillingFlag());

        project.setUpdatedAt(LocalDateTime.now());

        /*String token =  Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("token"))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
        String userName = jwtUtil.extractUsername(token);
        project.setUpdatedBy(userName);*/

        projectRepository.save(project);

        return projectDetails;

    }
}
