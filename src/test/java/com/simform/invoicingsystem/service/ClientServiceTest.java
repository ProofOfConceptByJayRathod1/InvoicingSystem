package com.simform.invoicingsystem.service;

import com.simform.invoicingsystem.dto.ClientDetails;
import com.simform.invoicingsystem.entity.Client;
import com.simform.invoicingsystem.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    ClientRepository clientRepository;
    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    private ClientService clientService;

    @Test
    void addClientSuccess() {
        String email = "chris@gmail.com";
        ClientDetails clientDetails = new ClientDetails("Chris", "Company Name", email, "LA", "CA", "USA", "1528252585");
        Client client = new Client(1L, "Chris", "Company Name", "chris@gmail.com", "LA", "CA", "USA", "1528252585", LocalDateTime.now(), null, null, null, null, null);

        when(clientRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(clientRepository.save(client)).thenReturn(client);
        when(modelMapper.map(clientDetails, Client.class)).thenReturn(client);

        Client actualResult = clientService.addClient(clientDetails);

        assertEquals(actualResult.getEmail(), email);
        assertEquals(actualResult, client);

        verify(clientRepository, times(1)).findByEmail(any(String.class));
        verify(clientRepository, times(1)).save(client);
        verify(modelMapper, times(1)).map(clientDetails, Client.class);
    }
}
