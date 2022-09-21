package com.simform.invoicingsystem.service;

import com.simform.invoicingsystem.dto.SpecialRateDetails;
import com.simform.invoicingsystem.entity.Project;
import com.simform.invoicingsystem.entity.SpecialRate;
import com.simform.invoicingsystem.exception.ResourceNotFoundException;
import com.simform.invoicingsystem.repository.ProjectRepository;
import com.simform.invoicingsystem.repository.RateRepository;
import com.simform.invoicingsystem.repository.SpecialRateRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class RateService {

  private RateRepository rateRepository;
  private ProjectRepository projectRepository;

  private SpecialRateRepository specialRateRepository;

  public RateService(RateRepository rateRepository, ProjectRepository projectRepository, SpecialRateRepository specialRateRepository) {
    this.rateRepository = rateRepository;
    this.projectRepository = projectRepository;
    this.specialRateRepository = specialRateRepository;
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

        Collection<SpecialRate> specialRates= new ArrayList<>(
          specialRateDetails.stream().map(specialRateDetail -> {
            SpecialRate specialRate = new SpecialRate();
            specialRate.setRate(specialRateDetail.getRate());
            specialRate.setKekaUserId(specialRateDetail.getKekaUserId());
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
