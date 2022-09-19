package com.simform.invoicingsystem.repository;

import com.simform.invoicingsystem.entity.LeadSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LeadSourceRepository extends JpaRepository<LeadSource, Long> {

    Optional<LeadSource> findBySource(String source);
}
