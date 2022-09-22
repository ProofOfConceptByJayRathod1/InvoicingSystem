package com.simform.invoicingsystem.repository;

import com.simform.invoicingsystem.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("SELECT c.name FROM Company c WHERE lower(c.name) LIKE lower(CONCAT('%' , ?1 , '%'))")
    List<String> autoSuggestionCompanyName(String companyName);

    Optional<Company> findByName(String name);
}
