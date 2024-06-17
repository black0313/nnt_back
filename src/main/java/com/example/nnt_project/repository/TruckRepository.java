package com.example.nnt_project.repository;

import com.example.nnt_project.entity.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TruckRepository extends JpaRepository<Truck, UUID> {
    Optional<Truck> findByTruckNumber(String truckNumber);
}
