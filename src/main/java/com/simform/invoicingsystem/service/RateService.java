package com.simform.invoicingsystem.service;


import com.simform.invoicingsystem.dto.RateDetails;
import com.simform.invoicingsystem.dto.SpecialRateDetails;
import com.simform.invoicingsystem.entity.Project;
import com.simform.invoicingsystem.entity.SpecialRate;
import com.simform.invoicingsystem.exception.ResourceNotFoundException;
import com.simform.invoicingsystem.repository.ProjectRepository;
import com.simform.invoicingsystem.repository.RateRepository;
import com.simform.invoicingsystem.repository.SpecialRateRepository;
import com.simform.invoicingsystem.repository.TechStackRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class RateService {

    private RateRepository rateRepository;
    private ProjectRepository projectRepository;

    private TechStackRepository techStackRepository;

    private SpecialRateRepository specialRateRepository;

    public RateService(RateRepository rateRepository, ProjectRepository projectRepository, TechStackRepository techStackRepository,
                       SpecialRateRepository specialRateRepository) {
        this.rateRepository = rateRepository;
        this.projectRepository = projectRepository;
        this.techStackRepository = techStackRepository;
        this.specialRateRepository = specialRateRepository;
    }

    public List<RateDetails> updateRate(String projectName, List<RateDetails> rateDetailsList) {
        Project project = projectRepository.findByName(projectName).orElseThrow(() -> new ResourceNotFoundException("Project with name " + projectName + " not found"));
        project.getRates().forEach(rate -> {
            rateDetailsList.forEach(rateDetails -> {
                if (rateDetails.getStack().equalsIgnoreCase(rate.getStack())) {
                    rate.setRate(rateDetails.getRate());
                    rate.setUpdatedAt(LocalDateTime.now());
                    rate.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
                    rateRepository.save(rate);
                }
            });

        });

        return rateDetailsList;
    }


    public Collection<SpecialRateDetails> updateSpecialRate(String projectName, Collection<SpecialRateDetails> specialRateDetails) {
        Project project = projectRepository.findByName(projectName).orElseThrow(() -> new ResourceNotFoundException(projectName + " Project name not found."));
        LocalDateTime now = LocalDateTime.now();
        project.getSpecialRates().forEach(specialRate -> {
            specialRate.setDeletedAt(now);
            specialRate.setDeletedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            specialRate.setIsDeleted(true);
            specialRateRepository.save(specialRate);
        });

        Collection<SpecialRate> specialRates = new ArrayList<>(
                specialRateDetails.stream().map(specialRateDetail -> {
                    SpecialRate specialRate = new SpecialRate();
                    specialRate.setRate(specialRateDetail.getRate());
                    specialRate.setZohoUserId(specialRateDetail.getZohoUserId());
                    specialRate.setCreatedAt(now);
                    specialRate.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
                    specialRate.setIsDeleted(false);
                    return specialRateRepository.save(specialRate);
                }).toList());

        project.setSpecialRates(specialRates);
        projectRepository.save(project);
        return specialRateDetails;
    }

}
