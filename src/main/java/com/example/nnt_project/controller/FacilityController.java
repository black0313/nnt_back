package com.example.nnt_project.controller;

import com.example.nnt_project.entity.Facility;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.service.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/facilities")
@RequiredArgsConstructor
public class FacilityController {

    private final FacilityService facilityService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllFacilities() {
        List<Facility> facilities = facilityService.getAllFacilities();
        return ResponseEntity.ok(new ApiResponse("Facilities retrieved successfully", true, facilities));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getFacilityById(@PathVariable UUID id) {
        return facilityService.getFacilityById(id)
                .map(facility -> ResponseEntity.ok(new ApiResponse("Facility found", true, facility)))
                .orElseGet(() -> ResponseEntity.ok(new ApiResponse("Facility not found", false)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveFacility(@RequestBody Facility facility) {
        Facility savedFacility = facilityService.saveFacility(facility);
        return ResponseEntity.ok(new ApiResponse("Facility saved successfully", true, savedFacility));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFacility(@PathVariable UUID id) {
        facilityService.deleteFacility(id);
        return ResponseEntity.ok(new ApiResponse("Facility deleted successfully", true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateFacility(@PathVariable UUID id, @RequestBody Facility updatedFacility) {
        return facilityService.updateFacility(id, updatedFacility)
                .map(facility -> ResponseEntity.ok(new ApiResponse("Facility updated successfully", true, facility)))
                .orElseGet(() -> ResponseEntity.ok(new ApiResponse("Facility not found", false)));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchFacilitiesByName(@RequestParam String name) {
        List<Facility> facilities = facilityService.searchFacilitiesByName(name);
        return ResponseEntity.ok(new ApiResponse("Facilities found", true, facilities));
    }
}
