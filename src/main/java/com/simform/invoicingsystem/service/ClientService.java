package com.simform.invoicingsystem.service;

import com.simform.invoicingsystem.dto.ClientDetails;
import com.simform.invoicingsystem.entity.Client;
import com.simform.invoicingsystem.entity.Company;
import com.simform.invoicingsystem.repository.ClientRepository;
import com.simform.invoicingsystem.repository.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class ClientService {
    private final ClientRepository clientRepository;

    private final CompanyRepository companyRepository;
    private final ModelMapper mapper;

    public ClientService(ClientRepository clientRepository, CompanyRepository companyRepository, ModelMapper mapper) {
        this.clientRepository = clientRepository;
        this.companyRepository = companyRepository;
        this.mapper = mapper;
    }

    public Client addClient(ClientDetails clientDetails) {
        return clientRepository.findByEmail(clientDetails.getEmail()).orElseGet(() -> {
            Client client = mapper.map(clientDetails, Client.class);
            client.setCompany(
                    companyRepository.findByName(clientDetails.getCompanyName()).orElseGet(() -> {
                        Company company = new Company();
                        company.setName(clientDetails.getCompanyName());

                        company.setCreatedAt(LocalDateTime.now());
                        company.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
                        return companyRepository.save(company);
                    })
            );

            client.setCreatedAt(LocalDateTime.now());
            client.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            return clientRepository.save(client);
        });
    }
}
