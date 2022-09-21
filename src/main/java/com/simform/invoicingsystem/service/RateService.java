package com.simform.invoicingsystem.service;

import com.simform.invoicingsystem.dto.RateDetails;
import com.simform.invoicingsystem.entity.Project;
import com.simform.invoicingsystem.exception.ResourceNotFoundException;
import com.simform.invoicingsystem.repository.ProjectRepository;
import com.simform.invoicingsystem.repository.RateRepository;
import com.simform.invoicingsystem.repository.TechStackRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RateService {

    private RateRepository rateRepository;

    private ProjectRepository projectRepository;

    private TechStackRepository techStackRepository;


    public RateService(RateRepository rateRepository, ProjectRepository projectRepository, TechStackRepository techStackRepository) {
        this.rateRepository = rateRepository;
        this.projectRepository = projectRepository;
        this.techStackRepository = techStackRepository;
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
}
