package com.simform.invoicingsystem.repository;

import com.simform.invoicingsystem.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByName(String name);

    @Query("SELECT p FROM Project p WHERE lower(p.name) LIKE lower(CONCAT('%' , ?1 , '%'))")
    List<Project> searchProjectByName(String projectName);

    boolean existsByName(String name);
}