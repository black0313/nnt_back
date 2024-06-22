package com.example.nnt_project.controller;

import com.example.nnt_project.entity.PickupAddress;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.service.PickupAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pickup-addresses")
@RequiredArgsConstructor
public class PickupAddressController {

    private final PickupAddressService pickupAddressService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllPickupAddresses() {
        List<PickupAddress> pickupAddresses = pickupAddressService.getAllPickupAddresses();
        return ResponseEntity.ok(new ApiResponse("Pickup addresses retrieved successfully", true, pickupAddresses));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getPickupAddressById(@PathVariable UUID id) {
        return pickupAddressService.getPickupAddressById(id)
                .map(pickupAddress -> ResponseEntity.ok(new ApiResponse("Pickup address found", true, pickupAddress)))
                .orElseGet(() -> ResponseEntity.ok(new ApiResponse("Pickup address not found", false)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> savePickupAddress(@RequestBody PickupAddress pickupAddress) {
        PickupAddress savedPickupAddress = pickupAddressService.savePickupAddress(pickupAddress);
        return ResponseEntity.ok(new ApiResponse("Pickup address saved successfully", true, savedPickupAddress));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePickupAddress(@PathVariable UUID id) {
        pickupAddressService.deletePickupAddress(id);
        return ResponseEntity.ok(new ApiResponse("Pickup address deleted successfully", true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updatePickupAddress(@PathVariable UUID id, @RequestBody PickupAddress updatedPickupAddress) {
        return pickupAddressService.updatePickupAddress(id, updatedPickupAddress)
                .map(pickupAddress -> ResponseEntity.ok(new ApiResponse("Pickup address updated successfully", true, pickupAddress)))
                .orElseGet(() -> ResponseEntity.ok(new ApiResponse("Pickup address not found", false)));
    }
}
