package com.example.nnt_project.controller;

import com.example.nnt_project.dto.DispatchersDto;
import com.example.nnt_project.entity.Dispatchers;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.payload.DispatcherDTO;
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
    public ResponseEntity<?> getAllDispatchers() {
        ApiResponse apiResponse = dispatchersService.getAllDispatchers();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DispatcherDTO dispatcherDTO) {
        ApiResponse apiResponse = dispatchersService.saveDispatcher(dispatcherDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDispatcher(@PathVariable UUID id) {
        dispatchersService.deleteDispatcher(id);
        return ResponseEntity.ok(new ApiResponse("Dispatcher deleted successfully", true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDispatcher(@PathVariable UUID id, @RequestBody DispatcherDTO dispatcherDTO) {
        ApiResponse apiResponse = dispatchersService.updateDispatcher(id, dispatcherDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/dateRange")
    public ResponseEntity<ApiResponse> getDispatchersByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        List<Dispatchers> dispatchersList = dispatchersService.getDispatchersByDateRange(startDate, endDate);
        return ResponseEntity.ok(new ApiResponse("Dispatchers retrieved successfully", true, dispatchersList));
    }
}
