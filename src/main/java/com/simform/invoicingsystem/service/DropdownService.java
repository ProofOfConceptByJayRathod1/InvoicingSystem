package com.simform.invoicingsystem.service;

import com.simform.invoicingsystem.entity.Csm;
import com.simform.invoicingsystem.entity.LeadSource;
import com.simform.invoicingsystem.entity.MarketingChannel;
import com.simform.invoicingsystem.entity.SalesPerson;
import com.simform.invoicingsystem.repository.CsmRepository;
import com.simform.invoicingsystem.repository.LeadSourceRepository;
import com.simform.invoicingsystem.repository.MarketingChannelRepository;
import com.simform.invoicingsystem.repository.SalesPersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DropdownService {

    private CsmRepository csmRepository;
    private SalesPersonRepository salesPersonRepository;
    private LeadSourceRepository leadSourceRepository;
    private MarketingChannelRepository marketingChannelRepository;

    public DropdownService(CsmRepository csmRepository, SalesPersonRepository salesPersonRepository, LeadSourceRepository leadSourceRepository) {
        this.csmRepository = csmRepository;
        this.salesPersonRepository = salesPersonRepository;
        this.leadSourceRepository = leadSourceRepository;
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
}
