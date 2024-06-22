package com.example.nnt_project.repository;

import com.example.nnt_project.entity.PickupAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PickupAddressRepository extends JpaRepository<PickupAddress, UUID> {
    Optional<PickupAddress> findByAddress(String address);
}
