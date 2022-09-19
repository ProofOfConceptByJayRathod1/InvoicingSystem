package com.simform.invoicingsystem.repository;

import com.simform.invoicingsystem.entity.ProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProjectModelRepository extends JpaRepository<ProjectModel, Long> {
    Optional<ProjectModel> findByModel(String model);
}
