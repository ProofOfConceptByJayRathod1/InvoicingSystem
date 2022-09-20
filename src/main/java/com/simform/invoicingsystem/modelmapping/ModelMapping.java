package com.simform.invoicingsystem.modelmapping;

import com.simform.invoicingsystem.dto.ClientDetails;
import com.simform.invoicingsystem.dto.ProjectDetails;
import com.simform.invoicingsystem.dto.ProjectView;
import com.simform.invoicingsystem.dto.TechStackRate;
import com.simform.invoicingsystem.entity.Client;
import com.simform.invoicingsystem.entity.Project;
import com.simform.invoicingsystem.entity.Rate;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.Collection;

public class ModelMapping {

    private static final ModelMapper mapper = new ModelMapper();

    public static PropertyMap<Project, ProjectDetails> getProjectDetailsMapping() {
        return new PropertyMap<>() {
            protected void configure() {
                //map().setTechStackRates(techStackRate(source.getRates()));
                map().setClientDetails(clientDetails(source.getClient()));
            }
        };
    }

    public static ClientDetails clientDetails(Client client) {
        return mapper.map(client, ClientDetails.class);
    }

    public static Collection<TechStackRate> techStackRate(Collection<Rate> rates) {
        return rates.stream().map(rate -> {
            TechStackRate techStackRate = mapper.map(rate, TechStackRate.class);
            techStackRate.setTechStack(rate.getStack());
            return techStackRate;
        }).toList();
    }

    public static PropertyMap<ProjectDetails, Project> getProjectMapping() {
        return new PropertyMap<>() {
            protected void configure() {
                map().setClient(client(source.getClientDetails()));
                map().setAccStartDate(source.getAccStartDate());
                map().setEndDate(source.getProjectEndDate());
                map().setStartDate(source.getProjectStartDate());
            }
        };
    }

    public static Client client(ClientDetails clientDetails) {
        return mapper.map(clientDetails, Client.class);
    }


    public static PropertyMap<Project, ProjectView> getProjectViewMapping() {
        return new PropertyMap<>() {
            protected void configure() {
                map().setEmail(source.getClient().getEmail());
                map().setClientName(source.getClient().getName());
                map().setModel(source.getProjectModel().getModel());
                map().setAccType(source.getAccType().getAccType());
                map().setInvoiceCycle(source.getInvoiceCycle().getCycle());
            }
        };
    }

}
