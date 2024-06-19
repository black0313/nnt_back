package com.example.nnt_project.controller;

import com.example.nnt_project.entity.Dispatchers;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.service.DispatchersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dispatchers")
@RequiredArgsConstructor
public class DispatchersController {

    private final DispatchersService dispatchersService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getDispatcherById(@PathVariable UUID id) {
        return dispatchersService.getDispatcherById(id)
                .map(dispatcher -> ResponseEntity.ok(new ApiResponse("Dispatcher found", true, dispatcher)))
                .orElseGet(() -> ResponseEntity.ok(new ApiResponse("Dispatcher not found", false)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllDispatchers() {
        List<Dispatchers> dispatchersList = dispatchersService.getAllDispatchers();
        return ResponseEntity.ok(new ApiResponse("Dispatchers retrieved successfully", true, dispatchersList));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveDispatcher(@RequestBody Dispatchers dispatcher) {
        Dispatchers savedDispatcher = dispatchersService.saveDispatcher(dispatcher);
        return ResponseEntity.ok(new ApiResponse("Dispatcher saved successfully", true, savedDispatcher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDispatcher(@PathVariable UUID id) {
        dispatchersService.deleteDispatcher(id);
        return ResponseEntity.ok(new ApiResponse("Dispatcher deleted successfully", true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateDispatcher(@PathVariable UUID id, @RequestBody Dispatchers updatedDispatcher) {
        return dispatchersService.updateDispatcher(id, updatedDispatcher)
                .map(dispatcher -> ResponseEntity.ok(new ApiResponse("Dispatcher updated successfully", true, dispatcher)))
                .orElseGet(() -> ResponseEntity.ok(new ApiResponse("Dispatcher not found", false)));
    }

    @GetMapping("/dateRange")
    public ResponseEntity<ApiResponse> getDispatchersByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        List<Dispatchers> dispatchersList = dispatchersService.getDispatchersByDateRange(startDate, endDate);
        return ResponseEntity.ok(new ApiResponse("Dispatchers retrieved successfully", true, dispatchersList));
    }
}
