package com.example.nnt_project.controller;

import com.example.nnt_project.entity.Broker;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.service.BrokerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/brokers")
@RequiredArgsConstructor
public class BrokerController {

    private final BrokerService brokerService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllBrokers() {
        List<Broker> brokers = brokerService.getAllBrokers();
        return ResponseEntity.ok(new ApiResponse("Brokers retrieved successfully", true, brokers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getBrokerById(@PathVariable UUID id) {
        return brokerService.getBrokerById(id)
                .map(broker -> ResponseEntity.ok(new ApiResponse("Broker found", true, broker)))
                .orElseGet(() -> ResponseEntity.ok(new ApiResponse("Broker not found", false)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveBroker(@RequestBody Broker broker) {
        Broker savedBroker = brokerService.saveBroker(broker);
        return ResponseEntity.ok(new ApiResponse("Broker saved successfully", true, savedBroker));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBroker(@PathVariable UUID id) {
        brokerService.deleteBroker(id);
        return ResponseEntity.ok(new ApiResponse("Broker deleted successfully", true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateBroker(@PathVariable UUID id, @RequestBody Broker updatedBroker) {
        return brokerService.updateBroker(id, updatedBroker)
                .map(broker -> ResponseEntity.ok(new ApiResponse("Broker updated successfully", true, broker)))
                .orElseGet(() -> ResponseEntity.ok(new ApiResponse("Broker not found", false)));
    }
}
