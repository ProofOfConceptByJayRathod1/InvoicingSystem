package com.simform.invoicingsystem.repository;

import com.simform.invoicingsystem.entity.AccType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccTypesRepository extends JpaRepository<AccType, Long> {
    Optional<AccType> findByType(String type);
}
