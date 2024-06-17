package com.example.nnt_project.service;

import com.example.nnt_project.entity.Trailers;
import com.example.nnt_project.repository.TrailersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrailersService {

    private final TrailersRepository trailersRepository;

    public Optional<Trailers> getTrailersById(UUID id) {
        return trailersRepository.findById(id);
    }

    public Trailers saveTrailers(Trailers trailers) {
        return trailersRepository.save(trailers);
    }

    public void deleteTrailers(UUID id) {
        trailersRepository.deleteById(id);
    }

    public List<Trailers> getAllTrailers() {
        return trailersRepository.findAll();
    }
}
