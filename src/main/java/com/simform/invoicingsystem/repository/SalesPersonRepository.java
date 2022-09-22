package com.simform.invoicingsystem.repository;

import com.simform.invoicingsystem.entity.SalesPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalesPersonRepository extends JpaRepository<SalesPerson, Long> {
    Optional<SalesPerson> findByName(String name);

    @Query("SELECT s.name FROM SalesPerson s WHERE lower(s.name) LIKE lower(CONCAT('%' , ?1 , '%'))")
    List<String> autoSuggestionSalesPersonName(String salesPersonName);
}
