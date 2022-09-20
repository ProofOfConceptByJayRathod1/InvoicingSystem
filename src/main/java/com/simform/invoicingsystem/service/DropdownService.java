package com.simform.invoicingsystem.service;

import com.simform.invoicingsystem.entity.*;
import com.simform.invoicingsystem.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DropdownService {

    private ProjectModelRepository projectModelRepository;
    private InvoiceCycleRepository invoiceCycleRepository;
    private AccTypeRepository accTypeRepository;

    private CsmRepository csmRepository;
    private SalesPersonRepository salesPersonRepository;
    private LeadSourceRepository leadSourceRepository;
    private MarketingChannelRepository marketingChannelRepository;

    public DropdownService(ProjectModelRepository projectModelRepository, InvoiceCycleRepository invoiceCycleRepository, AccTypeRepository accTypeRepository, CsmRepository csmRepository, SalesPersonRepository salesPersonRepository, LeadSourceRepository leadSourceRepository, MarketingChannelRepository marketingChannelRepository) {
        this.projectModelRepository = projectModelRepository;
        this.invoiceCycleRepository = invoiceCycleRepository;
        this.accTypeRepository = accTypeRepository;
        this.csmRepository = csmRepository;
        this.salesPersonRepository = salesPersonRepository;
        this.leadSourceRepository = leadSourceRepository;
        this.marketingChannelRepository = marketingChannelRepository;
    }

    public List<String> getCsm() {
        List<Csm> csmList = csmRepository.findAll();
        return csmList.stream().map(Csm::getName).collect(Collectors.toList());
    }

    public List<String> getSalesPerson() {
        List<SalesPerson> salesPersonList = salesPersonRepository.findAll();
        return salesPersonList.stream().map(SalesPerson::getName).collect(Collectors.toList());
    }

    public List<String> getLeadSource() {
        List<LeadSource> leadSourceList = leadSourceRepository.findAll();
        return leadSourceList.stream().map(LeadSource::getSource).collect(Collectors.toList());
    }

    public List<String> getMarketingChannel() {
        List<MarketingChannel> marketingChannelList = marketingChannelRepository.findAll();
        return marketingChannelList.stream().map(MarketingChannel::getChannel).collect(Collectors.toList());
    }

    public List<String> getProjectModel() {
        List<ProjectModel> projectModelList = projectModelRepository.findAll();
        return projectModelList.stream().map(ProjectModel::getModel).collect(Collectors.toList());
    }

    public List<String> getInvoiceCycle() {
        List<InvoiceCycle> invoiceCycleList = invoiceCycleRepository.findAll();
        return invoiceCycleList.stream().map(InvoiceCycle::getCycle).collect(Collectors.toList());
    }

    public List<String> getAccType() {
        List<AccType> accTypeList = accTypeRepository.findAll();
        return accTypeList.stream().map(AccType::getAccType).collect(Collectors.toList());
    }

    public List<String> getPayModel() {
        return Arrays.asList("Prepaid", "Postpaid");
    }

    public List<String> getActiveBilling() {
        return Arrays.asList("Yes", "No");
    }
}
