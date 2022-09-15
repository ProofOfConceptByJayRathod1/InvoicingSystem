package com.simform.invoicingsystem.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.simform.invoicingsystem.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
    Optional<Rate> findByStack(String stack);
}