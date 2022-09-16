package com.simform.invoicingsystem.repository;

import com.simform.invoicingsystem.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByName(String name);

    Optional<Client> findByEmail(String email);
}
