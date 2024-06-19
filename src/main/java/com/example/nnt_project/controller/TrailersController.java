package com.example.nnt_project.controller;

import com.example.nnt_project.entity.Trailers;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.service.TrailersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/trailers")
@RequiredArgsConstructor
public class TrailersController {

    private final TrailersService trailersService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllTrailers() {
        List<Trailers> trailers = trailersService.getAllTrailers();
        if (trailers.isEmpty()) {
            return ResponseEntity.ok(new ApiResponse("Empty", false, null));
        }
        return ResponseEntity.ok(new ApiResponse("Fetched all trailers", true, trailers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getTrailersById(@PathVariable UUID id) {
        return trailersService.getTrailersById(id)
                .map(trailers -> ResponseEntity.ok(new ApiResponse("Trailers found", true, trailers)))
                .orElseGet(() -> ResponseEntity.ok(new ApiResponse("Trailers not found", false)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveTrailers(@RequestBody Trailers trailers) {
        Trailers savedTrailers = trailersService.saveTrailers(trailers);
        return ResponseEntity.ok(new ApiResponse("Trailers saved successfully", true, savedTrailers));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTrailers(@PathVariable UUID id) {
        trailersService.deleteTrailers(id);
        return ResponseEntity.ok(new ApiResponse("Trailers deleted successfully", true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateTrailers(@PathVariable UUID id, @RequestBody Trailers updatedTrailers) {
        return trailersService.updateTrailers(id, updatedTrailers)
                .map(trailers -> ResponseEntity.ok(new ApiResponse("Trailers updated successfully", true, trailers)))
                .orElseGet(() -> ResponseEntity.ok(new ApiResponse("Trailers not found", false)));
    }
}
