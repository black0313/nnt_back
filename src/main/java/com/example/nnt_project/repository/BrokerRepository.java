package com.example.nnt_project.repository;

import com.example.nnt_project.entity.Broker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BrokerRepository extends JpaRepository<Broker, UUID> {
    Optional<Broker> findByName(String name);
}
