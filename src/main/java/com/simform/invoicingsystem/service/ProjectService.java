package com.simform.invoicingsystem.service;

import com.simform.invoicingsystem.dto.ProjectDetail;
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

    public ProjectDetail updateProject(HttpServletRequest request , ProjectDetail projectDetail, String projectName){

        Project project = projectRepository.findByName(projectName).orElseThrow();

        project.setName(projectDetail.getName());

        ProjectModel projectModel = projectModelRepository.findByModel(projectDetail.getModel()).orElseThrow();
        project.setProjectModel(projectModel);

        //Client client = new Client();
        Client client = project.getClient();
        client.setName(projectDetail.getClientDetails().getName());
        client.setCompanyName(projectDetail.getClientDetails().getCompanyName());
        client.setEmail(projectDetail.getClientDetails().getEmail());
        client.setCity(projectDetail.getClientDetails().getCity());
        client.setState(projectDetail.getClientDetails().getState());
        client.setCountry(projectDetail.getClientDetails().getCountry());
        client.setPhoneNumber(projectDetail.getClientDetails().getPhoneNumber());
        project.setClient(client);


        InvoiceCycle invoiceCycle = invoiceCycleRepository.findByCycle(projectDetail.getCycle()).orElseThrow();
        project.setInvoiceCycle(invoiceCycle);

        project.setInvoiceTerm(projectDetail.getInvoiceTerm());
        project.setPayModel(projectDetail.getPayModel());

        AccType accType = accTypeRepository.findByAccType(projectDetail.getAccType()).orElseThrow();
        project.setAccType(accType);

        project.setAccStartDate(projectDetail.getAccStartDate());
        project.setStartDate(projectDetail.getProjectStartDate());
        project.setEndDate(projectDetail.getProjectEndDate());

        Csm csm = csmRepository.findByName(projectDetail.getCsm()).orElseThrow();
        project.setCsm(csm);

        List<SalesPerson> salesPersonListNew = new ArrayList<>();
        List<String> salesPersonName = (List<String>) projectDetail.getSalesPersons();
        SalesPerson salesPerson;

        for(int i=0 ; i<salesPersonName.size() ; i++){
            salesPerson = salesPersonRepository.findByName(salesPersonName.get(i)).orElseThrow();
            salesPersonListNew.add(salesPerson);
        }
        project.setSalesPersons(salesPersonListNew);

        project.setContractLink(projectDetail.getContractLink());

        LeadSource leadSource = leadSourceRepository.findBySource(projectDetail.getSource()).orElseThrow();
        project.setLeadSource(leadSource);

        MarketingChannel marketingChannel = marketingChannelRepository.findByChannel(projectDetail.getChannel()).orElseThrow();
        project.setMarketingChannel(marketingChannel);

        project.setActiveBillingFlag(projectDetail.isActiveBillingFlag());

        project.setUpdatedAt(LocalDateTime.now());

        /*String token =  Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("token"))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
        String userName = jwtUtil.extractUsername(token);
        project.setUpdatedBy(userName);*/

        projectRepository.save(project);

        return projectDetail;

    }
}
