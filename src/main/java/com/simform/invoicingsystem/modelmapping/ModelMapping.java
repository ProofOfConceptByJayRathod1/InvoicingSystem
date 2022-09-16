package com.simform.invoicingsystem.modelmapping;

import com.simform.invoicingsystem.dto.ClientDetails;
import com.simform.invoicingsystem.dto.ProjectDetail;
import com.simform.invoicingsystem.dto.ProjectView;
import com.simform.invoicingsystem.entity.Client;
import com.simform.invoicingsystem.entity.Project;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class ModelMapping {

    private static final ModelMapper mapper = new ModelMapper();

    public static PropertyMap<Project, ProjectDetail> getProjectDetailsMapping() {
        return new PropertyMap<>() {
            protected void configure() {
                map().setClientDetails(clientDetails(source.getClient()));
            }
        };
    }

    public static ClientDetails clientDetails(Client client) {
        return mapper.map(client, ClientDetails.class);
    }

    public static PropertyMap<ProjectDetail, Project> getProjectMapping() {
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
