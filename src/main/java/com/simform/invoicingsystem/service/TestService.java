package com.simform.invoicingsystem.service;


import com.simform.invoicingsystem.entity.*;
import com.simform.invoicingsystem.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
@Service

public class TestService {
 /*   BillingDetails billingDetails = new BillingDetails();
billingDetails.setAccStartDate(LocalDate.now());
billingDetails.setAccType("accType");
billingDetails.setCycle("billCycle");
billingDetails.setInvoiceTerm(30);
billingDetails.setPayModel("payModel");


    ClientDetails clientDetails = new ClientDetails();
clientDetails.setName("client");
clientDetails.setState("state");
clientDetails.setCountry("country");
clientDetails.setCity("city");
clientDetails.setCompanyName("company");
clientDetails.setEmail("tony@gmail.com");
clientDetails.setPhoneNumber("728814102000");


    OtherDetails otherDetails = new OtherDetails();
otherDetails.setChannel("channel");
otherDetails.setActiveBillingFlag(false);
otherDetails.setContractLink("contractLink");
otherDetails.setCsm("CSM");
otherDetails.setSource("leadSource");


    ProjectDetails projectDetails = new ProjectDetails();
projectDetails.setName("name");
projectDetails.setModel("model");
projectDetails.setBillingDetails(billingDetails);
projectDetails.setClientDetails(clientDetails);
projectDetails.setOtherDetails(otherDetails);
*/
 private ProjectRepository projectRepository;

     public TestService(ProjectRepository projectRepository) {
          this.projectRepository = projectRepository;
     }

     public Project getProject() {
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        LocalDateTime now = LocalDateTime.now();

        AccType accType = new AccType();
        accType.setAccType("Acc Type");
        accType.setCreatedAt(now);
        accType.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        accType.setDeletedAt(now);
        accType.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
        accType.setId(123L);
        accType.setUpdatedAt(now);
        accType.setUpdatedBy("2020-03-01");

        Client client = new Client();
        client.setCity("Oxford");
        client.setCompanyName("Company Name");
        client.setCountry("GB");
        client.setCreatedAt(now);
        client.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        client.setDeletedAt(now);
        client.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
        client.setEmail("jane.doe@example.org");
        client.setId(1L);
        client.setName("Name");
        client.setPhoneNumber("4105551212");
        client.setState("MD");
        client.setUpdatedAt(now);
        client.setUpdatedBy("2020-03-01");

        Csm csm = new Csm();
        csm.setCreatedAt(now);
        csm.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        csm.setDeletedAt(now);
        csm.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
        csm.setId(123L);
        csm.setName("Name");
        csm.setUpdatedAt(now);
        csm.setUpdatedBy("2020-03-01");


        InvoiceCycle invoiceCycle = new InvoiceCycle();
        invoiceCycle.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        invoiceCycle.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        invoiceCycle.setCycle("Cycle");
        invoiceCycle.setDeletedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        invoiceCycle.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
        invoiceCycle.setId(123L);
        invoiceCycle.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        invoiceCycle.setUpdatedBy("2020-03-01");


        LeadSource leadSource = new LeadSource();
        leadSource.setCreatedAt(now);
        leadSource.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        leadSource.setDeletedAt(now);
        leadSource.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
        leadSource.setId(123L);
        leadSource.setSource("Source");
        leadSource.setUpdatedAt(now);
        leadSource.setUpdatedBy("2020-03-01");

        MarketingChannel marketingChannel = new MarketingChannel();
        marketingChannel.setChannel("Channel");
        marketingChannel.setCreatedAt(now);
        marketingChannel.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        marketingChannel.setDeletedAt(now);
        marketingChannel.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
        marketingChannel.setId(123L);
        marketingChannel.setUpdatedAt(now);
        marketingChannel.setUpdatedBy("2020-03-01");


        ProjectModel projectModel = new ProjectModel();
        projectModel.setCreatedAt(now);
        projectModel.setCreatedBY("Jan 1, 2020 8:00am GMT+0100");
        projectModel.setDeletedAt(now);
        projectModel.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
        projectModel.setId(123L);
        projectModel.setModel("Model");
        projectModel.setUpdatedAt(now);
        projectModel.setUpdatedBy("2020-03-01");

        Project actualProject = new Project();
        actualProject.setAccStartDate(ofEpochDayResult);


        actualProject.setAccType(accType);
        actualProject.setActive(true);
        actualProject.setActiveBillingFlag(true);
        actualProject.setClient(client);
        actualProject.setContractLink("Contract Link");
        actualProject.setCreatedAt(now);
        actualProject.setCreatedBY("Jan 1, 2020 8:00am GMT+0100");
        actualProject.setCsm(csm);
        actualProject.setDeletedAt(now);
        actualProject.setDeletedBy("Jan 1, 2020 11:00am GMT+0100");
        actualProject.setEndDate(ofEpochDayResult);
        actualProject.setId(123L);
        actualProject.setInvoiceCycle(invoiceCycle);
        actualProject.setInvoiceTerm(1);
        actualProject.setLeadSource(leadSource);
        actualProject.setMarketingChannel(marketingChannel);
        actualProject.setName("Project XYZ");
        actualProject.setPayModel("Pay Model");
        actualProject.setProjectModel(projectModel);

        ArrayList<SalesPerson> salesPersonList = new ArrayList<>();
        salesPersonList.add(new SalesPerson("AAA"));
          salesPersonList.add(new SalesPerson("BBB"));
          salesPersonList.add(new SalesPerson("CCC"));
          salesPersonList.add(new SalesPerson("DDD"));
        actualProject.setSalesPersons(salesPersonList);
        actualProject.setStartDate(ofEpochDayResult);
        actualProject.setUpdatedAt(now);
        actualProject.setUpdatedBy("2020-03-01");


        return  projectRepository.save(actualProject);

    }
}
