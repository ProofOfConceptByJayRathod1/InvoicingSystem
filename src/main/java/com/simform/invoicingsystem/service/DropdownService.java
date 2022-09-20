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

    public DropdownService(ProjectModelRepository projectModelRepository, InvoiceCycleRepository invoiceCycleRepository,
                           AccTypeRepository accTypeRepository, CsmRepository csmRepository, SalesPersonRepository salesPersonRepository,
                           LeadSourceRepository leadSourceRepository, MarketingChannelRepository marketingChannelRepository) {
        this.projectModelRepository = projectModelRepository;
        this.invoiceCycleRepository = invoiceCycleRepository;
        this.accTypeRepository = accTypeRepository;
        this.csmRepository = csmRepository;
        this.salesPersonRepository = salesPersonRepository;
        this.leadSourceRepository = leadSourceRepository;
        this.marketingChannelRepository = marketingChannelRepository;
    }

    public List<String> getCsm() {
        return csmRepository.findAll().stream().map(Csm::getName).toList();
    }

    public List<String> getSalesPerson() {
        return salesPersonRepository.findAll().stream().map(SalesPerson::getName).toList();
    }

    public List<String> getLeadSource() {
        return leadSourceRepository.findAll().stream().map(LeadSource::getSource).toList();
    }

    public List<String> getMarketingChannel() {
        return marketingChannelRepository.findAll().stream().map(MarketingChannel::getChannel).toList();
    }

    public List<String> getProjectModel() {
        return projectModelRepository.findAll().stream().map(ProjectModel::getModel).toList();
    }

    public List<String> getInvoiceCycle() {
        return invoiceCycleRepository.findAll().stream().map(InvoiceCycle::getCycle).toList();
    }

    public List<String> getAccType() {
        return accTypeRepository.findAll().stream().map(AccType::getAccType).toList();
    }

    public List<String> getPayModel() {
        return Arrays.asList("Prepaid", "Postpaid");
    }

    public List<String> getActiveBilling() {
        return Arrays.asList("Yes", "No");
    }
}
