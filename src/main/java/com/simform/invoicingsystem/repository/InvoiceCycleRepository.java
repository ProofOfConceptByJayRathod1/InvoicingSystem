package com.simform.invoicingsystem.repository;

import com.simform.invoicingsystem.entity.InvoiceCycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceCycleRepository extends JpaRepository<InvoiceCycle, Long> {

    Optional<InvoiceCycle> findByCycle(String cycle);
}
