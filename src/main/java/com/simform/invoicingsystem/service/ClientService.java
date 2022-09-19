package com.simform.invoicingsystem.service;

import com.simform.invoicingsystem.dto.ClientDetails;
import com.simform.invoicingsystem.entity.Client;
import com.simform.invoicingsystem.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class ClientService {
    private final ClientRepository clientRepository;

    private final ModelMapper mapper;

    public ClientService(ClientRepository clientRepository, ModelMapper mapper) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
    }

    public Client addClient(ClientDetails clientDetails) {
        return clientRepository.findByEmail(clientDetails.getEmail()).orElseGet(() -> {
            Client client = mapper.map(clientDetails, Client.class);
            client.setCreatedAt(LocalDateTime.now());
            client.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            return clientRepository.save(client);
        });
    }
}
