package com.simform.invoicingsystem.repository;

import com.simform.invoicingsystem.entity.DemoUser;
import com.simform.invoicingsystem.service.DemoUserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoUserRepository extends JpaRepository<DemoUser , Long> {

  boolean existsByEmail(String email);

  DemoUser findByEmail(String email);
}
