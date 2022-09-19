package com.simform.invoicingsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 10)
    private String name;
    private String payModel;
    private int invoiceTerm;
    private LocalDate accStartDate;
    private boolean activeBillingFlag;
    @Column(length = 250)
    private String contractLink;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActive;
    private LocalDateTime createdAt;
    @Column(length = 40)
    private String createdBY;
    private LocalDateTime updatedAt;
    @Column(length = 40)
    private String updatedBy;
    private LocalDateTime deletedAt;
    @Column(length = 40)
    private String deletedBy;


    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER, targetEntity = InvoiceCycle.class)
    private InvoiceCycle invoiceCycle;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER, targetEntity = AccType.class)
    private AccType accType;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER, targetEntity = LeadSource.class)
    private LeadSource leadSource;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER, targetEntity = Csm.class)
    private Csm csm;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER, targetEntity = ProjectModel.class)
    private ProjectModel projectModel;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER, targetEntity = Client.class)
    private Client client;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER, targetEntity = MarketingChannel.class)
    private MarketingChannel marketingChannel;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER, targetEntity = SalesPerson.class)
    private Collection<SalesPerson> salesPersons;

}
