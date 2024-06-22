package com.example.nnt_project.repository;

import com.example.nnt_project.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, UUID> {
    Optional<Facility> findByName(String name);
    List<Facility> findByNameContainingIgnoreCase(String name);
}
