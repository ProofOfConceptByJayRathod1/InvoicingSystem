package com.simform.invoicingsystem.repository;

import com.simform.invoicingsystem.entity.SalesPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SalesPersonRepository extends JpaRepository<SalesPerson,Long> {
    Optional<SalesPerson> findByName(String name);
}
