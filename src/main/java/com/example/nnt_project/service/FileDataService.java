package com.example.nnt_project.service;

import com.example.nnt_project.entity.FileData;
import com.example.nnt_project.repository.FileDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileDataService {

    private final FileDataRepository fileDataRepository;

    public FileData saveFile(MultipartFile file) throws IOException {
        FileData fileData = new FileData();
        fileData.setFileName(file.getOriginalFilename());
        fileData.setFileData(file.getBytes());
        fileData.setSize(file.getSize());
        return fileDataRepository.save(fileData);
    }

    public Optional<FileData> getFile(UUID id) {
        return fileDataRepository.findById(id);
    }
}
