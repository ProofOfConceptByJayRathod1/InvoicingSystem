package com.simform.invoicingsystem.repository;

import com.simform.invoicingsystem.entity.Csm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CsmRepository extends JpaRepository<Csm, Long> {

    Optional<Csm> findByName(String name);
}
