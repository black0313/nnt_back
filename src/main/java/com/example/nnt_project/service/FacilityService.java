package com.example.nnt_project.service;

import com.example.nnt_project.entity.Facility;
import com.example.nnt_project.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FacilityService {

    private final FacilityRepository facilityRepository;

    public List<Facility> getAllFacilities() {
        return facilityRepository.findAll();
    }

    public Optional<Facility> getFacilityById(UUID id) {
        return facilityRepository.findById(id);
    }

    public Facility saveFacility(Facility facility) {
        return facilityRepository.save(facility);
    }

    public void deleteFacility(UUID id) {
        facilityRepository.deleteById(id);
    }

    public Optional<Facility> updateFacility(UUID id, Facility updatedFacility) {
        return facilityRepository.findById(id).map(existingFacility -> {
            existingFacility.setName(updatedFacility.getName());
            return facilityRepository.save(existingFacility);
        });
    }

    public List<Facility> searchFacilitiesByName(String name) {
        return facilityRepository.findByNameContainingIgnoreCase(name);
    }
}
