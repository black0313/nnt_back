package com.example.nnt_project.controller;

import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.payload.TrailersDto;
import com.example.nnt_project.service.TrailersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/trailers")
@RequiredArgsConstructor
public class TrailersController {

    private final TrailersService trailersService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllTrailers() {
        ApiResponse apiResponse = trailersService.getAllTrailers();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getTrailersById(@PathVariable UUID id) {
        ApiResponse apiResponse = trailersService.getTrailersById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveTrailers(@RequestBody TrailersDto trailersDto) {
        ApiResponse apiResponse = trailersService.saveTrailers(trailersDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTrailers(@PathVariable UUID id) {
        trailersService.deleteTrailers(id);
        return ResponseEntity.ok(new ApiResponse("Trailers deleted successfully", true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateTrailers(@PathVariable UUID id, @RequestBody TrailersDto trailersDto) {
        ApiResponse apiResponse = trailersService.updateTrailers(id, trailersDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
