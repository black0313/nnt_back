package com.example.nnt_project.controller;

import com.example.nnt_project.entity.FileData;
import com.example.nnt_project.payload.ApiResponse;
import com.example.nnt_project.service.FileDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileDataController {

    private final FileDataService fileDataService;

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ApiResponse> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        FileData savedFile = fileDataService.saveFile(file);
        return ResponseEntity.ok(new ApiResponse("File uploaded successfully", true, savedFile));
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable UUID id) {
        Optional<FileData> fileData = fileDataService.getFile(id);
        return fileData.map(data -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(data.getFileData()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
