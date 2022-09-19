package com.simform.invoicingsystem.repository;

import com.simform.invoicingsystem.entity.MarketingChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarketingChannelRepository extends JpaRepository<MarketingChannel, Long> {

    Optional<MarketingChannel> findByChannel(String channel);
}
