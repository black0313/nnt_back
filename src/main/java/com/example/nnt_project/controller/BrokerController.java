package com.example.nnt_project.controller;

import com.example.nnt_project.annotations.CheckPermission;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.payload.BrokerDto;
import com.example.nnt_project.service.BrokerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/brokers")
@RequiredArgsConstructor
public class BrokerController {

    private final BrokerService brokerService;

    @CheckPermission("GET_BROKER")
    @GetMapping
    public ResponseEntity<ApiResponse> getAllBrokers() {
        ApiResponse apiResponse = brokerService.getAllBrokers();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("GET_BROKER")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getBrokerById(@PathVariable UUID id) {
        ApiResponse apiResponse = brokerService.getBrokerById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("ADD_BROKER")
    @PostMapping
    public ResponseEntity<?> saveBroker(@RequestBody BrokerDto brokerDto) {
        ApiResponse apiResponse = brokerService.saveBroker(brokerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @CheckPermission("ADD_BROKER")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBroker(@PathVariable UUID id) {
        brokerService.deleteBroker(id);
        return ResponseEntity.ok(new ApiResponse("Broker deleted successfully", true));
    }

    @CheckPermission("ADD_BROKER")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateBroker(@PathVariable UUID id, @RequestBody BrokerDto brokerDto) {
        ApiResponse apiResponse = brokerService.updateBroker(id, brokerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
