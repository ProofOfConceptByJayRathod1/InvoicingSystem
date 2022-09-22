package com.simform.invoicingsystem.repository;

import com.simform.invoicingsystem.entity.Csm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CsmRepository extends JpaRepository<Csm, Long> {

    Optional<Csm> findByName(String name);

    @Query("SELECT c.name FROM Csm c WHERE lower(c.name) LIKE lower(CONCAT('%' , ?1 , '%'))")
    List<String> autoSuggestionCsmName(String csmName);
}
